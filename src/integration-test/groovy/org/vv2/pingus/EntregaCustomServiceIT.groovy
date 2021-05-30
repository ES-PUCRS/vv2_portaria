package org.vv2.pingus

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import java.text.SimpleDateFormat
import groovy.time.TimeCategory

@Integration
@Rollback
class EntregaCustomServiceIT extends Specification {

    static def sdf = new SimpleDateFormat("dd/MM/yy HH:mm")
    EntregaCustomService entregaCustomService
    EntregaService entregaService

    private long setupData() {
        new Entrega(
                criado: sdf.parse(sdf.format(use (TimeCategory) { new Date() - 1.day })),
                descricao: "sedex cx 27x25x70cm",
                apto: 103,
                operador: new Operador(nome: "Jose Otavio da Mata")
        ).save(flush: true, failOnError: true)
        new Entrega(
                criado: sdf.parse(sdf.format(use (TimeCategory) { new Date() - 22.minute })),
                descricao: "sedex envelope",
                apto: 101,
                operador: new Operador(nome: "Jose da Mata")
        ).save(flush: true, failOnError: true)
        new Entrega(
                criado: sdf.parse(sdf.format(use (TimeCategory) { new Date() - 1.hour })),
                descricao: "sedex cx",
                apto: 101,
                operador: new Operador(nome: "Jose Otavio da Mata")
        ).save(flush: true, failOnError: true)

        def entrega = new Entrega(
                criado: sdf.parse(sdf.format(use (TimeCategory) { new Date() - 1.week })),
                descricao: "correios carta pequena",
                apto: 101,
                operador: new Operador(nome: "Jose Otavio da Mata"),
                retirado: sdf.parse(sdf.format(use (TimeCategory) { new Date() - 1.day })),
                morador: new Morador(nome: "Levi Kate Jesus", apto: 1, rg: "24.222.979-2", inativo: false)
        ).save(flush: true, failOnError: true)
        new Entrega(
                criado: sdf.parse(sdf.format(use (TimeCategory) { new Date() - 1.month })),
                descricao: "correios cx media",
                apto: 101,
                operador: new Operador(nome: "Jose Otavio da Mata"),
                retirado: sdf.parse(sdf.format(use (TimeCategory) { new Date() - 1.day })),
                morador: new Morador(nome: "Levi Kate Jesus", apto: 2, rg: "24.222.979-3", inativo: false)
        ).save(flush: true, failOnError: true)
        entrega?.id
    }

    def cleanup() {
        Entrega.deleteAll()
    }

    void "Test entregaCustomService searchByDESCRICAO 3"() {
        setupData()
        def params = [
                'searchfield'   : "DESCRICAO",
                'searchfor'     : "cx"
        ]

        when:"Setup list"
        def list = entregaCustomService.searchBy(params.searchfield as String, params.searchfor)

        then: "Assert all"
        list.size() == 3
    }

    void "Test entregaCustomService searchByDESCRICAO 1"() {
        setupData()
        def params = [
                'searchfield'   : "DESCRICAO",
                'searchfor'     : "envelope"
        ]

        when:"Setup list"
        def list = entregaCustomService.searchBy(params.searchfield as String, params.searchfor)

        then: "Assert one"
        list.size() == 1
    }

    void "Test entregaCustomService searchByDESCRICAO 0"() {
        setupData()
        def params = [
                'searchfield'   : "DESCRICAO",
                'searchfor'     : "grande"
        ]

        when:"Setup list"
        def list = entregaCustomService.searchBy(params.searchfield as String, params.searchfor)

        then: "Assert none"
        list.size() == 0
    }

    void "Test entregaCustomService searchByRETIRADA 0"() {
        setupData()
        def entrega = Entrega.all.findAll {
            it.retirado != null
        }
        def date = entrega[0].retirado
        def assertDate = sdf.format(date)

        def params = [
                'searchfield'   : "RETIRADA",
                'searchfor'     : "${assertDate}"
        ]

        when:"Setup list"
        def list = entregaCustomService.searchBy(params.searchfield as String, params.searchfor)

        then: "Assert one"
        assert list.get(0).retirado == date
    }

    void "Test entregaCustomService searchByRETIRADA null"() {
        setupData()
        def params = [
                'searchfield'   : "RETIRADA",
                'searchfor'     : ""
        ]

        when:"Setup list"
        def list = entregaCustomService.searchBy(params.searchfield as String, params.searchfor)

        then: "Assert none delivered"
        def filtered = list.findAll {
            it?.morador != null
        }
        assert filtered.size() == 0
    }

    void "Test find all not delivered"() {
        setupData()
        when:"Setup list"
        then:"Should find 3"
        assert entregaCustomService.findAllNotDelivered() == 3
    }

//    void "Test the average of the delivery time"() {
//        when:"Setup list"
//        then:"Should find 3"
//        assert entregaCustomService.findDeliveryAverageTime()
//    }

    void "Test find on last 30 days"() {
        setupData()
        when:"Setup list"
        then:"Should find 4"
        assert entregaCustomService.findAllByDateCreatedGreaterThan(use (TimeCategory) { new Date() - 1.month }).size() == 4
    }

    void "Test delivery that has not been taken"() {
        setupData()
        given:"There is a package not taken"
        def list = Entrega.all.findAll{it.retirado == null}
        def entrega = list.get(0)
        def size = Entrega.count()

        entrega.morador = new Morador(nome: "Dummy Buddy", rg: "100000001", apto: 101, inativo: false).save(flush: true)

        when:"We try to take a delivery"
        entregaCustomService.save(entrega)

        then:"Should have the entrega updated"
        def entregue = entregaService.get(entrega.id)
        assert size == Entrega.count()
        assert entregue.retirado != null
        assert entregue.morador.nome == "Dummy Buddy"
    }

    void "Test delivery already taken"() {
        setupData()
        given:"There is a package already taken"
        def list = Entrega.all.findAll{it.retirado != null}
        def entrega = list.get(0)

        entrega.morador = new Morador(nome: "Dummy Test", rg: "000000000", apto: 111, inativo: false)

        when:"We try to save an already taken delivery"
        entregaCustomService.save(entrega)

        then:"Should respond exception IllegalArgumentException and guarantee that the morador was not changed"
        Exception e = thrown()
        'The package has already been taken' == e.message
    }

}
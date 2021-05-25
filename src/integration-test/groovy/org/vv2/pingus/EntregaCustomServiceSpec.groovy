package org.vv2.pingus

import grails.testing.services.ServiceUnitTest
import grails.testing.gorm.DomainUnitTest
import java.text.SimpleDateFormat
import spock.lang.Specification

class EntregaCustomServiceSpec extends Specification implements ServiceUnitTest<EntregaCustomService>, DomainUnitTest<Entrega> {

    static def sdf = new SimpleDateFormat("dd/MM/yy HH:mm")

    def setup() {
        new Entrega(
                criado: sdf.parse("14/12/20 11:26"),
                descricao: "sedex cx 27x25x70cm",
                apto: 103,
                operador: new Operador(nome: "Jose Otavio da Mata")
        ).save(flush: true)
        new Entrega(
                criado: sdf.parse("16/12/20 12:26"),
                descricao: "sedex envelope",
                apto: 101,
                operador: new Operador(nome: "Jose Otavio da Mata")
        ).save(flush: true)

        new Entrega(
                criado: sdf.parse("01/01/21 11:00"),
                descricao: "correios carta pequena",
                apto: 101,
                operador: new Operador(nome: "Jose Otavio da Mata"),
                retirado: sdf.parse("03/04/21 09:01"),
                morador: Morador.findByNome("Levi Kate Jesus")
        ).save(flush: true)
        new Entrega(
                criado: sdf.parse("01/01/21 09:07"),
                descricao: "correios cx media",
                apto: 101,
                operador: new Operador(nome: "Jose Otavio da Mata"),
                retirado: sdf.parse("04/04/21 11:00"),
                morador: Morador.findByNome("Levi Kate Jesus")
        ).save(flush: true)
    }

    def cleanup() {
        Entrega.deleteAll()
    }

    void "Test EntregaCustomService searchByDESCRICAO 2"() {
        def params = [
            'searchfield'   : "DESCRICAO",
            'searchfor'     : "cx"
        ]

        when:"Setup list"
        def list = EntregaCustomService.searchBy(params.searchfield as String, params.searchfor)
        
        then: "Assert all"
            list.size() == 2
    }

    void "Test EntregaCustomService searchByDESCRICAO 1"() {
        def params = [
                'searchfield'   : "DESCRICAO",
                'searchfor'     : "envelope"
        ]

        when:"Setup list"
        def list = EntregaCustomService.searchBy(params.searchfield as String, params.searchfor)

        then: "Assert one"
        list.size() == 1
    }

    void "Test EntregaCustomService searchByDESCRICAO 0"() {
        def params = [
                'searchfield'   : "DESCRICAO",
                'searchfor'     : "grande"
        ]

        when:"Setup list"
        def list = EntregaCustomService.searchBy(params.searchfield as String, params.searchfor)

        then: "Assert none"
        list.size() == 0
    }


    void "Test EntregaCustomService searchByRETIRADA 0"() {
        def params = [
                'searchfield'   : "RETIRADA",
                'searchfor'     : "04/04"
        ]

        when:"Setup list"
        def list = EntregaCustomService.searchBy(params.searchfield as String, params.searchfor)

        then: "Assert none"
        list.get(0).retirado.toString() == sdf.parse("04/04/21 11:00").toString()
    }

}
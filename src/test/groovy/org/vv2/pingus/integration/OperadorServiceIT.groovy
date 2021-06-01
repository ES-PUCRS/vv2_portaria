package org.vv2.pingus.integration

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import groovy.time.TimeCategory
import org.vv2.pingus.Entrega
import org.vv2.pingus.Morador
import org.vv2.pingus.Operador
import org.vv2.pingus.OperadorService
import spock.lang.Specification
import org.hibernate.SessionFactory

import java.text.SimpleDateFormat

@Integration
@Rollback
class OperadorServiceIT extends Specification {

    static def sdf = new SimpleDateFormat("dd/MM/yy HH:mm")
    OperadorService operadorService
    SessionFactory sessionFactory

    private Long setupData() {
        new Operador(nome: "Rebeca Adriana da Mota").save()
        def operador = new Operador(nome: "Daiane Evelyn Rita da Costa").save()
        new Operador(nome: "Benicio Danilo Arthur Barbosa").save()
        def op = new Operador(nome: "Jose Otavio da Mata").save()

        new Entrega(
                criado: sdf.parse(sdf.format(use (TimeCategory) { new Date() - 1.hour })),
                descricao: "sedex cx",
                apto: 101,
                operador: op
        ).save(flush: true, failOnError: true)

        operador.id
    }

    void "test get"() {
        def operadorId = setupData()

        expect:
        operadorService.get(operadorId) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Operador> operadorList = operadorService.list(max: 2, offset: 2)

        then:
        operadorList.size() == 2
    }

    void "test count"() {
        setupData()

        expect:
        operadorService.count() == 4
    }

    void "test delete"() {
        Long operadorId = setupData()

        expect:
        operadorService.count() == 4

        when:
        operadorService.delete(operadorId)
        sessionFactory.currentSession.flush()

        then:
        operadorService.count() == 3
    }

    void "test delete when there is a delivery relation"() {
        given:"The setup is mounted"
        Long operadorId = setupData()
        new Entrega(
                criado: sdf.parse(sdf.format(use (TimeCategory) { new Date() - 1.month })),
                descricao: "correios cx media",
                apto: 101,
                operador: Operador.findById(operadorId)
        ).save(flush: true, failOnError: true)

        expect: "Find 4 operadores"
        operadorService.count() == 4

        when:"Try to delete an operador with relationship"
        operadorService.delete(operadorId)
        sessionFactory.currentSession.flush()

        then:"Should not delete and throw an error"
        assert operadorService.count() == 4
        Exception e = thrown()
        assert e.message.startsWith('deleted object would be re-saved by cascade (remove deleted object from associations): [org.vv2.pingus.Operador#')
    }

    void "test save"() {
        when:
        Operador operador = new Operador(nome: "Jair dos Santos")
        operadorService.save(operador)

        then:
        operador.id != null
    }
}

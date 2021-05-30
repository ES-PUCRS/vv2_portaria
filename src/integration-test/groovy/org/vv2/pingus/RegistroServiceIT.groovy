package org.vv2.pingus

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class RegistroServiceIT extends Specification {

    RegistroService registroService
    SessionFactory sessionFactory

    private Long setupData() {
        new Registro(operador: new Operador(nome: "Gabriel Rabelo Almeida"),jSessionId: "cookie1").save(flush: true, failOnError: true)
        new Registro(operador: new Operador(nome: "Pedro Maia Rogoski"),jSessionId: "cookie2").save(flush: true, failOnError: true)
        Registro registro = new Registro(operador: new Operador(nome: "Cleyson Braga de Oliveira"),jSessionId: "cookie3").save(flush: true, failOnError: true)
        new Registro(operador: new Operador(nome: "Rafael dos Santos Cardoso"),jSessionId: "cookie4").save(flush: true, failOnError: true)
        new Registro(operador: new Operador(nome: "Vinicius Bazanella"),jSessionId: "cookie5").save(flush: true, failOnError: true)
        registro.id
    }

    void "test get"() {
        setupData()

        expect:
        registroService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Registro> registroList = registroService.list(max: 2, offset: 2)

        then:
        registroList.size() == 2
    }

    void "test count"() {
        setupData()

        expect:
        registroService.count() == 5
    }

    void "test delete"() {
        Long registroId = setupData()

        expect:
        registroService.count() == 5

        when:
        registroService.delete(registroId)
        sessionFactory.currentSession.flush()

        then:
        registroService.count() == 4
    }

    void "test save"() {
        when:
        Registro registro =
            new Registro(
                operador: new Operador(nome: "Barco de Teseu"),
                jSessionId: "cookie6"
            )

        registroService.save(registro)

        then:
        registro.id != null
    }
}

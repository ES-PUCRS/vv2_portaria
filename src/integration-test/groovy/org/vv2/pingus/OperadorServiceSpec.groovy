package org.vv2.pingus

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class OperadorServiceSpec extends Specification {

    OperadorService operadorService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Operador(...).save(flush: true, failOnError: true)
        //new Operador(...).save(flush: true, failOnError: true)
        //Operador operador = new Operador(...).save(flush: true, failOnError: true)
        //new Operador(...).save(flush: true, failOnError: true)
        //new Operador(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //operador.id
    }

    void "test get"() {
        setupData()

        expect:
        operadorService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Operador> operadorList = operadorService.list(max: 2, offset: 2)

        then:
        operadorList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        operadorService.count() == 5
    }

    void "test delete"() {
        Long operadorId = setupData()

        expect:
        operadorService.count() == 5

        when:
        operadorService.delete(operadorId)
        sessionFactory.currentSession.flush()

        then:
        operadorService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Operador operador = new Operador()
        operadorService.save(operador)

        then:
        operador.id != null
    }
}

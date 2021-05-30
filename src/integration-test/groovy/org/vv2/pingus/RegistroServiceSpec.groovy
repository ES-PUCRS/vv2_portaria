package org.vv2.pingus

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class RegistroServiceSpec extends Specification {

    RegistroService registroService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Registro(...).save(flush: true, failOnError: true)
        //new Registro(...).save(flush: true, failOnError: true)
        //Registro registro = new Registro(...).save(flush: true, failOnError: true)
        //new Registro(...).save(flush: true, failOnError: true)
        //new Registro(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //registro.id
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
        assert false, "TODO: Verify the correct instances are returned"
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
        assert false, "TODO: Provide a valid instance to save"
        Registro registro = new Registro()
        registroService.save(registro)

        then:
        registro.id != null
    }
}

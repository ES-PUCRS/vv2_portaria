package org.vv2.pingus

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class MoradorServiceSpec extends Specification {

    MoradorService moradorService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Morador(...).save(flush: true, failOnError: true)
        //new Morador(...).save(flush: true, failOnError: true)
        //Morador morador = new Morador(...).save(flush: true, failOnError: true)
        //new Morador(...).save(flush: true, failOnError: true)
        //new Morador(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //morador.id
    }

//    void "test get"() {
//        setupData()
//
//        expect:
//        moradorService.get(1) != null
//    }
//
//    void "test list"() {
//        setupData()
//
//        when:
//        List<Morador> moradorList = moradorService.list(max: 2, offset: 2)
//
//        then:
//        moradorList.size() == 2
//        assert false, "TODO: Verify the correct instances are returned"
//    }
//
//    void "test count"() {
//        setupData()
//
//        expect:
//        moradorService.count() == 5
//    }
//
//    void "test delete"() {
//        Long moradorId = setupData()
//
//        expect:
//        moradorService.count() == 5
//
//        when:
//        moradorService.delete(moradorId)
//        sessionFactory.currentSession.flush()
//
//        then:
//        moradorService.count() == 4
//    }
//
//    void "test save"() {
//        when:
//        assert false, "TODO: Provide a valid instance to save"
//        Morador morador = new Morador()
//        moradorService.save(morador)
//
//        then:
//        morador.id != null
//    }
}

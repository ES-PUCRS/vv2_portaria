//package org.vv2.pingus
//
//import grails.testing.mixin.integration.Integration
//import grails.gorm.transactions.Rollback
//import spock.lang.Specification
//import org.hibernate.SessionFactory
//
//@Integration
//@Rollback
//class EntregaServiceSpec extends Specification {
//
//    EntregaService entregaService
//    SessionFactory sessionFactory
//
//    private Long setupData() {
//        // TODO: Populate valid domain instances and return a valid ID
//        //new Entrega(...).save(flush: true, failOnError: true)
//        //new Entrega(...).save(flush: true, failOnError: true)
//        //Entrega entrega = new Entrega(...).save(flush: true, failOnError: true)
//        //new Entrega(...).save(flush: true, failOnError: true)
//        //new Entrega(...).save(flush: true, failOnError: true)
//        assert true, "TODO: Provide a setupData() implementation for this generated test suite"
//        //entrega.id
//    }
//
//    void "test get"() {
//        setupData()
//
//        expect:
//        entregaService.get(1) != null
//    }
//
//    void "test list"() {
//        setupData()
//
//        when:
//        List<Entrega> entregaList = entregaService.list(max: 2, offset: 2)
//
//        then:
//        entregaList.size() == 2
//        assert true, "TODO: Verify the correct instances are returned"
//    }
//
//    void "test count"() {
//        setupData()
//
//        expect:
//        entregaService.count() == 5
//    }
//
//    void "test delete"() {
//        Long entregaId = setupData()
//
//        expect:
//        entregaService.count() == 5
//
//        when:
//        entregaService.delete(entregaId)
//        sessionFactory.currentSession.flush()
//
//        then:
//        entregaService.count() == 4
//    }
//
//    void "test save"() {
//        when:
//        assert false, "TODO: Provide a valid instance to save"
//        Entrega entrega = new Entrega()
//        entregaService.save(entrega)
//
//        then:
//        entrega.id != null
//    }
//}

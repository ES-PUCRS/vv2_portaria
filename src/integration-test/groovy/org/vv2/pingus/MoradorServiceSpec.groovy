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

    private static Long setupData() {
        new Morador(nome: "Levi Kazue",                 rg: "24.222.979-7", apto: 101, inativo: true ).save(flush: true)
        new Morador(nome: "Geraldo Antonio Viana",      rg: "47.412.808-8", apto: 102, inativo: false).save(flush: true)
        new Morador(nome: "Marcos Felipe Moreira",      rg: "38.597.970-8", apto: 103, inativo: false).save(flush: true)
        new Morador(nome: "Rosa Costa",                 rg: "50.835.610-6", apto: 104, inativo: false).save(flush: true)
        new Morador(nome: "Davi Julio",                 rg: "46.224.780-6", apto: 201, inativo: true ).save(flush: true)
        new Morador(nome: "Pedro Santos",               rg: "20.173.376-6", apto: 202, inativo: false).save(flush: true)

        Morador.count()
    }

    void "test get"() {
        setupData()

        expect:
        moradorService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Morador> moradorList = moradorService.list(max: 2, offset: 2)

        then:
        moradorList.size() == 2
        assert moradorList.get(0).getNome() == "Marcos Felipe Moreira"
        assert moradorList.get(1).getNome() == "Rosa Costa"
    }

    void "test count"() {
        setupData()

        expect:
        moradorService.count() == 6
    }

    void "test delete"() {
        Long moradorId = setupData()

        expect:
        moradorService.count() == 6

        when:
        moradorService.delete(moradorId)
        sessionFactory.currentSession.flush()

        then:
        moradorService.count() == 5
    }

    void "test save"() {
        when:
        Morador morador = new Morador(nome: "Davi Julio", rg: "46.224.780-6", apto: 201, inativo: true).save(flush: true)
        moradorService.save(morador)

        then:
        morador.id != null
    }
}
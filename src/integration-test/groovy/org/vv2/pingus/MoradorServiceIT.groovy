package org.vv2.pingus

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class MoradorServiceIT extends Specification {

    MoradorService moradorService
    SessionFactory sessionFactory

    private static Long setupData() {
        new Morador(nome: "Jennifer Alicia Vieira",          rg: "41.092.275-4", apto: 302, inativo: false).save()
        new Morador(nome: "Mateus Pietro Caue da Mota",      rg: "27.244.251-3", apto: 303, inativo: false).save()
        def morador = new Morador(nome: "Arthur Renan Pietro Melo",        rg: "34.971.550-6", apto: 304, inativo: false).save()
        new Morador(nome: "Aparecida Pietra Fogaca",         rg: "34.971.550-6", apto: 401, inativo: false).save()
        new Morador(nome: "Davi Benicio Jesus",              rg: "36.291.849-1", apto: 402, inativo: false).save()
        morador.id
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
    }

    void "test count"() {
        setupData()

        expect:
        moradorService.count() == 5
    }

    void "test delete"() {
        Long moradorId = setupData()

        expect:
        moradorService.count() == 5

        when:
        moradorService.delete(moradorId)
        sessionFactory.currentSession.flush()

        then:
        moradorService.count() == 4
    }

    void "test save"() {
        when:
        Morador morador =
            new Morador(nome: "Pedro Murilo Santos",             rg: "20.173.376-6", apto: 202, inativo: false).save()
        moradorService.save(morador)

        then:
        morador.id != null
    }
}

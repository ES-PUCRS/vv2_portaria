package org.vv2.pingus

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class OperadorServiceIT extends Specification {

    OperadorService operadorService
    SessionFactory sessionFactory

    private Long setupData() {
        new Operador(nome: "Rebeca Adriana da Mota").save()
        def operador = new Operador(nome: "Daiane Evelyn Rita da Costa").save()
        new Operador(nome: "Benicio Danilo Arthur Barbosa").save()
        new Operador(nome: "Jose Otavio da Mata").save()

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

    void "test save"() {
        when:
        Operador operador = new Operador(nome: "Jair dos Santos")
        operadorService.save(operador)

        then:
        operador.id != null
    }
}

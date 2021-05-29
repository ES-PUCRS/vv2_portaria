package org.vv2.pingus

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class OperadorSpec extends Specification implements DomainUnitTest<Operador> {

    def setup() {
        new Operador(
            nome: "Rebeca Adriana da Mota"
        ).save(flush: true)
        new Operador(
            nome: "Jose Otavio"
        ).save(flush: true)
    }

    def cleanup() {
        Operador.deleteAll()
    }

    void "Test create operador"() {
        when:"There is no change between "
        def count = Operador.count()

        new Operador(
            nome: "Raiane Evelyn Rita da Costa"
        ).save(flush: true)

        then:"The model is correct"
        Operador.count() == count + 1
    }

    void "Teste delete operador"() {
        when:"There is no change between"
        def count = Operador.count()

        Operador.findById(1).delete(flush: true)

        then:"The model is correct"
        Operador.count() == count - 1
    }

    void "Test toString response"() {
        when:"There is no change"
        then:"The model is correct"
        Operador?.findByNome("Jose Otavio")?.toString() == "Jose Otavio"
    }
}

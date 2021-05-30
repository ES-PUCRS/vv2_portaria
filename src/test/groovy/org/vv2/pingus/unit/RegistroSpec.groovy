package org.vv2.pingus.unit

import grails.testing.gorm.DomainUnitTest
import org.vv2.pingus.Operador
import org.vv2.pingus.Registro
import spock.lang.Specification

class RegistroSpec extends Specification implements DomainUnitTest<Registro> {

    def setup() {
        new Registro(
            operador: new Operador(nome: "Jose Otavio da Mata"),
            jSessionId: "cookie1"
        ).save(flush: true)
        new Registro(
            operador: new Operador(nome: "Jose Otavio da Mata"),
            jSessionId: "cookie2"
        ).save(flush: true)
    }

    def cleanup() {
        Registro.deleteAll()
    }

    void "Test create registro"() {
        when:"There is no change between "
        def count = Registro.count()

        new Registro(
            operador: new Operador(nome: "Jose Otavio da Mata"),
            jSessionId: "cookie${count}"
        ).save(flush: true)

        then:"The model is correct"
        Registro.count() == count + 1
    }

    void "Teste delete registro"() {
        when:"There is no change between"
        def count = Registro.count()

        Registro.findById(1).delete(flush: true)

        then:"The model is correct"
        Registro.count() == count - 1
    }
}

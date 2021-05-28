package org.vv2.pingus

import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class RegistroControllerIntegrationSpec extends Specification implements ControllerUnitTest<RegistroController>, DomainUnitTest<Operador> {

    private static Long setupData() {
        new Operador(nome: 'Thomas')
        new Operador(nome: 'Ricardo')
        new Operador(nome: 'Joelson')
        Operador.findByNome("Thomas").get(1).getId()
    }

    void "Test the show Registro"() {
        given:
        setupData()

        when:"The show action is executed with a null domain"
        controller.show()

        then:"A 404 error is returned"
        response.status == 200
    }

}

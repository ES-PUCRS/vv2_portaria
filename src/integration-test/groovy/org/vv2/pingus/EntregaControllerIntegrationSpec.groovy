package org.vv2.pingus

import grails.testing.web.controllers.ControllerUnitTest
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class EntregaControllerIntegrationSpec extends Specification implements ControllerUnitTest<EntregaController> , DomainUnitTest<Entrega> {

    void "Test the create action with no operator logged"() {
        when:"The create action is executed"
        controller.create()


        then:"On the register page"
            $('title').text() == ''
    }

}

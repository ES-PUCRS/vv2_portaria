package org.vv2.pingus

import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.*

class RegistroControllerSpec extends Specification implements ControllerUnitTest<RegistroController>, DomainUnitTest<Registro> {

    void "Test the index action returns the correct model"() {
        given:
        controller.registroService = Mock(RegistroService) {
            1 * list(_) >> []
            1 * count() >> 0
        }

        when:"The index action is executed"
        controller.index()

        then:"The model is correct"
        !model.registroList
        model.registroCount == 0
    }

}







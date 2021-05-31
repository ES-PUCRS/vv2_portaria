package org.vv2.pingus.unit

import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import org.vv2.pingus.Registro
import org.vv2.pingus.RegistroController
import org.vv2.pingus.RegistroService
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
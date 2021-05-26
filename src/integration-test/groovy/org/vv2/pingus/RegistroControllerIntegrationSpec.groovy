//package org.vv2.pingus
//
//import grails.testing.gorm.DomainUnitTest
//import grails.testing.web.controllers.ControllerUnitTest
//import spock.lang.Specification
//
//class RegistroControllerIntegrationSpec extends Specification implements ControllerUnitTest<RegistroController>, DomainUnitTest<Registro> {
//
//    void "Test the edit action with a null id"() {
//        given:
//        controller.registroService = Mock(RegistroService) {
//            1 * get(null) >> null
//        }
//
//        when:"The show action is executed with a null domain"
//        controller.edit(null)
//
//        then:"A 404 error is returned"
//        response.status == 404
//    }
//
//}

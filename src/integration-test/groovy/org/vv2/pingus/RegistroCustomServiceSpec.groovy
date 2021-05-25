package org.vv2.pingus

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

import javax.servlet.http.HttpServletMapping
import javax.servlet.http.HttpServletRequest

class RegistroCustomServiceSpec extends Specification implements ServiceUnitTest<RegistroCustomService> {

    HttpServletMapping request

    def setup() {
        new Registro(operador: new Operador(nome: "Jose Otavio da Mata"), jSessionId: "cookie1").save()
        new Registro(operador: new Operador(nome: "Jose Otavio da Mata"), jSessionId: "cookie2").save()
        new Registro(operador: new Operador(nome: "Jose Otavio da Mata"), jSessionId: "cookie3").save()
        new Registro(operador: new Operador(nome: "Jose Otavio da Mata"), jSessionId: "cookie4").save()
        request = HttpServletRequest.getHttpServletMapping()
    }

    def cleanup() {
        Registro.deleteAll()
    }

    void "Test RegistroCustomService find inative"() {
        when:"Setup morador list"
        RegistroCustomService.searchByCookieActive(request)

        then: "Assert 3 falses"
        list.size() == 1
    }
}

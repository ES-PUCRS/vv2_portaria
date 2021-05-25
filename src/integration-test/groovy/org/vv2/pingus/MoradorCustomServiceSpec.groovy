package org.vv2.pingus

import grails.testing.gorm.DomainUnitTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class MoradorCustomServiceSpec extends Specification implements ServiceUnitTest<MoradorCustomService>, DomainUnitTest<Morador> {

    def setup() {
        new Morador(nome: "Levi Kaue Jesus",                 rg: "24.222.979-7", apto: 101, inativo: true ).save()
        new Morador(nome: "Geraldo Benjamin Antonio Viana",  rg: "47.412.808-8", apto: 102, inativo: true).save()
        new Morador(nome: "Marcos Caue Felipe Moreira",      rg: "38.597.970-8", apto: 103, inativo: true).save()
        new Morador(nome: "Helena Rosa Costa",               rg: "50.835.610-6", apto: 104, inativo: false).save()
    }

    def cleanup() {
        Morador.deleteAll()
    }

    void "Test MoradorCustomService find inative"() {
        when:"Setup morador list"
        def list = MoradorCustomService.findAllActive()

        then: "Assert 3 falses"
        list.size() == 1
    }
}


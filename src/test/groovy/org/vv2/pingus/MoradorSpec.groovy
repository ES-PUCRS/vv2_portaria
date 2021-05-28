package org.vv2.pingus

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class MoradorSpec extends Specification implements DomainUnitTest<Morador> {

    def setup() {
        new Morador(
            nome: "Thomas Mateus da Rocha",
            rg: "35.482.513-6",
            apto: 204,
            inativo: false
        ).save(flush: true)
        new Morador(
            nome: "Thomas Bartolomeu",
            rg: "35.482.513-8",
            apto: 207,
            inativo: false
        ).save(flush: true)
    }

    def cleanup() {
        Morador.deleteAll()
    }

    void "Test create morador"() {
        when:"There is no change between "
        def count = Morador.count()

        new Morador(
            nome: "Thomas Nelson Mateus da Rocha",
            rg: "35.482.513-6",
            apto: 204,
            inativo: false
        ).save(flush: true)

        then:"The model is correct"
        Morador.count() == count + 1
    }

    void "Teste delete morador"() {
        when: "There is no change between"
        def count = Morador.count()

        Morador.findById(1).delete(flush: true)

        then: "The model is correct"
        Morador.count() == count - 1
    }

    void "Test count all"() {
        when:"There is no change between"

        then:"The model is correct"
        Morador.count() == 2
    }

    void "Test list by name"() {
        when:"There is no change between"

        then:"The model is correct"
        Morador.findByNome("Thomas Bartolomeu") != null
    }
}

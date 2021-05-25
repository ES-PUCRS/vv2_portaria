package org.vv2.pingus

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class EntregaCustomServiceSpec extends Specification implements ServiceUnitTest<EntregaCustomService>{

    def setup() {
        new Entrega(
                criado: sdf.parse("14/12/20 11:26"),
                descricao: "sedex cx 27x25x70cm",
                apto: 103,
                operador: new Operador(nome: "Jose Otavio da Mata")
        ).save(flush: true)
        new Entrega(
                criado: sdf.parse("16/12/20 12:26"),
                descricao: "sedex cx 27x25x70cm",
                apto: 101,
                operador: new Operador(nome: "Jose Otavio da Mata")
        ).save(flush: true)
    }

    def cleanup() {
    }

    void "Test service get"() {
        expect:"fix me"
            true == true
    }

}

//Entrega get(Serializable id)
//
//List<Entrega> list(Map args)
//
//Long count()
//
//void delete(Serializable id)
//
//Entrega save(Entrega entrega)
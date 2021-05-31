package org.vv2.pingus.unit

import grails.testing.gorm.DomainUnitTest
import org.vv2.pingus.Entrega
import org.vv2.pingus.Operador
import spock.lang.Specification

import java.text.SimpleDateFormat

class EntregaSpec extends Specification implements DomainUnitTest<Entrega> {

    static def sdf = new SimpleDateFormat("dd/MM/yy HH:mm")

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
        Entrega.deleteAll()
    }

//    void "Test create entrega"() {
//        when:"There is no change between "
//        def count = Entrega.count()
//
//        new Entrega(
//                criado: sdf.parse("16/12/20 12:26"),
//                descricao: "sedex cx 27x25x70cm",
//                apto: 101,
//                operador: new Operador(nome: "Jose Otavio da Mata")
//        ).save(flush: true)
//
//        then:"The model is correct"
//        Entrega.count() == count + 1
//    }

    void "Teste delete entrega"() {
        when:"There is no change between"
        def count = Entrega.count()

        Entrega.findById(1).delete(flush: true)

        then:"The model is correct"
        Entrega.count() == count - 1
    }
}

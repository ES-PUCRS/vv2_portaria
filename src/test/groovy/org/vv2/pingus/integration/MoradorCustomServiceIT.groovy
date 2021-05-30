package org.vv2.pingus.integration

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import groovy.time.TimeCategory
import org.vv2.pingus.Entrega
import org.vv2.pingus.Morador
import org.vv2.pingus.Operador
import spock.lang.Specification

import java.text.SimpleDateFormat

@Rollback
@Integration
class MoradorCustomServiceIT extends Specification {


    static def sdf = new SimpleDateFormat("dd/MM/yy HH:mm")

    private long setupData() {
        new Entrega(
                criado: sdf.parse(sdf.format(use (TimeCategory) { new Date() - 1.day })),
                descricao: "sedex cx 27x25x70cm",
                apto: 103,
                operador: new Operador(nome: "Jose Otavio da Mata")
        ).save(flush: true, failOnError: true)
        new Entrega(
                criado: sdf.parse(sdf.format(use (TimeCategory) { new Date() - 22.minute })),
                descricao: "sedex envelope",
                apto: 101,
                operador: new Operador(nome: "Jose da Mata")
        ).save(flush: true, failOnError: true)
        new Entrega(
                criado: sdf.parse(sdf.format(use (TimeCategory) { new Date() - 1.hour })),
                descricao: "sedex cx",
                apto: 101,
                operador: new Operador(nome: "Jose Otavio da Mata")
        ).save(flush: true, failOnError: true)

        def entrega = new Entrega(
                criado: sdf.parse(sdf.format(use (TimeCategory) { new Date() - 1.week })),
                descricao: "correios carta pequena",
                apto: 101,
                operador: new Operador(nome: "Jose Otavio da Mata"),
                retirado: sdf.parse(sdf.format(use (TimeCategory) { new Date() - 1.day })),
                morador: new Morador(nome: "Levi Kate Jesus", apto: 1, rg: "24.222.979-2", inativo: false)
        ).save(flush: true, failOnError: true)
        new Entrega(
                criado: sdf.parse(sdf.format(use (TimeCategory) { new Date() - 1.month })),
                descricao: "correios cx media",
                apto: 101,
                operador: new Operador(nome: "Jose Otavio da Mata"),
                retirado: sdf.parse(sdf.format(use (TimeCategory) { new Date() - 1.day })),
                morador: new Morador(nome: "Levi Kate Jesus", apto: 2, rg: "24.222.979-3", inativo: false)
        ).save(flush: true, failOnError: true)
        entrega?.id
    }

}
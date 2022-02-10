package org.vv2.pingus
import java.text.SimpleDateFormat

class BootStrap {

    def init = { servletContext ->
         println "Populating database ----------------------------------------------------------------------------------------"
         new Operador(nome: "Rebeca Adriana da Mota")        .save()
         new Operador(nome: "Daiane Evelyn Rita da Costa")   .save()
         new Operador(nome: "Benicio Danilo Arthur Barbosa") .save()
         new Operador(nome: "Jose Otavio da Mata")           .save()

         new Morador(nome: "Levi Kaue Jesus",                 rg: "24.222.979-7", apto: 101, inativo: true ).save()
         new Morador(nome: "Geraldo Benjamin Antonio Viana",  rg: "47.412.808-8", apto: 102, inativo: false).save()
         new Morador(nome: "Marcos Caue Felipe Moreira",      rg: "38.597.970-8", apto: 103, inativo: false).save()
         new Morador(nome: "Helena Rosa Costa",               rg: "50.835.610-6", apto: 104, inativo: false).save()
         new Morador(nome: "Davi Julio da Mota",              rg: "46.224.780-6", apto: 201, inativo: false).save()
         new Morador(nome: "Pedro Murilo Santos",             rg: "20.173.376-6", apto: 202, inativo: false).save()
         new Morador(nome: "Stefany Pietra Cavalcanti",       rg: "31.190.544-4", apto: 203, inativo: false).save()
         new Morador(nome: "Thomas Nelson Mateus da Rocha",   rg: "35.482.513-6", apto: 204, inativo: false).save()
         new Morador(nome: "Giovana Emilly Oliveira",         rg: "32.310.894-5", apto: 301, inativo: false).save()
         new Morador(nome: "Jennifer Alicia Vieira",          rg: "41.092.275-4", apto: 302, inativo: false).save()
         new Morador(nome: "Mateus Pietro Caue da Mota",      rg: "27.244.251-3", apto: 303, inativo: false).save()
         new Morador(nome: "Arthur Renan Pietro Melo",        rg: "34.971.550-6", apto: 304, inativo: false).save()
         new Morador(nome: "Aparecida Pietra Fogaca",         rg: "34.971.550-6", apto: 401, inativo: false).save()
         new Morador(nome: "Davi Benicio Jesus",              rg: "36.291.849-1", apto: 402, inativo: false).save()
         new Morador(nome: "Tereza Rosangela Milena Rezende", rg: "14.878.159-7", apto: 403, inativo: false).save()

         def sdf = new SimpleDateFormat("dd/MM/yy HH:mm")
         new Entrega(criado: sdf.parse("14/04/21 11:26"), descricao: "correios cx 10x25x30cm", apto: 101, operador: Operador.findByNome("Rebeca Adriana da Mota")        , retirado: sdf.parse("23/05/21 11:45"), morador: Morador.findByNome("Levi Kaue Jesus")).save(failOnError: true)
         new Entrega(criado: sdf.parse("14/12/20 11:26"), descricao: "sedex cx 27x25x70cm"   , apto: 103, operador: Operador.findByNome("Jose Otavio da Mata"))          .save(failOnError: true)
         new Entrega(criado: sdf.parse("20/05/21 11:26"), descricao: "correios carta pequena", apto: 101, operador: Operador.findByNome("Daiane Evelyn Rita da Costa")   , retirado: sdf.parse("26/05/21 08:16"), morador: Morador.findByNome("Levi Kaue Jesus")).save(failOnError: true)
         new Entrega(criado: sdf.parse("13/04/21 12:13"), descricao: "correios carta grande" , apto: 104, operador: Operador.findByNome("Rebeca Adriana da Mota")        , retirado: sdf.parse("12/05/21 11:32"), morador: Morador.findByNome("Helena Rosa Costa")).save(failOnError: true)
         new Entrega(criado: sdf.parse("18/05/20 16:30"), descricao: "sequoia cx 16x17x13cm" , apto: 101, operador: Operador.findByNome("Daiane Evelyn Rita da Costa")   , retirado: sdf.parse("07/11/20 13:15"), morador: Morador.findByNome("Levi Kaue Jesus")).save(failOnError: true)
         new Entrega(criado: sdf.parse("19/11/20 15:19"), descricao: "sedex cx 112x223x494cm", apto: 101, operador: Operador.findByNome("Benicio Danilo Arthur Barbosa")).save(failOnError: true)
         new Entrega(criado: sdf.parse("14/09/20 11:26"), descricao: "correios carta pequena", apto: 301, operador: Operador.findByNome("Benicio Danilo Arthur Barbosa")).save(failOnError: true)
         new Entrega(criado: sdf.parse("05/05/21 17:11"), descricao: "sedex cx 100x75x30cm"  , apto: 304, operador: Operador.findByNome("Daiane Evelyn Rita da Costa")   , retirado: sdf.parse("07/05/21 10:26"), morador: Morador.findByNome("Arthur Renan Pietro Melo")).save(failOnError: true)
    }

    def destroy = { }
}

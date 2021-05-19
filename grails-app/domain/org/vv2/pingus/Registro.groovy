package org.vv2.pingus

class Registro {

    Operador operador
    String   jSessionId

    static constraints = {
        jSessionId  nullable: false
        operador    nullable: false
    }

    @Override
    String toString(){
        operador.nome
    }
}

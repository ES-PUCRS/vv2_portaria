package org.vv2.pingus

class Operador {

    String nome

    static constraints = {
        nome nullable: false, matches: /[A-Za-z _]*[A-Za-z][A-Za-z _]*/
    }

    static mapping = {
        version false
    }

    @Override
    String toString(){
        nome
    }
}

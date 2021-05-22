package org.vv2.pingus

class Morador {

    String  nome
    String  rg
    Integer apto
    boolean inativo

    static constraints = {
        nome    nullable: false, matches: /[A-Za-z _]*[A-Za-z][A-Za-z _]*/
        rg      nullable: false, matches: /\d{2}.\d{3}.\d{3}-\d{1}|\d{9}/
        apto    nullable: false, min: 0
        inativo()
    }

    static mapping = {
        version false
    }

    @Override
    String toString(){
        nome
    }
}
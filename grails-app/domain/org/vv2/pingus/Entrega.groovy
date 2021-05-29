package org.vv2.pingus

class Entrega {

    Date        dateCreated // Grails create-date
    Date        lastUpdated // Grails update-date
    // BigDecimal id -> grails mapping
    Date        criado
    String      descricao
    Integer     apto
    Operador    operador
    Date        retirado
    Morador     morador

    static constraints = {
        dateCreated()
        lastUpdated()
        criado      nullable: true, display: false
        retirado    nullable: true, display: false
        descricao   nullable: false
        apto        nullable: false, min: 0
        operador    nullable: false
        morador     nullable: true
    }

    static mapping = {
        id generator:'native', params:[sequence:'ENTREGA_SEQ'], column: 'ENTREGA'
        version false
    }

}
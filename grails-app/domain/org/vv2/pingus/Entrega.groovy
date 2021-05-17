package org.vv2.pingus

class Entrega {

    Date        dateCreated
    Date        lastUpdated
    Date        criado
    String      descricao
    Integer     apto
    Operador    operador
    String      operadorNome
    Date        retirado
    Morador     morador


    static constraints = {
        dateCreated()
        lastUpdated()
        criado      nullable: true, display: false
        retirado    nullable: true, display: false
        descricao   nullable: false
        apto        nullable: false, min: 0
        operador     (nullable: true, validator: {val, obj ->
            if (!obj.operadorNome) return ['operador.required']
        })
        operadorNome display: false
        morador      nullable: true
    }

    static mapping = {
        id generator:'native', params:[sequence:'ENTREGA_SEQ'], column: 'ENTREGA'
        version false
    }

    static List<Entrega> findAllByDateCreatedGreaterThan(Date date) {
        Entrega.all.findAll {
            if(it.criado)
                it.criado.after date
        }
    }

    static Calendar findDeliveryAverageTime() {
        def list = Entrega.all.findAll{
            it?.morador
        }

        Long difference = 0L
        for(entrega in list){
            difference += entrega?.retirado?.getTime() - entrega?.criado?.getTime()
        }

        def dateDiff = new Date()
        dateDiff.setTime((difference / list.size()) as Long)
        def calendar = Calendar.getInstance()
        calendar.setTime(dateDiff)
        return calendar
    }
}
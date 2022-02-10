package org.vv2.pingus

import grails.gorm.transactions.Transactional

@Transactional
class MoradorCustomService {

    def findAllActive() {
        Morador.findAllWhere(inativo: false)
    }

    def findAllReceived(Morador morador) {
        Entrega.findAllByMorador(morador)
    }
}

package org.vv2.pingus

import grails.gorm.transactions.Transactional

@Transactional
class MoradorCustomService {

    def findAllActive() {
        Morador.findAllWhere(inativo: false)
    }

}

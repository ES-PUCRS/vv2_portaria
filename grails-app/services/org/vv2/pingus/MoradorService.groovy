package org.vv2.pingus

import grails.gorm.services.Service

@Service(Morador)
interface MoradorService {

    Morador get(Serializable id)

    List<Morador> list(Map args)

    Long count()

    void delete(Serializable id)

    Morador save(Morador morador)

}
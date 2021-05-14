package org.vv2.pingus

import grails.gorm.services.Service

@Service(Entrega)
interface EntregaService {

    Entrega get(Serializable id)

    List<Entrega> list(Map args)

    Long count()

    void delete(Serializable id)

    Entrega save(Entrega entrega)

}
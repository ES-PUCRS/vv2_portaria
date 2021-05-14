package org.vv2.pingus

import grails.gorm.services.Service

@Service(Operador)
interface OperadorService {

    Operador get(Serializable id)

    List<Operador> list(Map args)

    Long count()

    void delete(Serializable id)

    Operador save(Operador operador)

}
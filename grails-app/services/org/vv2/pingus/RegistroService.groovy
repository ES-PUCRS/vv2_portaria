package org.vv2.pingus

import grails.gorm.services.Service

@Service(Registro)
interface RegistroService {

    Registro get(Serializable id)

    List<Registro> list(Map args)

    Long count()

    void delete(Serializable id)

    Registro save(Registro registro)

}
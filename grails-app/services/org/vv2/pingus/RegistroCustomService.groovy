package org.vv2.pingus

import grails.gorm.transactions.Transactional

import javax.servlet.http.HttpServletRequest

@Transactional
class RegistroCustomService {

    def searchByCookieActive(HttpServletRequest request) {
        Registro.findWhere(
            jSessionId: request.getHeader("cookie").replace("JSESSIONID=","")
        )
    }
}

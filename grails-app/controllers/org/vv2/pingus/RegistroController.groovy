package org.vv2.pingus

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class RegistroController {

    RegistroService registroService
    OperadorService operadorService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond registroService.list(params), model:[registroCount: registroService.count()]
    }

    def show() {
        respond operadorService.list(), model:[operadorCount: operadorService.count()]
    }

    def edit(Long id) {
        def registro = new Registro(
            operador:   Operador.findById(id),
            jSessionId: request.getHeader("cookie").replace("JSESSIONID=","")
        )

        try {
            registroService.save(registro)
        } catch (ValidationException ignored) {
            respond registro.errors, view:'show'
            return
        }
        redirect controller: "entrega", action: "index"
    }
}

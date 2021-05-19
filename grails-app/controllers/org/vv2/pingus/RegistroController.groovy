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
        respond operadorService.list(), model:[registroCount: registroService.count()]
    }

    def edit(Long id) {
        def registro = new Registro(
            operador:   Operador.findById(id),
            jSessionId: request.getHeader("cookie").replace("JSESSIONID=","")
        )

        try {
            registroService.save(registro)
        } catch (ValidationException e) {
            respond registro.errors, view:'show'
            return
        }
        redirect controller: "entrega", action: "index"
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'registro.label', default: 'Registro'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

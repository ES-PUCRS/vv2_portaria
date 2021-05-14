package org.vv2.pingus

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class OperadorController {

    OperadorService operadorService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond operadorService.list(params), model:[operadorCount: operadorService.count()]
    }

    def show(Long id) {
        respond operadorService.get(id)
    }

    def create() {
        respond new Operador(params)
    }

    def save(Operador operador) {
        if (operador == null) {
            notFound()
            return
        }

        try {
            operadorService.save(operador)
        } catch (ValidationException e) {
            respond operador.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'operador.label', default: 'Operador'), operador.id])
                redirect operador
            }
            '*' { respond operador, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond operadorService.get(id)
    }

    def update(Operador operador) {
        if (operador == null) {
            notFound()
            return
        }

        try {
            operadorService.save(operador)
        } catch (ValidationException e) {
            respond operador.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'operador.label', default: 'Operador'), operador.id])
                redirect operador
            }
            '*'{ respond operador, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        try {
            operadorService.delete(id)
        } catch (Exception e) {
            return request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'error.deleted.message', args: [message(code: 'operador.label', default: 'Operador'), operadorService.get(id).nome])
                    redirect action:"show", method:"GET", id: id
                }
                '*'{ render status: NOT_ACCEPTABLE }
            }
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'operador.label', default: 'Operador'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'operador.label', default: 'Operador'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

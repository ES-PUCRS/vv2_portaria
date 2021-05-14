package org.vv2.pingus

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class MoradorController {

    MoradorService moradorService
    MoradorCustomService moradorCustomService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond moradorService.list(params), model:[moradorCount: moradorService.count()]
    }

    def show(Long id) {
        respond moradorService.get(id)
    }

    def create() {
        respond new Morador(params)
    }

    def save(Morador morador) {
        if (morador == null) {
            notFound()
            return
        }

        try {
            moradorService.save(morador)
        } catch (ValidationException e) {
            respond morador.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'morador.label', default: 'Morador'), morador.id])
                redirect morador
            }
            '*' { respond morador, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond moradorService.get(id)
    }

    def update(Morador morador) {
        if (morador == null) {
            notFound()
            return
        }

        try {
            moradorService.save(morador)
        } catch (ValidationException e) {
            respond morador.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'morador.label', default: 'Morador'), morador.id])
                redirect morador
            }
            '*'{ respond morador, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        try {
            moradorService.delete(id)
        } catch (Exception e) {
            return request.withFormat {
                 form multipartForm {
                     flash.message = message(code: 'error.deleted.message', args: [message(code: 'morador.label', default: 'Morador'), moradorService.get(id).nome])
                     redirect action:"show", method:"GET", id: id
                 }
                 '*'{ render status: NOT_ACCEPTABLE }
            }
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'morador.label', default: 'Morador'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'morador.label', default: 'Morador'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

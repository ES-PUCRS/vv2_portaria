package org.vv2.pingus

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class EntregaController {

    EntregaService entregaService
    EntregaCustomService entregaCustomService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def filter(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        if ((! params.searchfor || params.searchfor.isEmpty() )  &&
            ( "RETIRADA" != (params.searchfield as String)       &&
              "MORADOR"  != (params.searchfield as String)       )) {
            redirect(action: "index")
        } else {
            def list = entregaCustomService.searchBy(params.searchfield as String, params.searchfor)
            render(view: "/entrega/index", model: [entregaList: list, entregaCount: list?.size()?:0, searchfield: params.searchfield])
        }
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond entregaService.list(params), model:[entregaCount: entregaService.count()]
    }

    def show(Long id) {
        respond entregaService.get(id)
    }

    def create() {
        respond new Entrega(params)
    }

    def save(Entrega entrega) {
        if (entrega == null) {
            notFound()
            return
        }

        try {
            if(!entrega.criado)
                entrega.criado = entrega.dateCreated
            entrega.operadorNome = entrega.operador?.nome
            entrega.lastUpdated = null;
            entregaService.save(entrega)
        } catch (ValidationException e) {
            respond entrega.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'entrega.label', default: 'Entrega'), entrega.id])
                redirect entrega
            }
            '*' { respond entrega, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond entregaService.get(id), model: [morador: Morador.findByNome("Giovana Emilly Oliveira")]
    }

    def update(Entrega entrega) {
        if (entrega == null) {
            notFound()
            return
        }

        if(entrega.morador?.id)
            entrega.operador = null


        try {
            entrega.retirado = entrega.lastUpdated
            entregaService.save(entrega)
        } catch (ValidationException e) {
            respond entrega.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'entrega.label', default: 'Entrega'), entrega.id])
                redirect entrega
            }
            '*'{ respond entrega, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        entregaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'entrega.label', default: 'Entrega'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'entrega.label', default: 'Entrega'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

package org.vv2.pingus

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class EntregaController {

    EntregaService entregaService
    EntregaCustomService entregaCustomService
    RegistroCustomService registroCustomService
    RegistroService registroService

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
        if(request.session?.isNew() || !registroCustomService.searchByCookieActive(request))
            redirect(controller:"registro", action: "show")

        params.max = Math.min(max ?: 10, 100)
        respond entregaService.list(params), model:[entregaCount: entregaService.count()]
    }

    def show(Long id) {
        respond entregaService.get(id)
    }

    def create() {
        def entrega = new Entrega(params)
        def registro = registroCustomService?.searchByCookieActive(request)

        if (!registro || request.session?.isNew())
            redirect controller:"registro", action: "show"

        entrega.operador = registro?.operador
        respond entrega
    }

    def save(Entrega entrega) {
        if (entrega == null) {
            notFound()
            return
        }

        if(!entrega.operador) {
            def registro = registroCustomService?.searchByCookieActive(request)
            entrega.operador = registro?.operador
        }

        try {
            entrega.criado = entrega.dateCreated
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
        respond entregaService.get(id)
    }

    def update(Entrega entrega) {
        if (entrega == null) {
            notFound()
            return
        }

        try {
            entregaCustomService.save(entrega)
        } catch (IllegalArgumentException ignored) {
            unprocessableEntity()
            return
        } catch (ValidationException ignored) {
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

    def logout() {
        def registro = registroCustomService.searchByCookieActive(request)

        try {
            registro.jSessionId = "${registro.jSessionId}{LOGOUT}"
            registroService.save(registro)
        } catch (ValidationException e) {
            respond registro.errors, view:'index'
            return
        }
        redirect controller: "registro", action: "show"
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

    protected void unprocessableEntity() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.unprocessable.entity.message', args: [message(code: 'entrega.label', default: 'Entrega'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: UNPROCESSABLE_ENTITY }
        }
    }
}

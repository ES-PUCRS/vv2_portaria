package org.vv2.pingus

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class EntregaController {

    EntregaService entregaService
    EntregaCustomService entregaCustomService
    RegistroCustomService registroCustomService
    RegistroService registroService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    private def registroAtivo() {
        if (request?.session?.isNew()) return null
        registroCustomService?.searchByCookieActive(request)
    }

    def filter(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        String field = (params.searchfield as String)?.trim()
        String search = (params.searchfor as String)?.trim()
        final Set<String> permitidos = ["RETIRADA", "MORADOR"] as Set

        if (!search || !field || !permitidos.contains(field?.toUpperCase())) {
            redirect(action: "index"); return
        }

        def list = entregaCustomService.searchBy(field, search)
        render(view: "/entrega/index",
               model: [entregaList: list, entregaCount: (list?.size() ?: 0), searchfield: field])
    }

    def index(Integer max) {
        if (!registroAtivo()) { redirect(controller: "registro", action: "show"); return }
        params.max = Math.min(max ?: 10, 100)
        respond entregaService.list(params), model: [entregaCount: entregaService.count()]
    }

    def show(Long id) {
        def entrega = entregaService.get(id)
        if (!entrega) { notFound(); return }
        respond entrega
    }

    def create() {
        def registro = registroAtivo()
        if (!registro) { redirect(controller: "registro", action: "show"); return }

        def entrega = new Entrega(params)
        entrega.operador = registro?.operador
        respond entrega
    }

    def save(Entrega entrega) {
        if (!entrega) { notFound(); return }

        if (!entrega.operador) {
            def registro = registroAtivo()
            if (!registro) { redirect(controller: "registro", action: "show"); return }
            entrega.operador = registro?.operador
        }

        try {

            if (!entrega.criado) entrega.criado = new Date()
            entregaService.save(entrega)
        } catch (ValidationException e) {
            respond entrega.errors, view: 'create'; return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message',
                    args: [message(code: 'entrega.label', default: 'Entrega'), entrega.id])
                redirect entrega
            }
            '*' { respond entrega, [status: CREATED] }
        }
    }

    def edit(Long id) {
        def entrega = entregaService.get(id)
        if (!entrega) { notFound(); return }
        respond entrega
    }

    def update(Entrega entrega) {
        if (!entrega) { notFound(); return }

        try {

            entregaService.save(entrega)
        } catch (IllegalArgumentException ignored) {
            unprocessableEntity(); return
        } catch (ValidationException ignored) {
            respond entrega.errors, view: 'edit'; return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message',
                    args: [message(code: 'entrega.label', default: 'Entrega'), entrega.id])
                redirect entrega
            }
            '*' { respond entrega, [status: OK] }
        }
    }

    def logout() {
        def registro = registroCustomService?.searchByCookieActive(request)

        try {
            request.session?.invalidate()
            if (registro) {
                registro.jSessionId = "${registro.jSessionId}{LOGOUT}"
                registroService.save(registro)
            }
        } catch (ValidationException e) {
            if (registro?.errors) {
                respond registro.errors, view: 'index'
                return
            }
        }
        redirect(controller: "registro", action: "show")
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message',
                    args: [message(code: 'entrega.label', default: 'Entrega'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    protected void unprocessableEntity() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.unprocessable.entity.message',
                    args: [message(code: 'entrega.label', default: 'Entrega'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: UNPROCESSABLE_ENTITY }
        }
    }
}

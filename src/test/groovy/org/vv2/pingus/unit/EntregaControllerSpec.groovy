package org.vv2.pingus.unit

import grails.testing.web.controllers.ControllerUnitTest
import grails.validation.ValidationException
import grails.testing.gorm.DomainUnitTest
import groovy.time.TimeCategory
import org.vv2.pingus.Entrega
import org.vv2.pingus.EntregaController
import org.vv2.pingus.EntregaCustomService
import org.vv2.pingus.EntregaService
import org.vv2.pingus.Morador
import org.vv2.pingus.Operador
import spock.lang.*

import java.text.SimpleDateFormat

class EntregaControllerSpec extends Specification implements ControllerUnitTest<EntregaController>, DomainUnitTest<Entrega> {

    static def sdf = new SimpleDateFormat("dd/MM/yy HH:mm")

    def populateValidParams(params) {
        params["descricao"] = "TestSpec"
        params["operador"]  = 1
        params["apto"]      = 404

        assert params != null
    }

    void "Test the index action returns the correct model"() {
        given:
        controller.entregaService = Mock(EntregaService) {
            1 * list(_) >> []
            1 * count() >> 0
        }

        when:"The index action is executed"
        controller.index()

        then:"The model is correct"
        !model.entregaList
        model.entregaCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
        controller.create()

        then:"The model is correctly created"
        model.entrega != null
    }

    void "Test the save action with a null instance"() {
        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        controller.save(null)

        then:"A 404 error is returned"
        response.redirectedUrl == '/entrega/index'
        flash.message != null
    }

    void "Test the save action correctly persists"() {
        given:
        controller.entregaService = Mock(EntregaService) {
            1 * save(_ as Entrega)
        }

        when:"The save action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        populateValidParams(params)
        def entrega = new Entrega(params)
        entrega.id = 1

        controller.save(entrega)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/entrega/show/1'
        controller.flash.message != null
    }

    void "Test the save action with an invalid instance"() {
        given:
        controller.entregaService = Mock(EntregaService) {
            1 * save(_ as Entrega) >> { Entrega entrega ->
                throw new ValidationException("Invalid instance", entrega.errors)
            }
        }

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def entrega = new Entrega()
        controller.save(entrega)

        then:"The create view is rendered again with the correct model"
        model.entrega != null
        view == 'create'
    }

    void "Test the show action with a null id"() {
        given:
        controller.entregaService = Mock(EntregaService) {
            1 * get(null) >> null
        }

        when:"The show action is executed with a null domain"
        controller.show(null)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the show action with a valid id"() {
        given:
        controller.entregaService = Mock(EntregaService) {
            1 * get(2) >> new Entrega()
        }

        when:"A domain instance is passed to the show action"
        controller.show(2)

        then:"A model is populated containing the domain instance"
        model.entrega instanceof Entrega
    }

    void "Test the edit action with a null id"() {
        given:
        controller.entregaService = Mock(EntregaService) {
            1 * get(null) >> null
        }

        when:"The show action is executed with a null domain"
        controller.edit(null)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the edit action with a valid id"() {
        given:
        controller.entregaService = Mock(EntregaService) {
            1 * get(2) >> new Entrega()
        }

        when:"A domain instance is passed to the show action"
        controller.edit(2)

        then:"A model is populated containing the domain instance"
        model.entrega instanceof Entrega
    }


    void "Test the update action with a null instance"() {
        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then:"A 404 error is returned"
        response.redirectedUrl == '/entrega/index'
        flash.message != null
    }

    void "Test the update action correctly persists"() {
        given:
        controller.entregaCustomService = Mock(EntregaCustomService) {
            1 * save(_ as Entrega)
        }

        when:"The save action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        populateValidParams(params)
        def entrega = new Entrega(
            criado: sdf.parse(sdf.format(use (TimeCategory) { new Date() - 1.day })),
            descricao: "sedex cx 27x25x70cm",
            apto: 103,
            operador: new Operador(nome: "Jose Otavio da Mata"),
            morador: new Morador(nome: "Tavares Rodrigo", rg: '000000000', apto: 900 ,inativo: false)
        )
        entrega.id = 1

        controller.update(entrega)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/entrega/show/1'
        controller.flash.message != null
    }

    void "Test the update action with an invalid instance"() {
        given:
        controller.entregaCustomService = Mock(EntregaCustomService) {
            1 * save(_ as Entrega) >> { Entrega entrega ->
                throw new ValidationException("Invalid instance", entrega.errors)
            }
        }

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(new Entrega())

        then:"The edit view is rendered again with the correct model"
        model.entrega != null
        view == 'edit'
    }

}







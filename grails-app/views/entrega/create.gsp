<%@ page import="org.vv2.pingus.Operador" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'entrega.label', default: 'Entrega')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-entrega" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="create-entrega" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.entrega}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.entrega}" var="error">
                    <li <g:if test="${error in org.springframework.validation.FieldError}">
                            data-field-id="${error.field}"
                    </g:if>>
                    </li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.entrega}" method="POST">
                <fieldset class="form">
                    <f:all style="text-align: center" bean="entrega" except="['morador', 'operador']"/>
                    <div class="fieldcontain required">
                        <label for="operador">
                            Operador
                        </label>
                    <input disabled value="${this?.entrega?.operador}"/>
                    </div>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>

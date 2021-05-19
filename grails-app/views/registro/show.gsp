<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'registro.label', default: 'Registro')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-registro" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div role="navigation">
            <ul>
                <a><g:message code="Selecionar operador atual"/></a>
            </ul>
        </div>
        <div id="show-registro" class="content scaffold-show" role="main">
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <table>
                <tr>
                    <th>Operador</th>
                </tr>
                <g:each status="i" in="${operadorList}" var="item">
                    <tr>
                        <td><g:link class="edit" action="edit" controller="registro" resource="registro" id="${item?.id}" ><g:message code="${item}" /></g:link></td>
                    </tr>
                </g:each>
            </table>

        </div>

    </body>
</html>

<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'entrega.label', default: 'Entrega')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-entrega" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
%{--                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>--}%
                <li><g:link class="create" action="create"><g:message code="default.new.delivery" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-entrega" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
%{--            <f:display bean="entrega" />--}%
            <table>
                <tr>
                    <th>ENTREGA</th>
                    <th>RECEBIDO</th>
                    <th>DESCRICAO</th>
                    <th>APTO</th>
                    <th>OPERADOR</th>
                    <th>RETIRADO</th>
                    <th>MORADOR</th>
                </tr>
                <g:each status="i" in="${entrega}" var="item">
                    <tr>
                        <td><g:link resource="entrega" action="show" id="${item?.id}">${item?.id}</g:link></td>
                        <g:if test="${item?.criado}">
                            <td> <g:formatDate date="${item?.criado}"/> </td>
                        </g:if>
                        <g:else>
                            <td> <g:formatDate date="${item?.dateCreated}"/> </td>
                        </g:else>
                        <td>${item?.descricao}</td>
                        <td>${item?.apto}</td>
                        <g:if test="${item?.operador}">
                            <td><g:link resource="operador" action="show" id="${item?.operador.id}">${item?.operador}</g:link></td>
                        </g:if>
                        <g:else>
                            <td>${item?.operadorNome}</td>
                        </g:else>
                        <g:if test="${item?.morador?.id}">
                            <g:if test="${item?.retirado}">
                                <td> <g:formatDate date="${item?.retirado}"/> </td>
                            </g:if>
                            <g:else>
                                <td> <g:formatDate date="${item?.lastUpdated}"/></td>
                            </g:else>
                        </g:if>
                        <td><g:link resource="morador" action="show" id="${item?.morador?.id}">${item?.morador}</g:link></td>
                    </tr>
                </g:each>
            </table>
            <g:form resource="${this.entrega}" method="DELETE">
                <fieldset class="buttons">
                    <g:if test="${!this.entrega?.morador}">
                        <g:link class="edit" action="edit" resource="${this.entrega}"><g:message code="default.button.withdraw.label" default="Edit" /></g:link>
                    </g:if>
%{--                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />--}%
                </fieldset>
            </g:form>
        </div>
    </body>
</html>

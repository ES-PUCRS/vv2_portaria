<%@ page import="org.vv2.pingus.SearchFields; groovy.time.TimeCategory; org.vv2.pingus.Operador; org.vv2.pingus.Entrega; java.text.SimpleDateFormat; org.vv2.pingus.OperadorController" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'entrega.label', default: 'Entrega')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <content tag="nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Controllers<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                        <li class="controller"><g:link controller="${c.logicalPropertyName}">${c.name}</g:link></li>
                    </g:each>
                </ul>
            </li>
        </content>
        <a href="#list-entrega" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="Nova entrega" args="[entityName]" /></g:link></li>
                <div class="search-block">
                    <g:form action="filter">
                        <li style="color: #666666; font-size: 14px">Pesquisar por: </li>
                        <li><g:select name="searchfield" from="${SearchFields.values()}" optionKey="name"/></li>
                        <li><g:field class="search-field" type="searchfor" name="searchfor"/></li>
                    </g:form>
                </div>
            </ul>
            <li><g:link resource="entrega" action="logout"><g:message code="Logout" /></g:link></li>
        </div>
        <div id="list-entrega" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table>
                <tr>
                    <th>ENTREGA</th>
                    <th>DATA/HORA</th>
                    <th>DESCRIÇÃO</th>
                    <th>APTO</th>
                    <th>OPERADOR</th>
                    <th>RETIRADA</th>
                    <th>MORADOR</th>
                </tr>
                <g:each status="i" in="${entregaList}" var="item">
                    <tr>
                        <td><g:link resource="entrega" action="show" id="${item?.id}">${item?.id}</g:link></td>
                        <g:if test="${item?.criado}">
                            <td> <g:formatDate date="${item?.criado}"/> </td>
                        </g:if>
                        <g:else>
                            <td> <g:formatDate date="${item?.dateCreated}"/></td>
                        </g:else>
                        <td>${item?.descricao}</td>
                        <td>${item?.apto}</td>
                        <td><g:link resource="operador" action="show" id="${item?.operador?.id}">${item?.operador}</g:link></td>
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

            <div class="pagination">
                    <g:paginate total="${entregaCount ?: 0}" />
                <div class="dashboard-block">
                    <p>Recebidos nos últimos 30 dias:</p>
                        <g:textField disabled="true" name="fieldByTime" class="dashboard-field" value="${Entrega.findAllByDateCreatedGreaterThan(use (TimeCategory) { new Date() - 1.month }).size()}"/>
                    <p style="margin-left: 20px">Não retiradas:</p>
                        <g:textField disabled="true" name="fieldUndelivered" class="dashboard-field" value="${Entrega.findAllWhere(morador: null).size()}"/>
                    <p style="margin-left: 20px">Tempo médio entre registro e retirada:</p>
                    <g:set class="dashboard-average-field" var="dateAverage" value="${Entrega.findDeliveryAverageTime()}" />
                        <g:textField disabled="true" name="fieldAverage" class="dashboard-average-field" value="${dateAverage.get(Calendar.DAY_OF_YEAR)}D ${dateAverage.get(Calendar.HOUR_OF_DAY)}h ${dateAverage.get(Calendar.MINUTE)}m"/>
                </div>
            </div>
        </div>
    </body>
</html>
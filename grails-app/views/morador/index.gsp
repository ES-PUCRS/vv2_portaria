<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'morador.label', default: 'Morador')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <content tag="nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Controllers<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                        <g:if test="${c?.name != 'Registro'}">
                            <li class="controller"><g:link controller="${c.logicalPropertyName}">${c.name}</g:link></li>
                        </g:if>
                    </g:each>
                </ul>
            </li>
        </content>
        <a href="#list-morador" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-morador" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${moradorList}" />

            <div class="pagination">
                <g:paginate total="${moradorCount ?: 0}" />
            </div>
        </div>
    </body>
</html>
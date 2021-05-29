<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>VV2 Portaria</title>
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

<div id="content" role="main">
    <section class="row colset-2-its">


    </section>
</div>

</body>
</html>

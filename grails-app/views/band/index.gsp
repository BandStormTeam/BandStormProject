
<%@ page import="bandstorm.Band" %>
<!DOCTYPE html>
<html>
	<head>
<meta name="layout" content="main">
<g:set var="entityName" value="${message(code: 'band.label', default: 'Band')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
<style>
.centered ul li {
    display: inline;
    margin: 3px;
}
.pagination {
    margin: 2px;
    box-sizing: border-box;
}

.prev, .next {
    border: solid 2px deepskyblue;
    background: whitesmoke;
    border-radius: 5px;
    padding: 4px;
    box-sizing: inherit;
}

.next {
    padding: 4.5px 10px 4.5px 10px;
}

.centered ul a {
    color: black;
    box-sizing: inherit;
}

.centered ul a:hover {
    text-decoration: none;
}

.alert {
    margin-top: 5px;
}

.tab-content {
    background-color: whitesmoke;
    padding: 10px;
    padding-top:30px ;
    margin-bottom: 30px;
}
</style>
</head>
</head>
<body>
<div class="container centered" style=" padding-top:30px;width: 90%; min-height: 300px; background-color: rgba(245,245,245,0.95);">
    <div class="container-fluid">
        <h2>Mes groupes</h2>
        <ul class="nav nav-tabs nav-justified">
            <li class="active"><a data-toggle="tab" href="#view">Liste de groupes</a></li>
            <li><a data-toggle="tab" href="#create">Créer un nouveau groupe</a></li>
        </ul>

        <div class="tab-content">
            <div id="view" class="tab-pane fade in active">
                <div class="table-responsive" id="listedBands">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Nom</th>
                            <th>Date de création</th>
                            <th>Lieu</th>
                            <th>Description</th>
                        </tr>
                        </thead>
                        <tbody>
                        <g:each in="${bandInstanceList}" var="bandInstance">
                            <tr>
                                <td><a type="button" data-toggle="modal" data-target="#myModal">${fieldValue(bean: bandInstance, field: "name")}</a></td>
                                <g:render template="modal" model="[bandInstance: bandInstance]"></g:render>
                                <td><g:formatDate date="${bandInstance.dateCreated}" /></td>

                                <td>${fieldValue(bean: bandInstance, field: "address")}</td>

                                <td>${fieldValue(bean: bandInstance, field: "description")}</td>
                            </tr>
                        </g:each>
                        </tbody>
                    </table>

                    <g:paginate total="${bandInstanceCount?:0}" />

                </div>
            </div>
            <div id="create" class="tab-pane fade">
                <g:form >
                    <fieldset class="form" id="creationForm">
                        <g:render template="form" bean="${bandInstance}"/>
                    </fieldset>
                </g:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
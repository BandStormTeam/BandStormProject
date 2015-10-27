
<%@ page import="bandstorm.Event" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
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
	<body>
		<div class="container centered" style=" padding-top:30px;width: 90%; min-height: 300px; background-color: rgba(245,245,245,0.95);">
            <div class="container-fluid">
                <h2>Mes Événements</h2>
                <ul class="nav nav-tabs nav-justified">
                    <li class="active"><a data-toggle="tab" href="#view">Liste d'évènements</a></li>
                    <li><a data-toggle="tab" href="#create">Créer un nouvel évènement</a></li>
                </ul>

                <div class="tab-content">
                    <div id="view" class="tab-pane fade in active">
                        <div class="table-responsive" id="listedEvents">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th>Nom</th>
                                    <th>Date de l'évènement</th>
                                    <th>Date de création</th>
                                    <th>Adresse</th>
                                    <th>Description</th>
                                </tr>
                                </thead>
                                <tbody>
                                <g:each in="${eventInstanceList}" var="eventInstance">
                                    <tr>
                                        <td><a type="button" data-toggle="modal" data-target="#myModal">${fieldValue(bean: eventInstance, field: "name")}</a></td>
                                        <!-- Modal -->
                                        <div class="modal fade" id="myModal" role="dialog">
                                            <div class="modal-dialog">
                                                <!-- Modal content-->
                                                <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                    <h4 class="modal-title">Détail</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <dl class="dl-horizontal">
                                                        <g:if test="${eventInstance?.name}">
                                                            <dt>Nom</dt>
                                                            <dd><g:fieldValue bean="${eventInstance}" field="name"/></dd>
                                                        </g:if>

                                                        <g:if test="${eventInstance?.dateEvent}">
                                                            <dt>Date de l'évènement</dt>
                                                            <dd><g:fieldValue bean="${eventInstance}" field="dateEvent"/></dd>
                                                        </g:if>

                                                        <g:if test="${eventInstance?.dateCreated}">
                                                            <dt>Date de création</dt>
                                                            <dd><g:fieldValue bean="${eventInstance}" field="dateCreated"/></dd>
                                                        </g:if>

                                                        <g:if test="${eventInstance?.address}">
                                                            <dt>Adresse</dt>
                                                            <dd><g:fieldValue bean="${eventInstance}" field="address"/></dd>
                                                        </g:if>

                                                        <g:if test="${eventInstance?.description}">
                                                            <dt>Description</dt>
                                                            <dd><g:fieldValue bean="${eventInstance}" field="description"/></dd>
                                                        </g:if>

                                                        <g:if test="${eventInstance?.tags}">
                                                            <dt>Tags</dt>
                                                            <dd><g:fieldValue bean="${eventInstance}" field="address"/></dd>
                                                        </g:if>
                                                    </dl>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                </div>
                                                </div>
                                            </div>
                                        </div>

                                        <td><g:formatDate date="${eventInstance.dateEvent}"/></td>

                                        <td><g:formatDate date="${eventInstance.dateCreated}" /></td>

                                        <td>${fieldValue(bean: eventInstance, field: "address")}</td>

                                        <td>${fieldValue(bean: eventInstance, field: "description")}</td>
                                    </tr>
                                </g:each>
                                </tbody>
                            </table>

                            <g:paginate total="${eventInstanceCount}" />

                        </div>
                    </div>
                    <div id="create" class="tab-pane fade">
                        <g:form >
                            <fieldset class="form" id="creationForm">
                                <g:render template="form" bean="${eventInstance}"/>
                            </fieldset>
                        </g:form>
                    </div>
                </div>
            </div>
		</div>
	</body>
</html>

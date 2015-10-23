
<%@ page import="bandstorm.Event" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
        <style>
            ul li {
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

            ul a {
                color: black;
                box-sizing: inherit;
            }

            ul a:hover {
                text-decoration: none;
            }

            .alert {
                margin-top: 5px;
            }
        </style>
	</head>
	<body>
		<div class="container centered" style=" padding-top:30px;width: 90%; min-height: 300px; background-color: rgba(245,245,245,0.95);">
            <div class="container-fluid">
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingOne">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    My Events
                                </a>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                            <div class="panel-body">
                                <div class="table-responsive" id="listedEvents">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>Event Name</th>
                                                <th>Date Event</th>
                                                <th>Created</th>
                                                <th>Address</th>
                                                <th>Description</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <g:each in="${eventInstanceList}" var="eventInstance">
                                                <tr>
                                                    <td><g:remoteLink action="show" id="${(eventInstance.id)}">${fieldValue(bean: eventInstance, field: "name")}</g:remoteLink></td>

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
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingTwo">
                            <h4 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                    Add New Events
                                </a>
                            </h4>
                        </div>
                        <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                            <div class="panel-body">
                                <g:form >
                                    <fieldset class="form" id="creationForm">
                                        <g:render template="form" bean="${eventInstance}"/>
                                    </fieldset>
                                </g:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
		</div>
	</body>
</html>

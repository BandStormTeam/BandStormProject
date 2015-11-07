<%@ page import="bandstorm.Band" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'band.label', default: 'Band')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-band" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-band" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list band">
			
				<g:if test="${bandInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="band.name.label" default="Nom" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${bandInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bandInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="band.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${bandInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${bandInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="band.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${bandInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bandInstance?.address}">
				<li class="fieldcontain">
					<span id="address-label" class="property-label"><g:message code="band.address.label" default="Address" /></span>
					
						<span class="property-value" aria-labelledby="address-label"><g:fieldValue bean="${bandInstance}" field="address"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:bandInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${bandInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>

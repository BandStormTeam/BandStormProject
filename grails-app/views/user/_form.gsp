<%@ page import="bandstorm.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="user.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${userInstance?.username}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="user.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="email" required="" value="${userInstance?.email}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="user.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" required="" value="${userInstance?.firstName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="user.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" required="" value="${userInstance?.lastName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'country', 'error')} required">
	<label for="country">
		<g:message code="user.country.label" default="Country" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="country" required="" value="${userInstance?.country}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'birthDate', 'error')} required">
	<label for="birthDate">
		<g:message code="user.birthDate.label" default="Birth Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="birthDate" precision="day"  value="${userInstance?.birthDate}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'posts', 'error')} ">
	<label for="posts">
		<g:message code="user.posts.label" default="Posts" />
		
	</label>
	<g:select name="posts" from="${bandstorm.Status.list()}" multiple="multiple" optionKey="id" size="5" value="${userInstance?.posts*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'interests', 'error')} ">
	<label for="interests">
		<g:message code="user.interests.label" default="Interests" />
		
	</label>
	<g:select name="interests" from="${bandstorm.Tag.list()}" multiple="multiple" optionKey="id" size="5" value="${userInstance?.interests*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'participates', 'error')} ">
	<label for="participates">
		<g:message code="user.participates.label" default="Participates" />
		
	</label>
	<g:select name="participates" from="${bandstorm.Event.list()}" multiple="multiple" optionKey="id" size="5" value="${userInstance?.participates*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'manages', 'error')} ">
	<label for="manages">
		<g:message code="user.manages.label" default="Manages" />
		
	</label>
	<g:select name="manages" from="${bandstorm.Event.list()}" multiple="multiple" optionKey="id" size="5" value="${userInstance?.manages*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'groupsFollowed', 'error')} ">
	<label for="groupsFollowed">
		<g:message code="user.groupsFollowed.label" default="Groups Followed" />
		
	</label>
	<g:select name="groupsFollowed" from="${bandstorm.Band.list()}" multiple="multiple" optionKey="id" size="5" value="${userInstance?.groupsFollowed*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="user.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${userInstance?.password}"/>

</div>


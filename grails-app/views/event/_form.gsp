<%@ page import="bandstorm.Event" %>



<div class="form-group">
	<label for="EventName">Name</label>
	<g:textField name="evName" class="form-control" maxlength="35" id="EventName" placeholder="name"
				 value="${eventInstance?.name}"/>
	<g:hasErrors bean="${eventInstance}" field="name">
		<div class="alert alert-danger alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
					aria-hidden="true">&times;</span></button>
			<strong>You must specify a name wiht a length between 3 and 35 characters</strong>
		</div>
	</g:hasErrors>
</div>

<div class="form-group">
	<label for="eventAddress">Address</label>
	<g:textField name="evAddress" class="form-control ${hasErrors(bean: eventInstance, field: 'address', 'error')}"
				 maxlength="200" id="eventAddress" placeholder="address" value="${eventInstance?.address}"/>
	<g:hasErrors bean="${eventInstance}" field="address">

		<div class="alert alert-danger alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
					aria-hidden="true">&times;</span></button>
			<strong>You must specify an address with a length between 3 and 200 characters</strong>
		</div>
	</g:hasErrors>
</div>

<div class="form-group">
	<label for="eventDescription">Description</label>
	<g:textArea rows="5" name="evDescription"
				class="form-control ${hasErrors(bean: eventInstance, field: 'description', 'error')}"
				id="eventDescription" placeholder="describe your event" value="${eventInstance?.description}"/>
	<g:hasErrors bean="${eventInstance}" field="address">
		<div class="alert alert-danger alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
					aria-hidden="true">&times;</span></button>
			<strong>You must specify a description of at least 2 characters</strong>
		</div>
	</g:hasErrors>
</div>

<div class="form-group">
	<label for="eventTags">Tags</label>
	<g:textField name="evTags" class="form-control" id="eventTags"/>
</div>



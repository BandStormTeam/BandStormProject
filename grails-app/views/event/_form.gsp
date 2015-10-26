<%@ page import="bandstorm.Event" %>

<div class="form-group">
	<div class="row">
		<div class="col-md-6">
			<label for="EventName">Nom</label>
			<g:textField name="evName" class="form-control" maxlength="35" id="EventName" placeholder="Nom"
						 value="${eventInstance?.name}"/>
			<g:hasErrors bean="${eventInstance}" field="name">
				<div class="alert alert-danger alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
							aria-hidden="true">&times;</span></button>
					<strong>Le nom doit être compris entre 3 et 35 caractères</strong>
				</div>
			</g:hasErrors>
		</div>

		<div class="col-md-6">
			<label for="EventDate">Date de l'évènement</label>
			<g:textField class="form-control" id="EventDate" name="evDate" value="${eventInstance.dateEvent.format('dd/MM/yyyy')}" placeholder="DD/MM/YYYY"/>
			<g:hasErrors bean="${eventInstance}" field="dateEvent">
				<div class="alert alert-danger alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
							aria-hidden="true">&times;</span></button>
					<strong>Vous devez indiquer une date postérieure à aujourd'hui au format DD/MM/YYYY</strong>
				</div>
			</g:hasErrors>
		</div>
	</div>
</div>

<div class="form-group">
	<label for="eventAddress">Adresse</label>
	<g:textField name="evAddress" class="form-control"
				 maxlength="200" id="eventAddress" placeholder="Adresse" value="${eventInstance?.address}"/>
	<g:hasErrors bean="${eventInstance}" field="address">

		<div class="alert alert-danger alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
					aria-hidden="true">&times;</span></button>
			<strong>Indiquez une adresse (entre 10 et 200 caractères)</strong>
		</div>
	</g:hasErrors>
</div>

<div class="form-group">
	<label for="eventDescription">Description</label>
	<g:textArea rows="5" name="evDescription" class="form-control" id="eventDescription" placeholder="Décrivez votre évènement" value="${eventInstance?.description}"/>
	<g:hasErrors bean="${eventInstance}" field="description">
		<div class="alert alert-danger alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
					aria-hidden="true">&times;</span></button>
			<strong>Donnez une description</strong>
		</div>
	</g:hasErrors>
</div>

<div class="form-group">
	<label for="eventTags">Tags</label>
	<g:textField name="evTags" class="form-control" id="eventTags" placeholder="Ajoutez des tags en les séparant par un point-virgule"/>
</div>

<div>
	<h1 style="display: none" id="head">${(status) ? status :'empty'}</h1>
</div>

<g:submitToRemote class="btn btn-primary" url="[resource:eventInstance, controller:'event', action:'save']" update="creationForm" onSuccess="if(document.getElementById('head').innerHTML == 'OK'){swal({title:'Succès !',text: 'Votr évènement a bien été crée !',type: 'success'},function(){window.location.href = '${createLink(action: 'index')}';});}" value="Créer" />



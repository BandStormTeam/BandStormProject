<%@ page import="bandstorm.Band" %>

<div class="form-group">
	<div class="row">
		<div class="col-md-6">
			<label for="bandName">Nom</label>
			<g:textField name="bandName" class="form-control" maxlength="35" id="bandName" placeholder="Nom"
						 value="${bandInstance?.name}"/>
			<g:hasErrors bean="${bandInstance}" field="name">
				<div class="alert alert-danger alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
							aria-hidden="true">&times;</span></button>
					<strong>Le nom doit être compris entre 3 et 35 caractères</strong>
				</div>
			</g:hasErrors>
		</div>
	</div>
</div>

<div class="form-group">
	<label for="bandAddress">Lieu</label>
	<g:textField name="bandAddress" class="form-control"
				 maxlength="200" id="bandAddress" placeholder="Souhaitez vous indiquer un pays/ville d'appartenance ?" value="${bandInstance?.address}"/>
	<g:hasErrors bean="${bandInstance}" field="address">

		<div class="alert alert-danger alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
					aria-hidden="true">&times;</span></button>
		</div>
	</g:hasErrors>
</div>

<div class="form-group">
	<label for="bandDescription">Description</label>
	<g:textArea rows="5" name="bandDescription" class="form-control" id="bandDescription" placeholder="Décrivez votre groupe" value="${bandInstance?.description}"/>
	<g:hasErrors bean="${bandInstance}" field="description">
		<div class="alert alert-danger alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
					aria-hidden="true">&times;</span></button>
			<strong>La description du groupe est limitée ne peut être vide (limite 550 caractères).</strong>
		</div>
	</g:hasErrors>
</div>

<div class="form-group">
	<label for="bandTags">Tags</label>
	<g:textField name="bandTags" class="form-control" id="bandTags" placeholder="Ajoutez des tags en les séparant par un point-virgule"/>
</div>

<div>
	<h1 style="display: none" id="head">${(status) ? status :'empty'}</h1>
</div>

<g:submitToRemote class="btn btn-primary" url="[resource:bandInstance, controller:'band', action:'save']" update="creationForm" onSuccess="if(document.getElementById('head').innerHTML == 'OK'){swal({title:'Succès !',text: 'Votre groupe a été créée !',type: 'success'},function(){window.location.href = '${createLink(action: 'index')}';});}" value="Créer" />



<%@ page import="bandstorm.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<meta name="layout" content="main"/>


<div class="container" style="padding-top: 100px;">

	<div class="row">
		<div class="col-xs-2"></div>
		<div class="col-xs-6" >

			<h2 style="color:white;"> Etape 1/2 : Présente toi</h2>

			<g:form url="[resource:userInstance, action:'save']" >
				<g:textField class="form-control ${hasErrors(bean:userInstance,field:'username','errors')}" placeholder="Pseudo" name="username" value="${userInstance.username}">  </g:textField><bR>

                <g:textField class="form-control ${hasErrors(bean:userInstance,field:'email','errors')}"   placeholder="Email" name="email" value="${userInstance.email}">  </g:textField><bR>

                <g:passwordField class="form-control ${hasErrors(bean:userInstance,field:'password','errors')}"  placeholder="Mot de passe" name="password" value="${userInstance.password}">  </g:passwordField><bR>

				<g:textField class="form-control ${hasErrors(bean:userInstance,field:'firstName','errors')}"  placeholder="Prenom" name="firstName" value="${userInstance.firstName}">  </g:textField><bR>
				<g:textField class="form-control ${hasErrors(bean:userInstance,field:'lastName','errors')}"  placeholder="Nom" name="lastName" value="${userInstance.lastName}">  </g:textField><bR>
                <g:textField class="form-control ${hasErrors(bean:userInstance,field:'country','errors')}"  placeholder="Votre pays" name="country" value="${userInstance.country}">  </g:textField><bR>
                <strong style="color:white;">Date de naissance :</strong>
                <g:datePicker class="form-control ${hasErrors(bean:userInstance,field:'birthDate','errors')}" name="birthDate" precision="day"  value="${userInstance?.birthDate}"  /><bR><bR>


				<div style="text-align: right">
					<g:submitButton name="create" class="btn btn-success" action="save" value="Suivant" />
				</div>

			</g:form>


		</div>
	</div>
</div>

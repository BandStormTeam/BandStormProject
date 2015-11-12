<%@ page import="bandstorm.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<meta name="layout" content="main"/>



<div class="container" style="padding-top: 50px;">

    <g:form url="[resource:userInstance, action:'update']" >

        <g:hiddenField name="page" value="profilSettings"></g:hiddenField>

        <div class="row">

            <h2 style="color: white;"> Bienvenue ${userInstance.username}</h2>
            <br>
            <div class="col-xs-2">

                <ul class="nav nav-pills nav-stacked nav-pills-stacked-example">
                    <li role="presentation" class="active" ><a href="<g:createLink controller="user" action="profilSettings"/>">Profile</a></li>
                    <li role="presentation"><a href="<g:createLink controller="user" action="passwordSettings"/>">Mot de passe</a></li>
                </ul>

            </div>
            <div class="col-xs-6" >
                <strong style="color:white;">Pseudo :</strong><br>
                <g:textField class="form-control ${hasErrors(bean:userInstance,field:'username','errors')}" placeholder="Pseudo" name="username" value="${userInstance.username}">  </g:textField><bR>

                <g:hiddenField style="display: none;" name="email" value="${userInstance.email}">  </g:hiddenField>


                <g:hiddenField style="display: none;" name="password" value="${userInstance.password}">  </g:hiddenField>
                <strong style="color:white;">Nom :</strong><br>
                <g:textField class="form-control ${hasErrors(bean:userInstance,field:'firstName','errors')}"  placeholder="Prenom" name="firstName" value="${userInstance.firstName}">  </g:textField><bR>
                <strong style="color:white;">Prenom :</strong><br>
                <g:textField class="form-control ${hasErrors(bean:userInstance,field:'lastName','errors')}"  placeholder="Nom" name="lastName" value="${userInstance.lastName}">  </g:textField><bR>
                <strong style="color:white;">Pays :</strong><br>
                <g:textField class="form-control ${hasErrors(bean:userInstance,field:'country','errors')}"  placeholder="Votre pays" name="country" value="${userInstance.country}">  </g:textField><bR>
                <strong style="color:white;">Date de naissance :</strong><br>
                <g:datePicker class="form-control ${hasErrors(bean:userInstance,field:'birthDate','errors')}" name="birthDate" precision="day"  value="${userInstance?.birthDate}"  /><bR><bR>
                <strong style="color:white;">Lien vers votre photo de profil :</strong>
                <g:textField placeholder="site.com/image.jpg" class="form-control ${hasErrors(bean:userInstance,field:'urlAvatar','errors')}" name="urlAvatar" value="${userInstance?.urlAvatar}"  /><bR>
                <div style="text-align: right">
                    <g:submitButton name="create" class="btn btn-success" value="Enregistrer" />
                </div>




            </div>
        </div>

    </g:form>
</div>

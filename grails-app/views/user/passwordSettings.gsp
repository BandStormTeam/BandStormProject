<%@ page import="bandstorm.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<meta name="layout" content="main"/>



<div class="container" style="padding-top: 50px;">

    <g:form url="[resource:userInstance, action:'update']" >

    <g:hiddenField name="page" value="passwordSettings"></g:hiddenField>

    <div class="row">

        <h2 style="color: white;"> Bienvenue ${userInstance.email}</h2>
        <br>
        <div class="col-xs-2">

            <ul class="nav nav-pills nav-stacked nav-pills-stacked-example">
                <li role="presentation" ><a href="<g:createLink controller="user" action="profilSettings"/>">Profile</a></li>
                <li role="presentation" class="active"><a href="<g:createLink controller="user" action="passwordSettings"/>">Mot de passe</a></li>
            </ul>



        </div>
        <div class="col-xs-6" >
                <strong style="color:white;">Mot de passe :</strong><br>

                <g:passwordField placeholder="Nouveau mot de passe"  class="form-control ${hasErrors(bean:userInstance,field:'password','errors')}"  name="password" value="">  </g:passwordField>
                <br>
                <div style="text-align: right">
                    <g:submitButton name="create" class="btn btn-success" value="Enregistrer" />
                </div>


        </div>
    </div>

    </g:form>
</div>

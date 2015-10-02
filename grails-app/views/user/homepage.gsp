<%--
  Created by IntelliJ IDEA.
  User: Steve
  Date: 02/10/2015
  Time: 10:56
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<meta name="layout" content="main"/>

<div class="container">
    <br><br><br><br>
    <div class="row">
        <div class="col-xs-6"></div>
        <div class="col-xs-6" style="color:white;">
            <h1>Suit tes musiciens favoris et leurs évènements.</h1><bR>

            <g:form url="[resource:userInstance, action:'save']" >
                <g:textField class="form-control" type="email"  placeholder="Email" name="email" value="${email}">  </g:textField><bR>
                <g:textField class="form-control" type="password" placeholder="Mot de passe" name="password" value="${password}">  </g:textField><bR>

                <g:submitButton name="create" class="btn btn-success" value="Inscription" />

            </g:form>

        </div>
    </div>
</div>


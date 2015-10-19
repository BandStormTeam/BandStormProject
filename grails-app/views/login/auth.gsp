
<%@ page import="bandstorm.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<meta name="layout" content="main"/>

<div class="container">
  <br><br><br><br>
  <div class="row">
    <div class="col-xs-6"></div>
    <div class="col-xs-6" style="color:white;">
      <h1>Suit tes musiciens favoris et leurs évènements.</h1><bR>

      <g:form controller="user" action="save" novalidate="novalidate"  >
        <g:textField required="required" class="form-control" type="input"  placeholder="Pseudo" name="username" >  </g:textField><bR>
        <g:field required="required" class="form-control" type="email"  placeholder="Email" name="email" >  </g:field><bR>
        <g:passwordField required="required" class="form-control" type="password" placeholder="Mot de passe" name="password" >  </g:passwordField><bR>
        <div style="text-align: right">
          <g:submitButton name="create" class="btn btn-success" value="Inscription" />
        </div>

      </g:form>

    </div>
  </div>
</div>


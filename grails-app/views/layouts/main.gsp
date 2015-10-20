<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<meta name="description" content="">
	<meta name="author" content="">


	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="${resource(dir:"css/bootstrap",file:"bootstrap.min.css")}">
	<link rel="stylesheet" href="${resource(dir:"css/bootstrap",file:"bootstrap-theme.min.css")}">

	<!-- Latest compiled and minified JavaScript -->
	<script src="${resource(dir:"js",file:"jquery-1.11.3.min.js")}"></script>
	<script src="${resource(dir:"js/bootstrap",file:"bootstrap.min.js")}"></script>



	<style>
	body{
		background-image: url("${resource(dir:"images",file:"m.jpg")}");
		background-size:cover;
		background-attachment: fixed;
	}

	.errors{
		border-color: rgba(200,0,0,0.8) !important;
		border-width: 2px;
	}
	</style>

	<title><g:layoutTitle default="Grails"/></title>

	<g:layoutHead/>
	<g:javascript library="application"/>
	<r:layoutResources />

</head>


<body>

<nav class="navbar navbar-default navbar-fixed-top">
	<div style="padding:5px;margin:0;padding-top:0px;padding-bottom:0px;">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<g:createLink controller="user" action="userHome"/>">BandStorm</a>

		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<sec:ifLoggedIn>
				<div class="navbar-form navbar-left">

					<div class="form-group">
						<g:form  method="get" controller="user" action="searchUser">
							<input type="text" placeholder="Trouvez un ami ou un groupe" class="form-control" name="keywords" value="${keywords}" style="width:400px;"/>
							<input type="submit" class="btn btn-success" value="Rechercher" />
						</g:form >
					</div>
				</div>
			</sec:ifLoggedIn>
			<div class="navbar-form navbar-right">

				<sec:ifNotLoggedIn>
					<g:form  method="post" controller="user">
						<div class="form-group">
							<g:textField type="text" placeholder="username" class="form-control" name="username"/>
						</div>
						<div class="form-group">
							<g:passwordField type="password" placeholder="password" class="form-control" name="password"/>
						</div>
						<g:actionSubmit  class="btn btn-success" action="userHome" value="Connexion" />
					</g:form>
				</sec:ifNotLoggedIn>
				<sec:ifLoggedIn>
					<a href="<g:createLink controller="user" action="show" />/${sec.loggedInUserInfo(field: 'id')}" type="button" class="btn btn-default" style="display: inline-block"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></a>
					<a href="<g:createLink controller="user" action="userHome"/>" type="button" class="btn btn-default" style="display: inline-block"><span class="glyphicon glyphicon-globe" aria-hidden="true" ></span></a>


					<div  class="dropdown" style="display: inline-block">
						<button style="width:120px;" class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
							<span class="glyphicon glyphicon-cog" aria-hidden="true"></span> <sec:username/>
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<li><a href="${createLink(controller: 'user', action: 'profilSettings')}">Paramétrage</a></li>
							<li><a href="${createLink(controller: 'user', action: 'logout')}">Déconnexion</a></li>
						</ul>
					</div>

				</sec:ifLoggedIn>
			</div>
		</div><!--/.navbar-collapse -->
	</div>
</nav>

<g:layoutBody/>

<r:layoutResources />
</body>
</html>

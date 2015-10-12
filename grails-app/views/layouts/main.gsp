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
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>



	<style>
	body{
		background-image: url("${resource(dir:"images",file:"m.jpg")}");
		background-size:cover;
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
			<a class="navbar-brand" href="#">BandStorm</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<g:form class="navbar-form navbar-right" method="post" controller="user">
				<sec:ifNotLoggedIn>
					<div class="form-group">
						<g:textField type="text" placeholder="Email" class="form-control" name="username"/>
					</div>
					<div class="form-group">
						<g:passwordField type="password" placeholder="Password" class="form-control" name="password"/>
					</div>
					<g:actionSubmit  class="btn btn-success" action="userHome" value="Connexion" />
				</sec:ifNotLoggedIn>
				<sec:ifLoggedIn>

					<div  class="dropdown">
						<button style="width:120px;" class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
							<span class="glyphicon glyphicon-user" aria-hidden="true"></span> <sec:username/>
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<li><a href="#">Paramétrage</a></li>
							<li><a href="${createLink(controller: 'user', action: 'logout')}">Déconnexion</a></li>
						</ul>
					</div>

				</sec:ifLoggedIn>
			</g:form>
		</div><!--/.navbar-collapse -->
	</div>
</nav>

<g:layoutBody/>

<r:layoutResources />
</body>
</html>

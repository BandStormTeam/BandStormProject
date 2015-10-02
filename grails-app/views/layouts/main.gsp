<%
    String connected = 'yes'
%>
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
    body {
        background-image: url("${resource(dir:"images",file:"m.jpg")}");
        background-size: cover;
    }
    </style>

    <title><g:layoutTitle default="Grails"/></title>

    <g:layoutHead/>
    <g:javascript library="application"/>
    <r:layoutResources/>

</head>


<body>

<nav class="navbar navbar-default navbar-fixed-top">
    <div style="padding:5px;margin:0;padding-top:0px;padding-bottom:0px;">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">BandStorm</a>
        </div>

        <g:if test="${connected == 'yes'}">
            <div id="navbar" class="navbar-collapse collapse">
                <form class="navbar-form navbar-right">
                    <div class="form-group">
                        <input type="text" placeholder="Email" class="form-control">
                    </div>

                    <div class="form-group">
                        <input type="password" placeholder="Password" class="form-control">
                    </div>
                    <a class="btn btn-success"
                       href="${createLink(controller: 'user', action: 'userHome')}">Connexion</a>
                </form>
            </div>
        </g:if>
        <g:else>
            <div id="navbar" class="navbar-collapse collapse">
                <form class="navbar-form navbar-right">

                    <a  class="btn btn-default" href="myhome.html"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></a>

                    <div class="dropdown clearfix" style="display:inline-block;">
                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            <span class="glyphicon glyphicon-th-large" aria-hidden="true"></span>
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <li><a href="#">Créer un évènement</a></li>
                            <li><a href="#">Créer un groupe</a></li>
                            <li><a href="#">Se déconnecter</a></li>
                        </ul>
                    </div>
                    <input type="text" placeholder="Vous cherchez un artiste, un groupe, une personne?" class="form-control" style="width:450px;">

                    <a  class="btn btn-success" href="myhome.html">Rechercher</a>
                </form>
            </div><!--/.navbar-collapse -->
        </g:else>
    </div>
</nav>

<g:layoutBody/>

<r:layoutResources/>
</body>
</html>
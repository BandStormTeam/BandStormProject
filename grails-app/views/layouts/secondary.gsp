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
    </div>
</nav>

<g:layoutBody/>

<r:layoutResources />
</body>
</html>

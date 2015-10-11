<%--
  Created by IntelliJ IDEA.
  User: Julian
  Date: 02/10/2015
  Time: 15:19
--%>

<%@ page import="bandstorm.User" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Jumbotron Template for Bootstrap</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <style>

    body {
        background: url("${resource(dir: 'images', file: 'm.jpg')}") fixed;
        background-size: cover;
    }
    </style>
</head>

<body>

<div class="container">
    <br><br><br><br>


    <div class="container">

        <div class="row">

            <div class="col-sm-8 blog-main">

                <div class="panel panel-default">
                    <div class="panel-body">
                        <textarea class="form-control" style="height:60px;"
                                  placeholder="Partagez votre actualité."></textarea>

                        <div style="text-align:right;padding-top:5px;"><button type="button"
                                                                               class="btn btn-default btn-xs">Publier</button>
                        </div>
                    </div>
                </div>

                <div style="background-color:rgb(255,255,255);padding:15px;">

                    <ul class="nav nav-tabs">
                        <li role="presentation" style="width:160px;text-align:center;" class="active"><a
                                href="#">Actualité</a></li>
                        <li role="presentation" style="width:160px;text-align:center;"><a href="#">Abonnement <span
                                class="badge">42</span></a></li>
                        <li role="presentation" style="width:160px;text-align:center;"><a href="#">Abonnés <span
                                class="badge">492</span></a></li>
                    </ul>

                    <g:each in="${statusList}" var="status">
                        <div class="media">
                            <div class="media-left">
                                <a href="#">
                                    <img class="media-object" data-src="holder.js/64x64" alt="64x64" src="${resource(dir: 'images', file: 'r.jpg')}"
                                         data-holder-rendered="true" style="width: 64px; height: 64px;">

                                </a>
                            </div>

                            <div class="media-body">
                                <h4 class="media-heading">Crazy <g:fieldValue bean="${user}" field="username"/></h4>
                                <i>Posté le ${status.dateCreated}</i>
                            </div>
                            <br>
                        </div>

                        <button type="button" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-fire"
                                                                                   aria-hidden="true"></span> Light (${status.lightCount})
                        </button>
                        <br><br>
                        <blockquote>
                            <p id="content${status.id}">${status.content}</p>
                        </blockquote>

                        </br>
                    </g:each>


                </div><!-- /.blog-userHomePage -->
            </div><!-- /.blog-userHomePage -->

            <div class="col-sm-3  blog-sidebar" style="background-color:rgb(255,255,255);padding:15px;">
                <div class="sidebar-module sidebar-module-inset">
                    <img src="${resource(dir: 'images', file: 'r.jpg')}" data-holder-rendered="true" style="width: 75%;"
                         style="display:inline-block !important;">

                    <h4>Crazy <g:fieldValue bean="${user}" field="username"/></h4>

                    <p> <em>"Merry"</em> Christmas, May the season be fun joyful and especialy crazy!! :P<br><br>

                    </p>

                </div>

                <div class="sidebar-module">
                    <h4>Derniers événements</h4>
                    <ol class="list-unstyled">
                        <li><a href="#">[16 Mars 2013] Concert a roterdam</a></li>
                        <li><a href="#">[16 Mars 2013] Concert a roterdam</a></li>
                        <li><a href="#">[16 Mars 2013] Concert a roterdam</a></li>
                    </ol>
                </div>
                <bR>

                <div class="sidebar-module">
                    <h4>Elsewhere</h4>
                    <ol class="list-unstyled">

                        <li><a href="#">https://twitter.com/Crazy_<g:fieldValue bean="${user}" field="username"/></a></li>
                        <li><a href="#">https://facebook.com/Crazy_<g:fieldValue bean="${user}" field="username"/></a></li>
                    </ol>
                </div>
            </div><!-- /.blog-sidebar -->

        </div><!-- /.row -->

    </div>

</div>

</body>
</html>

<%@ page import="bandstorm.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<meta name="layout" content="main"/>
<div class="col-sm-8 blog-main">

<div class="container" style="padding-top: 100px;">

    <div class="col-sm-8 blog-main">
        <div style="background-color:rgb(255,255,255);padding:15px;min-height: 400px">

        </div>
    </div>
    <div class="col-sm-3  blog-sidebar" style="background-color:rgb(255,255,255);padding:15px;">
        <div class="sidebar-module sidebar-module-inset">
          <!--  <img src="${resource(dir: 'images', file: 'r.jpg')}" data-holder-rendered="true" style="width: 75%;"
                 style="display:inline-block !important;">-->
            <h4>${userInstance.username} <small>${userInstance.firstName} ${userInstance.lastName}</small></h4>
            <ul style="list-style-type: none">
                <li><i class="glyphicon glyphicon-calendar"></i>&nbsp;Né le <g:formatDate format="dd/MM/yyyy" date="${userInstance.birthDate}"/></li>
                <li><i class="glyphicon glyphicon-calendar"></i>&nbsp;Inscrit le <g:formatDate format="dd/MM/yyyy" date="${userInstance.dateCreated}"/></li>
                <li><i class="glyphicon glyphicon-envelope"></i>&nbsp;Email: <a href="mailto:${userInstance.email}">${userInstance.email}</a></li>
                <li><i class="glyphicon glyphicon-flag"></i>&nbsp;Pays: ${userInstance.country}</li>
            </ul>
        </div>


    <!--<div class="row">
        <div class="col-xs-2"></div>
        <div class="col-xs-6">
        <div class="jumbotron">

        </div>-->

        </div>
    </div>
</div>

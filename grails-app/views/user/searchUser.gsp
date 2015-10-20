<%@ page import="bandstorm.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<meta name="layout" content="main"/>



<div class="container" style="padding-top: 100px;">

    <div class="container">

        <div class="row">



            <div class="col-sm-3  blog-sidebar" style="background-color:rgb(255,255,255);padding:15px;">
                <h2>Filtrage</h2>
                <ul class="nav nav-pills nav-stacked nav-pills-stacked-example">
                    <li role="presentation" class="active"><a href="#">Utilisateurs</a></li>
                    <li role="presentation"><a href="#">Groupes</a></li>
                    <li role="presentation"><a href="#">Evenements</a></li>
                </ul>
            </div><!-- /.blog-sidebar -->



            <div class="col-sm-8 blog-main" style="background-color:rgb(255,255,255);padding:15px;margin-left: 10px;">

                <h2>Recherche pour "${keywords}"</h2>
                <br>
                <div class="bs-example" data-example-id="media-alignment">

                    <g:each in="${userList}" var="user" >
                        <div class="media">
                            <div class="media-left">
                                <a href="#">
                                    <img class="media-object" src="${user.urlAvatar}" style="width: 100px; height: 100px;">
                                </a>
                            </div>
                            <div class="media-body">
                                <h4 class="media-heading">${user.username}</h4>
                                <p>
                                    <span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${user.firstName} ${user.lastName}<br>
                                    <span class="glyphicon glyphicon-home" aria-hidden="true"></span> ${user.country}
                                </p>
                            </div>
                        </div>
                    </g:each>

                    <div class="pagination">
                        <g:paginate controller="user" action="searchUser" total="${userCount}" max="10" params="[keywords:keywords]" />
                    </div>
                </div>
            </div><!-- /.blog-userHomePage -->


        </div><!-- /.row -->


    </div>

</div>

</body>
</html>

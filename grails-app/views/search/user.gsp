<%@ page import="bandstorm.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<meta name="layout" content="main"/>



<div class="container" style="padding-top: 100px;">

    <div class="container">

        <div class="row">
            <g:render template="filters"></g:render>
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
                                <h4 class="media-heading">
                                    <g:link controller="user" action="show"  id="${user.id}" style="text-decoration: none;">${user.username}</g:link>
                                </h4>
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

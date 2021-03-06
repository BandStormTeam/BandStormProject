<%@ page import="bandstorm.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<meta name="layout" content="main"/>


<div class="container" style="padding-top: 100px;">

    <div class="container">

        <div class="row">

            <div class="col-sm-8 blog-main">

                <div style="background-color:rgb(255,255,255);padding:15px;">
                    <g:each in="${statusList}" var="st">
                        <g:render template="/status/display-status" model="[status: st, width:"80px"]"></g:render>
                    </g:each>

                </div><!-- /.blog-userHomePage -->
            </div><!-- /.blog-userHomePage -->

            <div class="col-sm-3  blog-sidebar" style="background-color:rgb(255,255,255);padding:15px;">
                <div class="sidebar-module sidebar-module-inset">
                    <g:render template="../user/avatar" model="[userInstance: userInstance, width: '200px']"/>
                    <h4>${userInstance.username} <small>${userInstance.firstName} ${userInstance.lastName}</small></h4>
                    <ul style="list-style-type: none">
                        <li><i class="glyphicon glyphicon-calendar"></i>&nbsp;Né le <g:formatDate format="dd/MM/yyyy" date="${userInstance.birthDate}"/></li>
                        <li><i class="glyphicon glyphicon-calendar"></i>&nbsp;Inscrit le <g:formatDate format="dd/MM/yyyy" date="${userInstance.dateCreated}"/></li>
                        <li><i class="glyphicon glyphicon-envelope"></i>&nbsp;Email: <a href="mailto:${userInstance.email}">${userInstance.email}</a></li>
                        <li><i class="glyphicon glyphicon-flag"></i>&nbsp;Pays: ${userInstance.country}</li>
                    </ul>

                    <g:if test="${!(userInstance.id == currentUser.id)}">
                        <g:if test="${!userInstance.isFollowed(currentUser)}">
                            <g:link controller="user" action="followUser" id="${userInstance.id}" class="btn btn-default">Follow</g:link>
                        </g:if>
                        <g:else>
                            <g:link controller="user" action="unfollowUser" id="${userInstance.id}" class="btn btn-info">Unfollow</g:link>
                        </g:else>
                    </g:if>
                </div>

                <div class="sidebar-module">
                    <h4>Ses groupes</h4>
                    <ol class="list-unstyled">
                        <g:each in="${userInstance.getBands()}" var="b">
                            <li><a href="${createLink(action: 'show',controller: 'band', id: b.id)}">${b.name}</a></li>
                        </g:each>
                    </ol>
                </div>
                <bR>

                <div class="sidebar-module">
                </div>
            </div><!-- /.blog-sidebar -->

        </div><!-- /.row -->

    </div>

</div>

</body>
</html>



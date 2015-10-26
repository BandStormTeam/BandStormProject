<%@ page import="bandstorm.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<meta name="layout" content="main"/>


<div class="container" style="padding-top: 100px;">

    <div class="container">

        <div class="row">

            <div class="col-sm-8 blog-main">

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
                                    <img class="media-object" data-src="holder.js/64x64" alt="64x64"
                                         src="${resource(dir: 'images', file: 'r.jpg')}"
                                         data-holder-rendered="true" style="width: 64px; height: 64px;">

                                </a>
                            </div>

                            <div class="media-body">
                                <h4 class="media-heading"><a href="${createLink(action: 'show',controller: 'user', id: status.author.id)}">${status.author.username}</a></h4>
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
                    <g:if test="${userInstance.urlAvatar != null}">
                        <img src="${userInstance.urlAvatar}" data-holder-rendered="true" style="width: 75%;"
                             style="display:inline-block !important;">
                    </g:if>

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



<%@ page import="bandstorm.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<meta name="layout" content="main"/>



<div class="container" style="padding-top: 100px;">

    <div class="container">

        <div class="row">

            <div class="col-sm-8 blog-main">

                <div class="panel panel-default">
                    <div class="panel-body">
                        <g:form url="[resource: statusInstance,controller: 'status', action: 'save']" name="statusForm">

                            <g:textField id="contentField"
                                         class="form-control ${hasErrors(bean: statusInstance, field: 'content', 'errors')}"
                                         placeholder="Partagez votre actualité." name="content"
                                         value="${statusInstance?.content}" style="height:60px;"></g:textField><bR>

                            <g:textField id="urlField"
                                         class="form-control ${hasErrors(bean: statusInstance, field: 'url', 'errors')}"
                                         placeholder="Un lien à partager ?" name="url" value="${statusInstance?.url}"
                                         style="height:30px;"></g:textField><bR>

                            <g:hiddenField name="lightCount" value="${statusInstance?.lightCount = 0}"/>
                            <div style="text-align: right">
                                <g:submitToRemote name="publish" class="btn btn-success" action="save"
                                                  controller="status" value="Publier" onComplete="clearFields()"/>
                            </div>

                            <g:javascript>
                                function clearFields() {
                                    $("#contentField").fadeOut(500);
                                    $("#urlField").fadeOut(500);
                                    setTimeout(function () {
                                        document.getElementById("contentField").value = "";
                                        document.getElementById("urlField").value = "";
                                    }, 300);
                                    $("#contentField").fadeIn(500);
                                    $("#urlField").fadeIn(500);
                                }
                            </g:javascript>
                        </g:form>
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

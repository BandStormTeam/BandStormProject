<%@ page import="bandstorm.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<meta name="layout" content="main"/>


<div class="container" style="padding-top: 100px;">

    <div class="container">

        <div class="row">

            <div class="col-sm-8 blog-main">

                <div class="panel panel-default">
                    <div class="panel-body">
                        <g:form url="[resource: statusInstance, controller: 'status', action: 'save']"
                                name="statusForm">

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
                                <g:submitButton name="publish" class="btn btn-success" action="save"
                                                controller="status" value="Publier" onClick="clearFields()"/>
                            </div>


                            <g:javascript>
                                function clearFields() {

                                    if (document.getElementById("contentField").value != "") {
                                        $("#contentField").fadeOut(500);
                                        $("#contentField").fadeIn(500);
                                    }

                                    if (document.getElementById("urlField").value != "") {
                                        $("#urlField").fadeOut(500);
                                        $("#urlField").fadeIn(500);
                                    }

                                    setTimeout(function () {
                                        document.getElementById("contentField").value = "";
                                        document.getElementById("urlField").value = "";
                                    }, 300);
                                }

                                $(document).ready(function () {

                                    var mypage = 1;
                                    var inLoad = true;

                                    var deviceAgent = navigator.userAgent.toLowerCase();
                                    var agentID = deviceAgent.match(/(iphone|ipod|ipad)/);
                                    var finish = false;

                                    $.get('../status/connectedUserTimeline', function (data) {
                                        $("#timeline").append(data);
                                    });


                                    $(window).on('scroll', function () {


                                        if ((  ($(window).scrollTop() + $(window).height()) >= $(document).height() - 50
                                                || agentID && ($(window).scrollTop() + $(window).height()) + 150 > $(document).height() ) && inLoad == true && !finish) {
                                            inLoad = false;

                                            $.when($.get("../status/connectedUserTimeline", {page: mypage}) ).then(function (data) {

                                                if (data == "") {
                                                    finish = true;
                                                    $("#loading").hide();
                                                }
                                                else {
                                                    $("#timeline").append(data);
                                                    mypage++;
                                                    inLoad = true;
                                                }
                                            });
                                        }
                                    });


                                });

                            </g:javascript>
                        </g:form>
                    </div>
                </div>

                <div style="background-color:rgb(255,255,255);padding:15px;">

                    <ul class="nav nav-tabs">
                        <li role="presentation" style="width:160px;text-align:center;" id="statusTimeline"><g:link controller="user" action="home">Actualité</g:link></li>
                        <li role="presentation" style="width:160px;text-align:center;" id="followedTimeline"><g:link controller="user" action="showFollowed">Abonnements</g:link></li>
                        <li role="presentation" style="width:160px;text-align:center;" id="followerTimeline"><g:link controller="user" action="showFollowers">Abonnés</g:link></li>
                    </ul>

                    <g:if test="${followersList != null || followedList != null}">

                        <g:if test="${followersList != null}">
                            <g:javascript>$("#followerTimeline").addClass('active');</g:javascript>
                            <g:if test="${followersList != []}">
                                <g:each in="${followersList}" var="follower">
                                    <p style="margin-top: 10px; font-size: large" ><a href="${createLink(action: 'show',controller: 'user', id: follower.id)}">${follower.username}</a></p><hr>
                                </g:each>
                            </g:if>
                            <g:else>
                                <p>Désolé, vous n'avez aucun abonné.</p>
                            </g:else>
                        </g:if>
                        <g:else>
                            <g:javascript>$("#followedTimeline").addClass('active');</g:javascript>
                            <g:if test="${followedList != []}">
                                <g:each in="${followedList}" var="follower">
                                    <p style="margin-top: 10px; font-size: large" ><a href="${createLink(action: 'show',controller: 'user', id: follower.id)}">${follower.username}</a></p><hr>
                                </g:each>
                            </g:if>
                            <g:else>
                                <p>Désolé, vous n'avez aucun abonnement.</p>
                            </g:else>
                        </g:else>
                     </g:if>
                    <g:else>
                        <g:javascript>$("#statusTimeline").addClass('active');</g:javascript>
                        <br>
                        <div id="timeline"> </div>
                        <div id="loading" style="text-align: center;"><img src="${resource(dir:"images",file:"loading.gif")}"></div>
                    </g:else>
                </div><!-- /.blog-userHomePage -->
            </div><!-- /.blog-userHomePage -->

            <div class="col-sm-3  blog-sidebar" style="background-color:rgb(255,255,255);padding:15px;">
                <div class="sidebar-module sidebar-module-inset">
                    <g:render template="../user/avatar" model="[userInstance: user, width: '200px']"/>

                    <h4>Crazy <g:fieldValue bean="${user}" field="username"/></h4>

                    <p><em>"Merry"</em> Christmas, May the season be fun joyful and especialy crazy!! :P<br><br>

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

                        <li><a href="#">https://twitter.com/Crazy_<g:fieldValue bean="${user}" field="username"/></a>
                        </li>
                        <li><a href="#">https://facebook.com/Crazy_<g:fieldValue bean="${user}" field="username"/></a>
                        </li>
                    </ol>
                </div>
            </div><!-- /.blog-sidebar -->

        </div><!-- /.row -->

    </div>

</div>

</body>
</html>

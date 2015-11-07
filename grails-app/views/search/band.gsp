<%@ page import="bandstorm.Band" %>
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

                    <g:each in="${bandList}" var="band" >
                        <div class="media">
                            <div class="media-left">
                                <a href="#">
                                    <img class="media-object" src="http://www.boulognemusique.com/index.44.jpg" style="width: 100px; height: 100px;">
                                </a>
                            </div>
                            <div class="media-body">
                                <h4 class="media-heading">
                                    <g:link controller="band" action="show"  id="${band.id}" style="text-decoration: none;">
                                        <button class="btn btn-default btn-xs dropdown-toggle" type="button">
                                            Voir le profil
                                        </button>
                                    </g:link>
                                    ${band.name}</h4>
                                <p>
                                    ${band.description}
                                </p>
                            </div>
                        </div>
                    </g:each>

                    <div class="pagination">
                        <g:paginate controller="user" action="searchBand" total="${bandCount}" max="10" params="[keywords:keywords]" />
                    </div>
                </div>
            </div><!-- /.blog-userHomePage -->
        </div><!-- /.row -->
    </div>
</div>

</body>
</html>

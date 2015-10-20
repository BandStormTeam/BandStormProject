<div class="col-sm-3  blog-sidebar" style="background-color:rgb(255,255,255);padding:15px;">
    <h2>Filtrage</h2>
    <ul class="nav nav-pills nav-stacked nav-pills-stacked-example">
        <li role="presentation" <g:if test="${actionName == 'searchUser'}">class="active"</g:if> ><g:link controller="user" action="searchUser" params="[keywords:keywords]">Utilisateurs</g:link></li>
        <li role="presentation" <g:if test="${actionName == 'searchBand'}">class="active"</g:if> ><g:link controller="user" action="searchBand" params="[keywords:keywords]">Groupes</g:link></li>
    </ul>
</div>
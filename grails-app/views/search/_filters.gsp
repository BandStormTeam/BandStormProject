<div class="col-sm-3  blog-sidebar" style="background-color:rgb(255,255,255);padding:15px;">
    <h2>Filtrage</h2>
    <ul class="nav nav-pills nav-stacked nav-pills-stacked-example">
        <li id="user-filter" role="presentation" <g:if test="${actionName == 'searchUser'}">class="active"</g:if> ><g:link controller="search" action="user" params="[keywords:keywords]">Utilisateurs</g:link></li>
        <li id="band-filter" role="presentation" <g:if test="${actionName == 'searchBand'}">class="active"</g:if> ><g:link controller="search" action="band" params="[keywords:keywords]">Groupes</g:link></li>
        <li id="event-filter" role="presentation" <g:if test="${actionName == 'searchEvent'}">class="active"</g:if> ><g:link controller="search" action="event" params="[keywords:keywords]">Événements</g:link></li>
    </ul>
</div>
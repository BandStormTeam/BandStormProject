<dl class="dl-horizontal">
    <g:if test="${eventInstance?.name}">
        <dt>Nom</dt>
        <dd><g:fieldValue bean="${eventInstance}" field="name"/></dd>
    </g:if>

    <g:if test="${eventInstance?.dateEvent}">
        <dt>Date de l'évènement</dt>
        <dd><g:fieldValue bean="${eventInstance}" field="dateEvent"/></dd>
    </g:if>

    <g:if test="${eventInstance?.dateCreated}">
        <dt>Date de création</dt>
        <dd><g:fieldValue bean="${eventInstance}" field="dateCreated"/></dd>
    </g:if>

    <g:if test="${eventInstance?.address}">
        <dt>Adresse</dt>
        <dd><g:fieldValue bean="${eventInstance}" field="address"/></dd>
    </g:if>

    <g:if test="${eventInstance?.description}">
        <dt>Description</dt>
        <dd><g:fieldValue bean="${eventInstance}" field="description"/></dd>
    </g:if>

    <g:if test="${eventInstance?.tags}">
        <dt>Tags</dt>
        <dd><g:fieldValue bean="${eventInstance}" field="address"/></dd>
    </g:if>
</dl>
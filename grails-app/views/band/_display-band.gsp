<dl class="dl-horizontal">
    <g:if test="${bandInstance?.name}">
        <dt>Nom</dt>
        <dd><g:fieldValue bean="${bandInstance}" field="name"/></dd>
    </g:if>

    <g:if test="${bandInstance?.dateCreated}">
        <dt>Date de création</dt>
        <dd><g:fieldValue bean="${bandInstance}" field="dateCreated"/></dd>
    </g:if>

    <g:if test="${bandInstance?.address}">
        <dt>Lieu</dt>
        <dd><g:fieldValue bean="${bandInstance}" field="address"/></dd>
    </g:if>

    <g:if test="${bandInstance?.description}">
        <dt>Description</dt>
        <dd><g:fieldValue bean="${bandInstance}" field="description"/></dd>
    </g:if>

</dl>

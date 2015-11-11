<g:form action='delete' method="post">
    <dl class="dl-horizontal">
        <g:hiddenField name="id" value="${eventInstance?.id}"/>
        <g:if test="${eventInstance?.name}">
            <dt>Nom</dt>
            <dd><g:fieldValue bean="${eventInstance}" field="name"/>
                <a href="#" id="buttonTrigger">
                    <span class="glyphicon glyphicon-trash"></span>
                </a>
                <g:submitButton style="display: none;" name="submit" id="submit"/>
                <g:javascript>
                    $("#buttonTrigger").on( "click", function() {
                        $("#submit").click();
                    });
                </g:javascript>
            </dd>
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
            <dd><g:each in="${eventInstance.tags}" var="tag">
                <span class="label label-default">${tag.name}</span>
            </g:each></dd>
        </g:if>
    </dl>
</g:form>
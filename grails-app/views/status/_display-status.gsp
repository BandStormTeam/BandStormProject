<div class="media">
    <div class="media-left">
        <a href="${createLink(action: 'show',controller: 'user', id: status.author.id)}">
            <g:render template="../user/avatar" model="[userInstance: status.author, width:width]"/>
        </a>
    </div>

    <div class="media-body">
        <h4 class="media-heading"><a id="status-${status.id}" href="${createLink(action: 'show',controller: 'user', id: status.author.id)}">${status.author.username}</a></h4>
        <i>Posté le ${status.dateCreated}</i>
        <p id="content${status.id}">${status.content}</p>

    </div>
    <br>
</div>

<g:if test="${status.isLighted(user)}">
    <a href="${createLink(controller: 'user', action: 'unlight', id:status.id)}"><button id="unlightbtn-${status.id}" type="button" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-fire" aria-hidden="true"></span> Unlight (${status.nbLight()})
    </button></a>
</g:if>
<g:else>
    <a href="${createLink(controller: 'user', action: 'light', id:status.id)}"><button id="lightbtn-${status.id}" type="button" class="btn btn-default btn-xs">
        <span class="glyphicon glyphicon-fire" aria-hidden="true"></span> Light (${status.nbLight()})
    </button></a>
</g:else>


<g:if test="${status.url != null}">
    <a target="_blank" href="${status.url}">See more...</a>
    <iframe scrolling="no" style="pointer-events: none;width: 100%;height: 300px;border: none;overflow:hidden !important;" src="${status.url}"></iframe>

</g:if>

</br>

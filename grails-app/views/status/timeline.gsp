<g:each in="${statusList}" var="status">
    <div class="media">
        <div class="media-left">
            <a href="#">
                <img class="media-object" data-src="holder.js/64x64" alt="64x64"
                     src="${status.author.urlAvatar}"
                     data-holder-rendered="true" style="width: 64px; height: 64px;">

            </a>
        </div>

        <div class="media-body">
            <h4 class="media-heading"><a href="${createLink(action: 'show',controller: 'user', id: status.author.id)}">${status.author.username}</a></h4>
            <i>Posté le ${status.dateCreated}</i>
        </div>
        <br>
    </div>

    <a href="${createLink(controller: 'user', action: 'light', id:status.id)}"><button type="button" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-fire"
                                                               aria-hidden="true"></span> Light (${status.lightCount})
    </button></a>

    <br><br>
    <blockquote>
        <p id="content${status.id}">${status.content}</p>
    </blockquote>

    <g:if test="${status.url != null}">
        <a target="_blank" href="${status.url}">See more...</a>
        <iframe scrolling="no" style="pointer-events: none;width: 100%;height: 300px;border: none;overflow:hidden !important;" src="${status.url}"></iframe>

    </g:if>

    </br>
</g:each>
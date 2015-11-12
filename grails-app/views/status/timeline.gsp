<g:each in="${statusList}" var="st">
    <g:render template="/status/display-status" model="[status: st, width:"80px"]"></g:render>
</g:each>
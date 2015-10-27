<%@ page import="bandstorm.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<meta name="layout" content="secondary"/>



<div class="container" style="margin-top: 60px;">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4 list-group" style="padding-right: 0px; text-align: center ; background-color: whitesmoke; border: grey 2px solid; border-radius: 5px;">
            <g:if test="${type == 'success'}">
                <p class="list-group-item">Please check you email inbox for activation link</p>
            </g:if>
            <g:else>
                <a class="list-group-item" href="<g:createLink uri="/"/>"><strong>your account has been activated.</strong></a>
                <p>Click to access log in page.</p>
            </g:else>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>

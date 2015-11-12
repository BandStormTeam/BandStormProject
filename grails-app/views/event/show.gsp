<%@ page import="bandstorm.Event" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<meta name="layout" content="main"/>



<div class="container" style="padding-top: 100px;">
	<div class="container">
		<div class="row">
			<div class="col-sm-8 blog-main" style="background-color:rgb(255,255,255);padding:15px;margin-left: 10px;">
				<h2>${eventInstance?.name}</h2>
				<br>
				<div class="bs-example" data-example-id="media-alignment">
					<g:render template="/event/display-event" model="[eventInstance:eventInstance]"></g:render>
				</div>
			</div><!-- /.blog-userHomePage -->
		</div><!-- /.row -->
	</div>
</div>

</body>
</html>
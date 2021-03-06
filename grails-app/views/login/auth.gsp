<!DOCTYPE html>
<html lang="es">
<head>
<meta name='layout' content='main' />
<title><g:message code="springSecurity.login.title" /></title>
<link rel="stylesheet" href="${resource(dir: 'css', file: 'login.css')}" type="text/css">
</head>

<body>
   <div >
	<div id='login' >
				<div class="logo">
				<nav class="navbar navbar-default navbar-fixed-top navbar-ml">
        <div class="navbar-header">
            <!-- The mobile navbar-toggle button can be safely removed since you do not need it in a non-responsive implementation -->
            <h2 class="text-muted text-logo">
                <img src="../img/logo-ml-icon.png">
                <i class="fa fa-line-chart"></i> Analytics
            </h2>
        </div>
        <!-- Note that the .navbar-collapse and .collapse classes have been removed from the #navbar -->
</nav>
				</div>
		<div class='container'  style="margin-top:150px;">


			<div class="jumbotron" style="padding-top:10px;padding-bottom:20px;">

			<form action='${postUrl}' method='POST' id='loginForm'
				class='form-signin' autocomplete='off'  role="form">
				<h2 class="form-signin-heading"><g:message code="springSecurity.login.header"/></h2>
				<input type='text' class='form-control' name='j_username' id='username' placeholder="${message(code: "springSecurity.login.username.label")}" autofocus /> 
				<input type='password' class='form-control' name='j_password' id='password' placeholder="${message(code: "springSecurity.login.password.label")}"/>
				<label class="checkbox">
					<input type='checkbox' name='${rememberMeParameter}' id='remember_me' 
					<g:if test='${hasCookie}'>checked='checked'</g:if> /> 
						<g:message code="springSecurity.login.remember.me.label"/>
				</label>
				<g:if test='${flash.message}'>
				<div class='alert alert-danger alert-dismissable' style="font-size: 14px;line-height: 15px;">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
					${flash.message}
				</div>
			   </g:if>
					<button class="btn btn-lg btn-primary btn-block"  type='submit' id="submit"
						value='${message(code: "springSecurity.login.button")}' >${message(code: "springSecurity.login.button")}
					</button>
			</form>

			</div>
		</div>
	</div>
	</div>
	<script src="${resource(dir: 'js', file: 'jquery.backstretch.min.js')}"></script>
	   <script>
        $.backstretch("../img/bg-analytics.jpg", {speed:200});
    </script>
</body>
</html>

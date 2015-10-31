<nav class="navbar navbar-default navbar-fixed-top navbar-ml">
        <div class="navbar-header">
            <!-- The mobile navbar-toggle button can be safely removed since you do not need it in a non-responsive implementation -->
            <h2 class="text-muted text-logo">
                <img src="img/logo-ml-icon.png">
                <i class="fa fa-line-chart"></i> Analytics
            </h2>
        </div>
        <!-- Note that the .navbar-collapse and .collapse classes have been removed from the #navbar -->
    <div class="navbar-buttons navbar-header pull-right navbar-right" role="search">
    
        <ul class="list-inline ">
        <sec:ifLoggedIn>
            <li class="user-li">
            <img src="${urlUser}">
            ${user}
           </li>
         </sec:ifLoggedIn>
        <li class="user-li">
            <sec:ifLoggedIn>
			<g:link controller='logout' class="btn btn-primary btn-lg">
				Logout
			</g:link>
		   </sec:ifLoggedIn>
        </li>
        </ul>


    </div>
</nav>
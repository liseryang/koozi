<h3>Users</h3>
<#if userList??>
	<ul>
		<#list userList as user>
				<li><a href="${base}/users/${user.userId}">${user.surname} ${user.forename}</a></li>
		</#list>
	</ul>
</#if>
		

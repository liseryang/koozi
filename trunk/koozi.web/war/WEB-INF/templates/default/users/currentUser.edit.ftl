<h3>User</h3>
<#if user??>
	<#if user.userId??>
		<form action="${base}/users/currentuser" method="post">
			<input type="hidden" name="_method" value="put"/>
			<ul>
				<li>forename: <input name="forename" value="${user.forename}" /></li>
				<li>surname: <input name="surname" value="${user.surname}" /></li>
			</ul>
			<div>
				<input type="submit"  value="Save user"/>
			</div>
		</form>
		<form action="${base}/users/currentuser" method="post">
			<input type="hidden" name="_method" value="delete"/>
			<div>

				<input type="submit"  value="Delete user"/>
			</div>
			
		</form>
	</#if>
</#if>

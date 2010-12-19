<h3>Edit user</h3>
<#if user.userId??>
	<form action="${base}/users/$user.userId" method="post">
		<input type="hidden" name="_method" value="put"/>
		<ul>
			<li>forename: <input name="forename" value="${user.forename}" /></li>
			<li>surname: <input name="surname" value="${user.surname}" /></li>
		</ul>
		<div>
			<input type="submit"  value="Save user"/>
		</div>
	</form>
	<form action="${base}/users/${user.userId}" method="post">
		<input type="hidden" name="_method" value="delete"/>
		<div>

			<input type="submit"  value="Delete user"/>
		</div>
		
	</form>
</#if>
<#if isCurrentuser??>
	<@include_page path="${base}/users/currentuser/roles" inherit_params=false/>
<#else>
	<@include_page path="${base}/users/${user.userId}/roles" inherit_params=true params={"view": "edit"}/>
</#if>
<h3>Roles</h3>
<#list userRolesList as userRole>
	<form action="${base}/users/${userRole.userId}/roles/${userRole.id}" method="post">
		${userRole.role}
		<input type="hidden" name="_method" value="delete"/>
			<input type="submit"  value="Delete role"/>
	</form>
</#list>
<#if isCurrentuser??>
	<form action="${base}/users/currentuser/roles/" method="post">
<#else>
	<form action="${base}/users/${userId}/roles/" method="post">
</#if>
	<select name="role">
	
	       <option value="ROLE_ADMIN">Admin role</option>
	
	       <option value="ROLE_USER">User role</option>
	
	       <option value="ROLE_SUPER_USER">Super user role</option>
	
	 </select>
	<input type="submit"  value="Add role"/>
</form>
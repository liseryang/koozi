<h3>Roles</h3>
<#if userRolesList??>
	<#list userRolesList as userRole>
		${userRole.role}
		<form action="${base}/users/$userRole.userId/roles/$userRole.id" method="post">
			<input type="hidden" name="_method" value="delete"/>
			<div>
				<input type="submit"  value="Delete role"/>
			</div>

		</form>
	</#list>
</#if>	

<form action="${base}/users/$userId/roles/" method="post">
		<div>
			<select name="role">

		        <option value="ROLE_ADMIN">Admin role</option>
		
		        <option value="ROLE_USER">User role</option>

		        <option value="ROLE_SUPER_USER">Super user role</option>

    		</select>
			<input type="submit"  value="Add role"/>
		</div>
</form>
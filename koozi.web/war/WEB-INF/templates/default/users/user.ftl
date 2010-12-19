<h3>User</h3>
<ul>
	<li>Nickname: ${user.nickname}</li>
	<li>Email: ${user.email}</li>
	<li>Forname: ${user.forename}</li>
	<li>Surname: ${user.surname}</li>
</ul>
<#if isCurrentuser??>
	<form action="${base}/users/currentuser" method="get">
<#else>
	<form action="${base}/users/${user.userId}" method="get">
</#if>
	<input type="submit"  value="Edit user"/>
	<input type="hidden" name="view" value="edit"/>
</form>

<#if isCurrentuser??>
	<form action="${base}/users/currentuser" method="post">
<#else>
	<form action="${base}/users/${user.userId}" method="post">
</#if>
	<input type="hidden" name="_method" value="delete"/>
	<div>
		<input type="submit"  value="Delete user"/>
	</div>
</form>

<#if isCurrentuser??>
	<@include_page path="${base}/users/currentuser/roles" inherit_params=false/>
<#else>
	<@include_page path="${base}/users/${user.userId}/roles" inherit_params=true params={"view": "edit"}/>
</#if>

<#if user??>
	<#if user.userId??>
		<li><a href="${base}/users/${user.userId}">${user.surname} ${user.forename}</a></li>
	</#if>
</#if>		
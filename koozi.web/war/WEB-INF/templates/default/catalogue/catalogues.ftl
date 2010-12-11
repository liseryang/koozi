<h3>Catalogues</h3>
<#if productTagValues??>
		<#list productTagValues as value>
			<div><a href="${base}/catalogues/${value}">${value}</a></div>
		</#list>
</#if>
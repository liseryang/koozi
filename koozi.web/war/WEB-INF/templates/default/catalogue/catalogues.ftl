<#setting number_format="0">
<h3>Catalogues</h3>
<#list tagValues as value>
	<div><a href="${base}/catalogues/${value}">${value}</a></div>
</#list>

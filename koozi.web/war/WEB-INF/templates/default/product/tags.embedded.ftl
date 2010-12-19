<#setting number_format="0">
<ul>
	<#list tagList as tag>
		<li><a href="${base}/catalogues/${tag.value}">${tag.value}</a></li>
	</#list>
</ul>


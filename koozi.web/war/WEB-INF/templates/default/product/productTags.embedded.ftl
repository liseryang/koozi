<#if productTagList??>
	<ul>
		<#list productTagList as productTag>
			<li><a href="${base}/catalogues/${productTag.tag}">${productTag.tag}</a></li>
		</#list>
	</ul>
</#if>


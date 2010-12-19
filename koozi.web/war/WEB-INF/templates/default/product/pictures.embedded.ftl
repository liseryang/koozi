<#setting number_format="0">
<#if pictureList??>
	<ul>
		<#list pictureList as picture>
			<li><img style="height:100px" border="1" src="${picture.href}"/></li>
		</#list>
	</ul>
</#if>

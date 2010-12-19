<#setting number_format="0">
<#if tag??>
	<h1 class="title">${springMacroRequestContext.getMessage("catalogue.name." + tag)}</h1>
</#if>	
<#include "${base}/catalogue/catalogue.embedded.ftl"/>
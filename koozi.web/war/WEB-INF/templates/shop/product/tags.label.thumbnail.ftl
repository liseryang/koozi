<#setting number_format="0">
<#if tagList??>
	<#list tagList as tag>
		<#if tag.value == "nieuw">
			<div style="float:right;">
				<img style="position: absolute; margin-top: -60px; margin-left: -100px; width: 100px; " src="/static/shop/images/product/Nieuw.png" alt="">
			</div>
			<#break>
		</#if>
		<#if tag.value == "promo">
			<div style="float:right;">
				<img style="position: absolute; margin-top: -60px; margin-left: -100px; width: 100px; " src="/static/shop/images/product/Promo.png" alt="">
			</div>
			<#break>
		</#if>
	</#list>
</#if>
<#if productTagList??>
	<#list productTagList as productTag>
		<#if productTag.tag == "nieuw">
			<div style="float:left; margin-top: 250px; width: 0px;"><img
				style="position: absolute; margin-top: -60px; margin-left: -100px; width: 100px;"
				src="/static/shop/images/product/promo.png" alt=""></div>
			<#break>
		</#if>
		<#if productTag.tag == "promo">
			<div style="float:right;">
			<div style="float:left; margin-top: 250px; width: 0px;"><img
				style="position: absolute; margin-top: -60px; margin-left: -100px; width: 100px;"
				src="/static/shop/images/product/nieuw.png" alt=""></div>			</div>
			<#break>
		</#if>
	</#list>
</#if>
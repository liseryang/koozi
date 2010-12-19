<#setting number_format="0">
<#macro print lproductPrice>
${lproductPrice.amount}
<#if lproductPrice.currency.currencyCode == "EUR">
	&euro;
</#if>
<#if lproductPrice.currency.currencyCode == "GBP">
	£
</#if>
</#macro>

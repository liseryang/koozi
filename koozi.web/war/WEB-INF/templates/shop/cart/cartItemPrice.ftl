<#setting number_format="0">
<#import "/default/product/currency.ftl" as currency>
<#import "/spring.ftl" as spring>
<#if price??>
	<@currency.print  price/>
<#else>
	<@spring.message "product.noPrice"/>
</#if>

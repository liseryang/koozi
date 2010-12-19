<#setting number_format="0">
<#import "/default/product/currency.ftl" as currency>
<ul>
	<#list priceList as price>
			<li><@currency.print  price/></li>
	</#list>
</ul>


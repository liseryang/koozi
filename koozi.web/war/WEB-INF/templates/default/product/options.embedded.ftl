<#setting number_format="0">
<#import "/default/product/currency.ftl" as currency>

<#list optionMap?keys as optionKey>
	<h4>Edit ${optionMap[optionKey][0].type} option <b>"${optionKey}"</b><h4>
	<#list optionMap[optionKey] as option>
		${myContext.fetch(base + "/products/" +  option.productId + "/options/" + option.id + "/prices")}
		<#assign priceList =  myContext.getModelObject("priceList")>
	
		<h5>${option.value} (${option.type})</h5>
		<ul>
			<li>Stock: ${option.stock}</li>
			<li>Type: ${option.type}</li>
			<#list priceList as price>
				<li>Price ${price.currency}: + <@currency.print  price/>
				</li>
			</#list>
		</ul>
	</#list>
</#list>


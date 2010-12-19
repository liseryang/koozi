<#setting number_format="0">
<#import "/default/product/currency.ftl" as currency>
<#list optionMap?keys as optionKey>
	<h4>Edit option <b>"${optionKey}"</b><h4>
	<#list optionMap[optionKey] as option>
		<h5>${option.value} (${option.type})</h5>
		<ul>
			<li>Stock: ${option.stock}</li>
		</ul>
	</#list>
</#list>


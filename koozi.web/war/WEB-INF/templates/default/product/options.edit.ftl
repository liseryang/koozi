<#setting number_format="0">
<#import "/default/product/currency.ftl" as currency>

<a href="${base}/products">products</a>
-
<a href="${base}/products/${productId}">product</a>
-
<a href="${base}/products/${productId}/options">opions</a>

<h3>Product</h3>
<@include_page path="${base}/products/${productId}/metadata/default" inherit_params=true params={"view": "embedded"}/> 
<@include_page path="${base}/products/${productId}" inherit_params=true params={"view":"embedded"}/>

<h3>Edit product option</h3>

<#list optionMap?keys as optionKey>
	<h3>Edit option <b>"${optionKey}"</b></h3>
	<#list optionMap[optionKey] as option>
		<h4>${option.value}</h4>
		<ul>
			<li>Stock: ${option.stock}</li>
		</ul>
		<form action="${base}/products/${option.productId}/options/${option.id}" method="get">
			<input type="submit"  value="Edit product option"/>
			<input type="hidden" name="view" value="edit"/>
		</form>
	</#list>
</#list>

<h3>Add product option</h3>
<form action="${base}/products/${productId}/options" method="post">
<ul>
	<li>Key: <input name="key" type="input" /></li>
	<li>Type: 		
		<select name="type">
	        <option value="nl">select</option>
	        <option value="en">input</option>
    	</select>
	</li>
	<li>Value: <input name="value" type="input" /></li>
	<li>Stock: <input name="stock" type="input" /></li>

</ul>
	<input type="submit" value="Add option" />
</form>

<#setting number_format="0">
<a href="${base}/products">products</a> - 
<a href="${base}/products/${option.productId}">product</a> -
<a href="${base}/products/${option.productId}/options">options	</a> -
<a href="${base}/products/${option.productId}/options/${option.id}">${option.id}</a>

<h3>Product</h3>
 <@include_page path="${base}/products/${option.productId}" inherit_params=true  params={"view": "embedded"}/>

<h3>Edit option "<b>${option.key}</b>"</h3>

<form action="${base}/products/${option.productId}/options/${option.id}" method="post">
	<input type="hidden" name="_method" value="put"/>
	<li>Key: <input name="key" type="input" value="${option.key}" /></li>
	<li>Type: 		
		<select name="type">
	        <option value="select">select</option>
	        <option value="input">input</option>
    	</select>
	</li>
	<li>Value: <input name="value" type="input"  value="${option.value}"/></li>
	<li>Stock: <input name="stock" type="input" value="${option.stock}"/></li>
	</ul>
	<input type="submit"  value="Update option"/>
</form>

<form action="${base}/products/${option.productId}/options/${option.id}" method="post">
	<input type="hidden" name="_method" value="delete"/>
	<input type="submit"  value="Delete metadata"/>
</form>
<form action="${base}/products/${option.productId}/options" method="get">
	<input type="submit"  value="Cancel edit"/>
	<input type="hidden" name="view" value="edit"/>
</form>

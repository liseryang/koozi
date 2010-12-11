<h3>Product</h3>
<#if product??>
	<input type="hidden" name="_method" value="put"/>
	<ul>
		<li>Code: ${product.code}</li>
		<li>Description: ${product.description}</li>
		<li>Name: ${product.name}</li>
		<#if product.thumbnail?? && product.thumbnail != "">
			<img style="height:100px" border="1" src="${product.thumbnail}"/>
		</#if>
	</ul>


	<form action="${base}/products/${product.id}" method="get">
		<input type="submit"  value="Edit product"/>
		<input type="hidden" name="view" value="edit"/>
	</form>


	<form action="${base}/products/${product.id}" method="post">
		<input type="hidden" name="_method" value="delete"/>
		<div>
			<input type="submit"  value="Delete product"/>
		</div>
		
	</form>
</#if>
<@include_page path="${base}/products/${product.id}/tags" inherit_params=true/>

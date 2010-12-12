<h3>Products</h3>
<#if productList??>
	<ul>
		<#list productList as product>
			<li><a href="${base}/products/${product.id}">${product.code}</a></li>
		</#list>
	</ul>
	<form action="${base}/products/new" method="get">
			<input type="submit"  value="Add product"/>
	</form>
</#if>
	
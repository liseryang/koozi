<#setting number_format="0">
<#setting number_format="0">
<a href="${base}/products">products</a> - 
<#if product??>
	<a href="${base}/products/${product.id}">product</a>
</#if>
<#if product??>
	<h3>Update product</h3>
	<form action="${base}/products/${product.id}" method="post">
		<input type="hidden" name="_method" value="put"/>
		<ul>
			<li>Code: <input name="code" value="${product.code}"/></li>
			<li>Collection: <input name="collection" value="${product.collection}"/></li>
			<li>Stock:<input name="stock" value="${product.stock}"/> </li>
			<li>Thumbnail: <input name="thumbnail" value="${product.thumbnail}"/></li>
			
			<#if product.thumbnail??>
				<img style="height:100px" border="1" src="${product.thumbnail}"/>
			</#if>
		</ul>
		<input type="submit"  value="Save product"/>
	</form>
	<form action="${base}/products/${product.id}" method="post">
		<input type="hidden" name="_method" value="delete"/>
		<input type="submit"  value="Delete product"/>
	</form>
	<form action="${base}/products/${product.id}" method="get">
		<input type="submit"  value="Cancel edit"/>
	</form>
<#else>
<h3>Create product</h3>
	<form action="${base}/products" method="post">
		<ul>
			<li>Code: <input name="code" value=""/></li>
			<li>Collection: <input name="collection" value=""/></li>
			<li>Stock:<input name="stock" value=""/> </li>
			<li>Thumbnail: <input name="thumbnail" value=""/></li>
			
		</ul>
		<input type="submit"  value="Add product"/>
		
	</form>
	<form action="${base}/products" method="get">
		<input type="submit"  value="Cancel"/>
	</form>
</#if>


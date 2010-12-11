<#if product??>
	<#if product.id??>
		<h3>Update product</h3>
		<form action="${base}/products/${product.id}" method="post">
			<input type="hidden" name="_method" value="put"/>
			<ul>
				<li>Code: <input name="code" value="${product.code}"/></li>
				<li>Description: <input name="description" value="${product.description}"/></li>
				<li>Name: <input name="name" value="${product.name}"/></li>
				
				<li>Thumbnail: <input name="thumbnail" value="${product.thumbnail}"/></li>
				<#if product.thumbnail??>
					<img style="height:100px" border="1" src="${product.thumbnail}"/>
				</#if>
			</ul>
			<div>
				<input type="submit"  value="Save product"/>
			</div>
		</form>

		<form action="${base}/products" method="get">
				<div>
					<input type="submit"  value="Cancel edit"/>
				</div>
		</form>
		<@include_page path="${base}/products/${product.id}/tags" inherit_params=true/>
	<#else>
	<h3>Create product</h3>
		<form action="${base}/products" method="post">
			<ul>
				<li>Code: <input name="code" value=""/></li>
				<li>Description: <input name="description" value=""/></li>
				<li>Name: <input name="name" value=""/></li>
				<li>Thumbnail: <input name="thumbnail" value=""/></li>
			</ul>
			<input type="submit"  value="Add product"/>
			
		</form>
		<form action="${base}/products" method="get">
			<input type="submit"  value="Cancel"/>
		</form>
	</#if>
</#if>	


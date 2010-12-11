<#if product??>
	<div>
		<div>
			<img style="height:100px" border="1" src="${product.thumbnail}"/>
		</div>
		<div>
			Name: <a href="${base}/products/${product.id}">${product.name}</a>
		</div>
	</div>
</#if>	


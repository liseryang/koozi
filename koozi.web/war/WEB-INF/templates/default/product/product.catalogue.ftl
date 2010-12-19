<#setting number_format="0">
<#if product??>
	<a href="${base}/products/${product.id}"><div>
		<div>
			<img style="height:100px" border="1" src="${product.thumbnail}"/>
		</div>
		<div>
			${product.code}
		</div>
	</div></a>
</#if>	


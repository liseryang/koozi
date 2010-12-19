<#setting number_format="0">
<#if product??>
	<a href="${base}/products/${product.id}" rel="${product.thumbnail}" title="Klik hier voor meer info over ${product.code}" alt="Koozi ${product.code}- Hippe benodigdheden voor je prematuur in de couveuse.">
		<img  src="${product.thumbnail}" />
	</a> 																		
</#if>

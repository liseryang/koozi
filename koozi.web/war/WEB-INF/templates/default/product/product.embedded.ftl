<#setting number_format="0">
<#setting number_format="0">
<ul>
	<li>Code: ${product.code}</li>
	<li>Collection: ${product.collection}</li>
	<li>Stock: ${product.stock}</li>
	<#if product.thumbnail?? && product.thumbnail != "">
		<br/>
		<img style="height:100px" border="1" src="${product.thumbnail}"/>
	</#if>
</ul>


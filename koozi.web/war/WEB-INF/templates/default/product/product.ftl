 <a href="${base}/products">products</a> - 
<a href="${base}/products/${product.id}">product</a>

<h3>Product</h3>
<@include_page path="${base}/products/${product.id}/metadata/default" inherit_params=true  params={"view": "embedded"}/>
<form action="${base}/products/${product.id}/metadata" method="get">
	<input type="submit"  value="Edit product metadata"/>
	<input type="hidden" name="view" value="edit"/>
</form>
<#include "/default/product/product.embedded.ftl">
<form action="${base}/products/${product.id}" method="get">
	<input type="submit"  value="Edit product"/>
	<input type="hidden" name="view" value="edit"/>
</form>
<h3>Tags</h3>
<@include_page path="${base}/products/${product.id}/tags" inherit_params=true  params={"view": "embedded"}/>
<form action="${base}/products/${product.id}/tags" method="get">
	<input type="submit"  value="Edit product tags"/>
	<input type="hidden" name="view" value="edit"/>
</form>
<h3>Pictures</h3>
<@include_page path="${base}/products/${product.id}/pictures" inherit_params=true  params={"view": "embedded"}/>
<form action="${base}/products/${product.id}/pictures" method="get">
	<input type="submit"  value="Edit product pictures"/>
	<input type="hidden" name="view" value="edit"/>
</form>
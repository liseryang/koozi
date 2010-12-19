<#setting number_format="0">
<a href="${base}/products">products</a> - 
<a href="${base}/products/${metadata.productId}">product</a> -
<a href="${base}/products/${metadata.productId}/metadata">metadata	</a> -
<a href="${base}/products/${metadata.productId}/metadata/${metadata.locale}">${metadata.locale}</a>

<h3>Product</h3>
 <@include_page path="${base}/products/${metadata.productId}" inherit_params=true  params={"view": "embedded"}/>

<h3>Edit Metadata "<b>${metadata.locale}</b>"</h3>
<form action="${base}/products/${metadata.productId}/metadata/${metadata.locale}" method="post">
	<input type="hidden" name="_method" value="put"/>
	<ul>
		<li>Name: <input type="text" name="name" value="${metadata.name}"/></li>
		<li>Description: <input type="text" name="description" value="${metadata.description}"/></li>
	</ul>
	<input type="submit"  value="Save metadata"/>
</form>
<form name="deleteMetadata" action="${base}/products/${metadata.productId}/metadata/${metadata.locale}" method="post">
	<input type="hidden" name="_method" value="delete"/>
	<input type="submit"  value="Delete metadata"/>
</form>
<form action="${base}/products/${metadata.productId}/metadata" method="get">
	<input type="submit"  value="Cancel edit"/>
	<input type="hidden" name="view" value="edit"/>
</form>
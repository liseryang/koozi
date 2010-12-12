<a href="${base}/products">products</a> - 
<a href="${base}/products/${productMetadata.productId}">product</a> -
<a href="${base}/products/${productMetadata.productId}/metadata">metadata	</a> -
<a href="${base}/products/${productMetadata.productId}/metadata/${productMetadata.locale}">${productMetadata.locale}</a>

<h3>Product</h3>
 <@include_page path="${base}/products/${productMetadata.productId}" inherit_params=true  params={"view": "embedded"}/>

<h3>Edit Metadata "<b>${productMetadata.locale}</b>"</h3>
<form action="${base}/products/${productMetadata.productId}/metadata/${productMetadata.locale}" method="post">
	<input type="hidden" name="_method" value="put"/>
	<ul>
		<li>Name: <input type="text" name="name" value="${productMetadata.name}"/></li>
		<li>Description: <input type="text" name="description" value="${productMetadata.description}"/></li>
	</ul>
	<input type="submit"  value="Save metadata"/>
</form>
<form name="deleteMetadata" action="${base}/products/${productMetadata.productId}/metadata/${productMetadata.locale}" method="post">
	<input type="hidden" name="_method" value="delete"/>
	<input type="submit"  value="Delete metadata"/>
</form>
<form action="${base}/products/${productMetadata.productId}/metadata" method="get">
	<input type="submit"  value="Cancel edit"/>
	<input type="hidden" name="view" value="edit"/>
</form>
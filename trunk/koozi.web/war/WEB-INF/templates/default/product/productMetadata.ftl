<a href="${base}/products">products</a> - 
<a href="${base}/products/${productMetadata.productId}">product</a> -
<a href="${base}/products/${productMetadata.productId}/metadata">metadata	</a> -
<a href="${base}/products/${productMetadata.productId}/metadata/${productMetadata.locale}">${productMetadata.locale}</a>

<@include_page path="${base}/products/${productMetadata.productId}/metadata" inherit_params=true  params={"view": "edit"}/>

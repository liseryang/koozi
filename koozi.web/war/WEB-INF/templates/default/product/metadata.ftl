<#setting number_format="0">
<a href="${base}/products">products</a> - 
<a href="${base}/products/${metadata.productId}">product</a> -
<a href="${base}/products/${metadata.productId}/metadata">metadata	</a> -
<a href="${base}/products/${metadata.productId}/metadata/${metadata.locale}">${metadata.locale}</a>

<@include_page path="${base}/products/${metadata.productId}/metadatas" inherit_params=true  params={"view": "edit"}/>

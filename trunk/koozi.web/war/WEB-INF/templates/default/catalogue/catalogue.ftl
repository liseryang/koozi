 <a href="${base}/catalogue">catalogues</a> - 
<a href="${base}/products/${tag}">${tag}</a>

<h3>Catalogue ${tag}</h3>
<#list productTagList as productTag>
	<@include_page path="${base}/products/${productTag.productId}" inherit_params=true params={"view": "catalogue"}/>
</#list>

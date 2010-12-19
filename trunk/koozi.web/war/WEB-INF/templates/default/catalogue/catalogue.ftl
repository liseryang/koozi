<#setting number_format="0">
 <a href="${base}/catalogue">catalogues</a> - 
<a href="${base}/products/${value}">${value}</a>

<h3>Catalogue ${value}</h3>
<#list tagList as tag>
	<@include_page path="${base}/products/${tag.productId}" inherit_params=true params={"view": "catalogue"}/>
</#list>

<#setting number_format="0">
<a href="${base}/products">products</a> - 
<a href="${base}/products/${productId}">product</a> -
<a href="${base}/products/${productId}/tags">tags</a>

<h3>Product</h3>
<@include_page path="${base}/products/${productId}/metadata/default" inherit_params=true  params={"view": "embedded"}/>
<@include_page path="${base}/products/${productId}" inherit_params=true  params={"view": "embedded"}/>

<h3>Edit product tags</h3>
<ul>
	<#list tagList as tag>
		<li>
		<form name="deleteTag" action="${base}/products/${productId}/tags/${tag.id}" method="post">
				<a href="${base}/catalogues/${tag.value}">${tag.value}</a>
				<input type="hidden" name="_method" value="delete"/>
				<input type="submit"  value="Delete tag"/>
			</form>
		</li>
	</#list>
</ul>

<form action="${base}/products/${productId}/tags" method="post">
		<div>
			<input name="value" value=""/>
			<input type="submit"  value="Add tag"/>
		</div>
</form>
<form action="${base}/products/${productId}/tags" method="post">
		<div>
			<input type="hidden" name="value" value="nieuw"/>
			<input type="submit"  value="Add tag 'nieuw'"/>
		</div>
</form>
<form action="${base}/products/${productId}/tags" method="post">
		<div>
			<input type="hidden" name="value" value="promo"/>
			<input type="submit"  value="Add tag 'promo'"/>
		</div>
</form>
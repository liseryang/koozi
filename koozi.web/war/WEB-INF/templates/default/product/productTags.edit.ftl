<a href="${base}/products">products</a> - 
<a href="${base}/products/${productId}">product</a> -
<a href="${base}/products/${productId}/tags">tags</a>

<h3>Product</h3>
<@include_page path="${base}/products/${productId}/metadata/default" inherit_params=true  params={"view": "embedded"}/>
<@include_page path="${base}/products/${productId}" inherit_params=true  params={"view": "embedded"}/>

<h3>Edit product tags</h3>
<#if productTagList??>
	<ul>
		<#list productTagList as productTag>
			<li>
			<form name="deleteTag" action="${base}/products/${productId}/tags/${productTag.id}" method="post">
					<a href="${base}/catalogues/${productTag.tag}">${productTag.tag}</a>
					<input type="hidden" name="_method" value="delete"/>
					<input type="submit"  value="Delete tag"/>
				</form>
			</li>
		</#list>
	</ul>
</#if>

<form action="${base}/products/${productId}/tags" method="post">
		<div>
			<input name="tag" value=""/>
			<input type="submit"  value="Add tag"/>
		</div>
</form>

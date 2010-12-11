<h3>Tags</h3>
<#if productTagList??>
	<#list productTagList as productTag>
		<a href="${base}/catalogues/${productTag.tag}">${productTag.tag}</a>
		<form name="deleteTag" action="${base}/products/${productId}/tags/${productTag.id}" method="post">
			<input type="hidden" name="_method" value="delete"/>
			<div>
				<input type="submit"  value="Delete tag"/>
			</div>

		</form>
	</#list>
</#if>

<form action="${base}/products/${productId}/tags" method="post">
		<div>
			<input name="tag" value=""/>
			<input type="submit"  value="Add tag"/>
		</div>
</form>
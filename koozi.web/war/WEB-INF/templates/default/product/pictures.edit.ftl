<#setting number_format="0">
<a href="${base}/products">products</a> - 
<a href="${base}/products/${productId}">product</a> -
<a href="${base}/products/${productId}/pictures">pictures</a>

<h3>Product</h3>
<@include_page path="${base}/products/${productId}/metadata/default" inherit_params=true  params={"view": "embedded"}/>
<@include_page path="${base}/products/${productId}" inherit_params=true  params={"view": "embedded"}/>
<h3>Edit product pictures</h3>
<#if pictureList??>
	<ul>
		<#list pictureList as picture>
			<li>
			<form name="deleteTag" action="${base}/products/${productId}/pictures/${picture.id}" method="post">
					<img style="height:100px" border="1" src="${picture.href}"/>
					<input type="hidden" name="_method" value="delete"/>
					<input type="submit"  value="Delete picture ${picture.id}"/>
				</form>
			</li>
		</#list>
	</ul>
</#if>

<form action="${base}/products/${productId}/pictures" method="post">
	<input name="href" value=""/>
	<input type="submit"  value="Add picture"/>
</form>

<#if productTagList??>
	<form accept-charset="UTF-8" method="POST" action="http://www.koozi.be/PublishedService?" name="productSpotForm" enctype="application/x-www-form-urlencoded">
		<input name="formName" type="hidden" value="productSpotForm"><input name="file" type="hidden" value="">
		<input name="formName" type="hidden" value="productSpotForm"><input name="file" type="hidden" value="">
		<div class="productList">
			<table margin="0" cellpadding="0" cellspacing="0" padding="0" border="0" class="productList">
					<tbody>
						<#list productTagList as productTag>
							<#if productTag_index % 3 == 0>
								<tr>
							</#if>
							<td padding="0" margin="0" cellpadding="0" cellmargin="0" border="0" width="30%">
								<@include_page path="${base}/products/${productTag.productId}" inherit_params=false params={"view": "thumbnail"} />
							</td>
							<#if productTag_index % 3 == 2>
								</tr>
							</#if>
						</#list>
					</tbody>
			</table>
		</div>
	</form>
</#if>
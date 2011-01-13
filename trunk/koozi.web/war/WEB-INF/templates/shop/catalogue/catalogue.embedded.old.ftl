<#setting number_format="0">
<#if tagList??>
		<div id="catalogue" class="productList">
			<table margin="0" cellpadding="0" cellspacing="0" padding="0" border="0" class="productList">
					<tbody>
						<#list tagList as tag>
							<#if tag_index % 3 == 0>
								<tr>
							</#if>
							<td padding="0" margin="0" cellpadding="0" cellmargin="0" border="0" width="30%">
								<@include_page path="${base}/products/${tag.productId}" inherit_params=false params={"view": "thumbnail"} />
							</td>
							<#if tag_index % 3 == 2>
								</tr>
							</#if>
						</#list>
					</tbody>
			</table>
		</div>
</#if>
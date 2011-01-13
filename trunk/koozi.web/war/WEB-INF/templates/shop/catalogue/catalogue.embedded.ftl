<#setting number_format="0">
<#if tagList??>
    <div class="containerList_3_rows containerList">
	<#list tagList as tag>
			<@include_page path="${base}/products/${tag.productId}" inherit_params=false params={"view": "thumbnail"} />
			<#if tag_index % 3 == 2>
				<div class="end"></div>
			</#if>
	</#list>
	<div class="end"></div>
	</div>
</#if>
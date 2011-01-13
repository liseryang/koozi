<#setting number_format="0">
<div class="productItemPictureTumbList">
	<#list pictureList as picture>
		<#if picture_index  != 0>
			<div class="productItemPictureTumb">
				<a class="Thumb MagicThumb" href="${picture.href}"><img src="${picture.href}"><span class="MagicThumb-Description"></span></img></a> 
			</div>
		</#if>
	</#list>
</div>

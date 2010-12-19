<#setting number_format="0">
<ul>
	<div id="productItemPictureTumb">
		<#list pictureList as picture>
			<#if picture_index  != 0>
				<a class="Thumb MagicThumb" href="${picture.href}"><img src="${picture.href}"><span class="MagicThumb-Description"></span></img></a> 
			</#if>
		</#list>
	</div>
</ul>

<ul>
	<#list pictureList as picture>
		<#if picture_index  != 0>
			<div id="productItemPictureTumb">
				<a class="Thumb MagicThumb"
				href="${picture.href}">
					<img src="${picture.href}">
						<span class="MagicThumb-Description">
							<!-- <h2 class="title">picture</h2> -->
						</span>
					</img>
				</a> 
			</div>
		</#if>
	</#list>
</ul>

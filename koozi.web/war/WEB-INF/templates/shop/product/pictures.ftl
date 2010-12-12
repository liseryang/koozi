<#list pictureList as picture>
	<div id="productItemPicture">
		<a class="ThumbLarge  MagicThumb" href="${picture.href}">
			<img src="${picture.href}">
				<span class="MagicThumb-Description">
					<h2 class="title">xxx</h2>
				</span>
			</img>
		</a>
	</div>
	<#break>
</#list>



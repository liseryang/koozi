<#if productTagList??>
	<form accept-charset="UTF-8" method="POST" action="http://www.koozi.be/PublishedService?" name="productSpotForm" enctype="application/x-www-form-urlencoded"><input name="formName" type="hidden" value="productSpotForm"><input name="file" type="hidden" value="">
		<div   class="MagicSlideshow">
			<a rel="http://www.koozi.be/pictures/Logo_220.png" alt="Koozi - Hippe benodigdheden voor je prematuur in de couveuse."  >
				<img  src="http://www.koozi.be/pictures/Logo_220.png" />
			</a>
			<#list productTagList as productTag>
				<@include_page path="${base}/products/${productTag.productId}" inherit_params=false params={"view": "slides"}/>
			</#list>
		</div>
	</form>
</#if>

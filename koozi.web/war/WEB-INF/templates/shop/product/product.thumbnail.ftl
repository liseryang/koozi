<div id="productListItemContainer">
	<div class="innerPanel" id="productListItemBorder" name="productListItemBorder">
		<div id="productListItem">
			<h3 class="title">
			<a alt="" title="Meer info over ${product.code}" href="${base}/products/${product.id}">${product.code}</a></h3>
			<div class="picture" id="productListItemNamePicture">
				<a alt="${product.code}- Hippe benodigdheden voor je prematuur in de couveuse." title="Meer info over ${product.code}" href="${base}/products/${product.id}">
					<img alt="Koozi Dekentje Aiko- Hippe benodigdheden voor je prematuur in de couveuse." title="Meer info over ${product.code}" src="${product.thumbnail}">
				</a>
			</div>
			<@include_page path="${base}/products/${product.id}/tags" inherit_params=false params={"view": "label.thumbnail"}/>
			<div id="productListItemLeesMeer">
				<a alt="Koozi ${product.code} - Hippe benodigdheden voor je prematuur in de couveuse." title="Meer info over ${product.code}" dekentje="" aiko"="" class="productListItemLink" href="${base}/products/${product.id}">Meer info</a>
			</div>
			<div class="price" id="productListItemPrice">13,00&euro;</div>
			<div id="productListItemEnd"></div>
		</div>
	</div>
</div>

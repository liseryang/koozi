<div id="productListItemContainer">
	<div class="innerPanel" id="productListItemBorder" name="productListItemBorder">
		<div id="productListItem">
			<h3 class="title">
			<a alt="${product.description}" title="Meer info over ${product.name}" href="${base}/products/${product.id}">${product.name}</a></h3>
			<div class="picture" id="productListItemNamePicture">
				<a alt="${product.name}- Hippe benodigdheden voor je prematuur in de couveuse." title="Meer info over ${product.name}" href="${base}/products/${product.id}">
					<img alt="Koozi Dekentje Aiko- Hippe benodigdheden voor je prematuur in de couveuse." title="Meer info over ${product.name}" src="${product.thumbnail}">
				</a>
			</div>
			<@include_page path="${base}/products/${product.id}/tags" inherit_params=false params={"view": "label"}/>
			<div id="productListItemLeesMeer">
				<a alt="Koozi ${product.name} - Hippe benodigdheden voor je prematuur in de couveuse." title="Meer info over ${product.name}" dekentje="" aiko"="" class="productListItemLink" href="${base}/products/${product.id}">Meer info</a>
			</div>
			<div class="price" id="productListItemPrice">13,00</div>
			<div id="productListItemEnd"></div>
		</div>
	</div>
</div>

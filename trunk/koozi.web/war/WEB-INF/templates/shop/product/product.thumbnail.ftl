<#setting number_format="0">
<#import "/spring.ftl" as spring>

${myContext.fetch(base + "/products/" +  product.id + "/metadata/locale")}
<#assign metadata =  myContext.getModelObject("metadata")>


<div id="productListItemContainer">
	<div class="innerPanel" id="productListItemBorder" name="productListItemBorder">
		<div id="productListItem">
			<h3 class="title">
			<a alt="" title="Meer info over ${metadata.name}" href="${base}/products/${product.id}">${metadata.name}</a></h3>
			<div class="picture" id="productListItemNamePicture">
				<a alt="${product.code}- Hippe benodigdheden voor je prematuur in de couveuse." title="Meer info over ${product.code}" href="${base}/products/${product.id}">
					<img alt="Koozi Dekentje Aiko- Hippe benodigdheden voor je prematuur in de couveuse." title="Meer info over ${product.code}" src="${product.thumbnail}">
				</a>
			</div>
			<@include_page path="${base}/products/${product.id}/tags" inherit_params=false params={"view": "label.thumbnail"}/>
			<div id="productListItemLeesMeer">
				<a alt="Koozi ${product.code} - Hippe benodigdheden voor je prematuur in de couveuse." title="Meer info over ${product.code}" dekentje="" aiko"="" class="productListItemLink" href="${base}/products/${product.id}"><@spring.message "product.moreInfo"/></a>
			</div>
			<div class="price" id="productListItemPrice"><@include_page path="${base}/products/${product.id}/prices/locale" inherit_params=false/></div>
			<div id="productListItemEnd"></div>
		</div>
	</div>
</div>

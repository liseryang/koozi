<#setting number_format="0">
<#import "/spring.ftl" as spring>
<#if product??>
	${myContext.fetch(base + "/products/" +  product.id + "/metadata/locale")}
	<#assign metadata =  myContext.getModelObject("metadata")>
	
	<#if metadata != "null">
		<#assign metadataName = metadata.name>
	<#else>	
		<#assign metadataName = product.code>
	</#if>	
	
	
	<div class="container productListItem">
		<div class="containerBorder">
				<div class="productListTitle" >
					<h3 class="title top">
						<a title="Meer info over ${metadataName}" href="${base}/products/${product.id}">${metadataName}</a>
					</h3>
				</div>	
				<div class="productListPicture" >
					<a alt="${product.code}- Hippe benodigdheden voor je prematuur in de couveuse." title="Meer info over ${product.code}" href="${base}/products/${product.id}">
						<img alt="Koozi Dekentje Aiko- Hippe benodigdheden voor je prematuur in de couveuse." title="Meer info over ${product.code}" src="${product.thumbnail}">
					</a>
				</div>
				<@include_page path="${base}/products/${product.id}/tags" inherit_params=false params={"view": "label.thumbnail"}/>
				
				<div id="productListItemLeesMeer">
					<a alt="Koozi ${product.code} - Hippe benodigdheden voor je prematuur in de couveuse." title="Meer info over ${product.code}" dekentje="" aiko"="" class="productListItemLink" href="${base}/products/${product.id}"><@spring.message "product.moreInfo"/></a>
				</div>
				<div class="price" id="productListItemPrice"  class="price productThumbnailBottom"><@include_page path="${base}/products/${product.id}/prices/locale" inherit_params=false/></div>
				<div id="productListItemEnd"></div>
		</div>
	</div>
</#if>
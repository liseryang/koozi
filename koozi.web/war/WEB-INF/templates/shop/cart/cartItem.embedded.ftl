<#setting number_format="0">
	<#setting number_format="0">
<#import "/spring.ftl" as spring>

${myContext.fetch(base + "/products/" +  cartItem.productId)}
${myContext.fetch(base + "/products/" +  cartItem.productId + "/metadata/locale")}
${myContext.fetch(base + "/products/" +  cartItem.productId + "/pictures/")}

<#assign product =  myContext.getModelObject("product")>
<#assign metadata =  myContext.getModelObject("metadata")>
<#assign pictureList =  myContext.getModelObject("pictureList")>

<div id="cartItemContainer">
	<div class="innerPanel"  id="cartItemBorder">
		<div id="cartItem">
			<div class="picture" id="productItemPicture"> 
				<div class="picture" id="productListItemNamePicture"><a  href="${base}/products/${cartItem.productId}" alt="Koozi Knuffel Sterre III- Hippe benodigdheden voor je prematuur in de couveuse." title="Meer info over Knuffel Sterre III" Knuffel Sterre III"><img alt="Koozi Knuffel Sterre III- Hippe benodigdheden voor je prematuur in de couveuse." title="Meer info over Knuffel Sterre III" src="${product.thumbnail}" /></a>
				</div>
			</div>
			<h3 class="title"><a  href="${base}/products/${cartItem.productId}">${metadata.name}</a></h3>
			<input type="hidden" id="KNUFFEL-STERRE3" value="KNUFFEL-STERRE3"/>
			
			<div id="cartItemProperties">
				<table class="cartItem">
 					<tr class="cartItem">
						<td class="key cartItem" >
							<@spring.message "product.code"/>
     					</td>
      					<td class="cartItem value" id="productItemCode">
							${product.code}
      					</td>
					</tr>
   					<tr class="cartItem">
	      				<td class="key cartItem" >
	         				<@spring.message "product.amount"/>
	     				</td>
						<td class="cartItem value" id="productItemAantal">
							${cartItem.amount}
						</td>
   					</tr>

					 <!--  <tr class="cartItem">
					      <td class="key cartItem" >
					          Opties:
					      </td>
					      <td class="cartItem value" id="productItemOptions">
					      </td>
					   </tr>-->

   					<tr class="cartItem">
      					<td class="key cartItem">
							<@spring.message "product.price"/>
						</td>
						<td class="cartItem value price" id="productItemPrijs">
					        <@include_page path="${base}/products/${product.id}/prices/locale" inherit_params=false/>  
						</td>
					</tr>
   					<tr class="cartItem">
						<td colspan="2" class="cartItem">
							<a class="productListItemLink"  href="${base}/products/ ${cartItem.productId}"><@spring.message "product.moreInfo"/></a>
						</td>
					</tr>
				</table>
			</div>

			<div id="productItemDelete">
				<form action="${base}/cart/myCart/cartItems/${cartItem.id}" method="post" name="deleteCartItemForm_${cartItem.id}">
					<input type="hidden" name="_method" value="delete"/>
					<a class="button normal" href="javascript:document.deleteCartItemForm_${cartItem.id}.submit()"><@spring.message "cart.delete"/></a>
				</form>
			</div>
			<div class="end"></div>
		</div>
	</div>
</div>

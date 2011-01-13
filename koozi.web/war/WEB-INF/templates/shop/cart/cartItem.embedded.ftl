<#setting number_format="0">
<#import "/spring.ftl" as spring>

${myContext.fetch(base + "/products/" +  cartItem.productId)}
${myContext.fetch(base + "/products/" +  cartItem.productId + "/metadata/locale")}
${myContext.fetch(base + "/products/" +  cartItem.productId + "/pictures/")}
${myContext.fetch(base + "/products/" +  cartItem.productId + "/options/")}
${myContext.fetch(base + "/cart/myCart/cartItems/" +  cartItem.id + "/optionValues")}

<#assign product =  myContext.getModelObject("product")>
<#assign metadata =  myContext.getModelObject("metadata")>
<#assign pictureList =  myContext.getModelObject("pictureList")>
<#assign optionList =  myContext.getModelObject("optionList")>
<#assign optionValueList =  myContext.getModelObject("optionValueList")>

<#if metadata != "null">
	<#assign metadataName = metadata.name>
<#else>	
	<#assign metadataName = product.code>
</#if>	

<div class="container cartListItem">
	<div class="containerBorder">
			<div class="productListPicture"  style="float:left; width:128px; height:100%; padding-left:10px; padding-right:10px"> 
				<a  href="${base}/products/${cartItem.productId}" alt="Koozi Knuffel Sterre III- Hippe benodigdheden voor je prematuur in de couveuse." title="Meer info over Knuffel Sterre III" Knuffel Sterre III"><img alt="Koozi Knuffel Sterre III- Hippe benodigdheden voor je prematuur in de couveuse." title="Meer info over Knuffel Sterre III" src="${product.thumbnail}" /></a>
			</div>
			<h3 class="title top"><a  href="${base}/products/${cartItem.productId}">${metadataName}</a></h3>
			<input type="hidden" id="KNUFFEL-STERRE3" value="KNUFFEL-STERRE3"/>
			<div id="cartItemProperties" style="float:left">
				<table >
					<#list optionList as option>
						<#list optionValueList as optionValue>
							<#if optionValue.optionId == option.id>
								<tr class="productItem">
									<td class="key cartItem"><@spring.messageText "product.option.value.${option.key}" "${option.key}"/>:</td>	
									<td class="value cartItem">
										<@spring.messageText "product.option.value.${option.key}.${option.value}" "${option.value}"/>
									</td>
								</tr>
							</#if>
						</#list>
					</#list>
				</table>
			</div>
			<div id="cartItemProperties" style="float:right">
				<table class="cartItem" >
 				<!--  <tr class="cartItem">
						<td class="key cartItem" >
							<@spring.message "product.code"/>
     					</td>
      					<td class="cartItem value" id="productItemCode">
							${product.code}
      					</td>
					</tr>-->	
   					<tr class="cartItem">
	      				<td class="key cartItem" >
	         				<@spring.message "product.amount"/>:
	     				</td>
						<td class="cartItem value" id="productItemAantal">
							${cartItem.amount}
						</td>
   					</tr>
   					<tr class="cartItem">
      					<td class="key cartItem">
							<@spring.message "product.price"/>:
						</td>
						<td class="cartItem value price" id="productItemPrijs">
					        <@include_page path="${base}/cart/myCart/cartItems/" +  cartItem.id + "/prices/locale" inherit_params=false/>  
						</td>
					</tr>
				</table>
			</div>
			<div class="end"></div>
			<div id="productListItemLeesMeer" style="float:left">
				<a class="productListItemLink"  href="${base}/products/ ${cartItem.productId}"><@spring.message "product.moreInfo"/></a>
			</div>
			<div id="productItemDelete"  style="float:right">
				<form action="${base}/cart/myCart/cartItems/${cartItem.id}" method="post" name="deleteCartItemForm_${cartItem.id}">
					<input type="hidden" name="_method" value="delete"/>
					<a class="button normal" href="javascript:document.deleteCartItemForm_${cartItem.id}.submit()"><@spring.message "cart.delete"/></a>
				</form>
			</div>
			<div class="end"></div>
			
	</div>
</div>
<div class="end"></div>

<#setting number_format="0">
<#import "/spring.ftl" as spring>
<#import "/default/product/currency.ftl" as currency>

${myContext.fetch(base + "/products/" +  orderItem.productId)}
${myContext.fetch(base + "/products/" +  orderItem.productId + "/metadata/locale")}
${myContext.fetch(base + "/products/" +  orderItem.productId + "/pictures/")}
${myContext.fetch(base + "/products/" +  orderItem.productId + "/options/")}
${myContext.fetch(base + "/orders/" + orderItem.orderId + "/orderItems/" +  orderItem.id + "/optionValues")}

<#assign product =  myContext.getModelObject("product")>
<#assign metadata =  myContext.getModelObject("metadata")>
<#assign pictureList =  myContext.getModelObject("pictureList")>
<#assign optionList =  myContext.getModelObject("optionList")>
<!-- <#assign optionValueList =  myContext.getModelObject("optionValueList")>-->

<#if metadata != "null">
	<#assign metadataName = metadata.name>
<#else>	
	<#assign metadataName = product.code>
</#if>	
<div class="picture" id="productlistitem" style="float:left; width:128px; height:100%; padding-left:10px; padding-right:10px"> 
	<div class="picture" id="productListItemNamePicture"><a  href="${base}/products/${orderItem.productId}" alt="Koozi Knuffel Sterre III- Hippe benodigdheden voor je prematuur in de couveuse." title="Meer info over Knuffel Sterre III" Knuffel Sterre III"><img alt="Koozi Knuffel Sterre III- Hippe benodigdheden voor je prematuur in de couveuse." title="Meer info over Knuffel Sterre III" src="${product.thumbnail}" /></a>
	</div>
</div>
<h3 class="title top"><a  href="${base}/products/${orderItem.productId}">${metadataName}</a></h3>
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
				${orderItem.amount}
			</td>
					</tr>
					<tr class="cartItem">
   					<td class="key cartItem">
				<@spring.message "product.price"/>:
			</td>
			<td class="cartItem value price" id="productItemPrijs">
				<#if orderItem.price??>
					<@currency.print  orderItem.price/>
				<#else>
					<@spring.message "product.noPrice"/>
				</#if>
			</td>
		</tr>
	</table>
</div>
<div class="end"></div>
<div id="productListItemLeesMeer" style="float:left">
	<a class="productListItemLink"  href="${base}/products/ ${orderItem.productId}"><@spring.message "product.moreInfo"/></a>
</div>
<div class="end"></div>

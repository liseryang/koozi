<#setting number_format="0">
<#import "/spring.ftl" as spring>
<#if cartItemList?size != 0  >
	<div class="cartSummary cartSummaryContainer">
		<div class="cartSummary border">
			<#if .locale == "en">
				<#assign cartHeader =  "_en">
			<#else>	
				<#assign cartHeader =  "">
			</#if>
			<a href="${base}/cart/myCart/cartItems" target=""><img width="150" src="/static/shop/images/cart/top${cartHeader}.gif" alt="winkelwagentje" border="0"></a><div class="cartSummary cartSummaryBody">
			
				<!-- <p>volgende items zitten<br/>
					in uw winkelwagentje:</p> -->
				<ul>	
					<#list cartItemList as cartItem>
						${myContext.fetch(base + "/products/" +  cartItem.productId + "/metadata/locale")}
						<#assign metadata =  myContext.getModelObject("metadata")>
						<li>
							<#if metadata != "null">
								<a href="${base}/products/${cartItem.productId}">${metadata.name}</a>
							<#else>	
								${myContext.fetch(base + "/products/" +  cartItem.productId)}
								<#assign product =  myContext.getModelObject("product")>
								<a href="${base}/products/${cartItem.productId}">${product.code}</a>
							</#if>	
							
						</li> 
					</#list>
				</ul> 
				<br/>
				Totaal: <b ><@include_page path="${base}/cart/myCart/prices/locale" inherit_params=false/> </b><br/><br/>							
	
				<div class="cartSummary orderButton">
					<a class="button normal cartSummary" href="${base}/cart/myCart/cartItems"><@spring.message "cart.order"/></a>
				</div>
			</div>
			<img  src="/static/shop/images/cart/bottom.gif"></div>
	</div>
</#if>
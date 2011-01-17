<#setting number_format="0">
<#import "/spring.ftl" as spring>
<h1 class="title"><@spring.message "cart.title"/></h1>

<#include "${base}/cart/cart.tip.ftl"/>

	<div class="container">
		<div class="containerBorder">
			<h3 class="title top"><@spring.message "order.products"/></h3> 
			<div class="containerSeperator top"></div>
			<#list cartItemList as cartItem>
				<#include "${base}/cart/cartItem.embedded.ftl"/>
				<#if cartItem_has_next>
					<div class="containerSeperator"></div>
				</#if>
			</#list>
		</div>
	</div>	
<div style="float:right; padding-top:10px; padding-bottom:10px;">
		<select onchange="javascript: window.location = this.value ; ">
		   <option value="${base}/catalogues/muts"><@spring.message "catalogue.name.muts"/></option>
		   <option value="${base}/catalogues/dekentje"><@spring.message "catalogue.name.dekentje"/></option>
		   <option value="${base}/catalogues/nestje"><@spring.message "catalogue.name.nestje"/></option>	   
		   <option value="${base}/catalogues/knuffel"><@spring.message "catalogue.name.knuffel"/></option>
		   <option value="${base}/catalogues/kleding"><@spring.message "catalogue.name.kleding"/></option>
		   <option value="${base}/catalogues/promoties"><@spring.message "catalogue.name.promoties"/></option>
		</select>
</div>
<div style="float:right; padding-top:20px; padding-bottom:10px; padding-right:10px;"><@spring.message "cart.continue.shopping"/></div>
<div class="end"></div>

<div class="container">
	<div class="containerBorder">
				<h3 class="title top"><@spring.message "cart.total"/></h3> 
				<div id="totaalValue" class="price" ><@include_page path="${base}/cart/myCart/prices/locale" inherit_params=false/>  </div>
				<div class="end"/>
				<div id="cartOrder" style="float:right">
					<div id="addPresentButton">
						<form accept-charset="UTF-8" method="POST" action="http://www.koozi.be/PublishedService?" name="addPresent">
							<a class="button present" href="javascript:document.addPresent.submit()"><div><@spring.message "cart.present"/></div></a>
						</form>
					</div>
					<div id="orderButton">
						<form action="${base}/cart/myCart/cartItems/" method="post" name="deleteCartItemForm">
							<input type="hidden" name="_method" value="delete"/>
							<a class="button long" href="javascript:document.deleteCartItemForm.submit()"><@spring.message "cart.clear"/></a>
						</form>
					</div>
					<div id="orderButton">
						<form action="${base}/orders/" method="post" name="orderForm">
							<a class="button normal" href="javascript:document.orderForm.submit()"><@spring.message "cart.order"/></a>
						</form>
					</div>
				</div>
		</div>
		<div class="end"></div>
	</div>
</div>
	<#include "${base}/cart/cart.bottom.ftl"/>
</div>

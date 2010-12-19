<#setting number_format="0">
<#import "/spring.ftl" as spring>
<h1 class="title"><@spring.message "cart.title"/></h1>

<#include "${base}/cart/cart.tip.ftl"/>

<#list cartItemList as cartItem>
	<#include "${base}/cart/cartItem.embedded.ftl"/>
</#list>	
	
<div style="float:right; padding-top:5px; padding-right:10px;">
	<select onchange="javascript: window.location = this.value ; ">
	   <option value="${base}/catalogues/muts"><@spring.message "catalogue.name.muts"/></option>
	   <option value="${base}/catalogues/dekentje"><@spring.message "catalogue.name.dekentje"/></option>
	   <option value="${base}/catalogues/nestje"><@spring.message "catalogue.name.nestje"/></option>	   
	   <option value="${base}/catalogues/knuffel"><@spring.message "catalogue.name.knuffel"/></option>
	   <option value="${base}/catalogues/kleding"><@spring.message "catalogue.name.kleding"/></option>
	   <option value="${base}/catalogues/promoties"><@spring.message "catalogue.name.promoties"/></option>
	</select>
</div>
<div style="float:right; padding-top:10px;padding-right:10px;"><@spring.message "cart.continue.shopping"/></div>
<div style="clear:both"/>

<div id="cartItemContainer">
	<div class="innerPanel"  id="cartItemBorder">
		<div id="cartItem">
				<div id="totaalLabel" class="title"><@spring.message "cart.total"/></div> 
				<div id="totaalValue" class="price" >20,00€</div>
				<div id="cartOrder">
					<div id="addPresentButton">
						<form accept-charset="UTF-8" method="POST" action="http://www.koozi.be/PublishedService?" name="addPresent">
							<a class="button present" href="javascript:document.addPresent.submit()"><div><@spring.message "cart.present"/></div></a>
						</form>
					</div>
					<div id="orderButton">
						<a class="button normal" href="javascript:document.addPresent.submit()"><@spring.message "cart.order"/></a>
					</div>
				</div>
		</div>
		<div class="end"/>
		</div>
	</div>
</div>
	<#include "${base}/cart/cart.bottom.ftl"/>
</div>

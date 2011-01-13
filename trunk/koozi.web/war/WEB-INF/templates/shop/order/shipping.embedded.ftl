<#setting number_format="0">
<#import "/spring.ftl" as spring>
${myContext.fetch(base + "/orders/" + orderId + "/customer")}
		
<#assign customer =  myContext.getModelObject("customer")>
<div class="container">
	<div class="containerBorder">
		<h3 class="title top"><@spring.message "shipping.title"/> 
		<select id="address.countryCode" name="address.countryCode"> 
			<option value="BE" <#if customer.address.countryCode?default("") == "BE">selected="true"</#if>><@spring.message "country.BE"/></option> 
			<option value="NL" <#if customer.address.countryCode?default("") == "NL">selected="true"</#if>><@spring.message "country.NL"/></option> 
			<option value="LB" <#if customer.address.countryCode?default("") == "LB">selected="true"</#if>><@spring.message "country.LB"/></option> 
			<option value="FR" <#if customer.address.countryCode?default("") == "FR">selected="true"</#if>><@spring.message "country.FR"/></option>
			<option value="GB" <#if customer.address.countryCode?default("") == "GB">selected="true"</#if>><@spring.message "country.GB"/></option>
		</select> </h3>
		<div class="end"></div>
		<div id="shipping.BE" class="hidden">
			<#include "${base}/order/shipping/shipping.embedded.BE.ftl"/>
		</div>
		<div id="shipping.LB" class="hidden">
			<#include "${base}/order/shipping/shipping.embedded.LB.ftl"/>
		</div>
		<div id="shipping.NL" class="hidden">
			<#include "${base}/order/shipping/shipping.embedded.NL.ftl"/>
		</div>
		<div id="shipping.FR" class="hidden">
			<#include "${base}/order/shipping/shipping.embedded.FR.ftl"/>
		</div>
		<script type="text/javascript">
			function onSelectShippingCountry(String value)
			{
				docuement.getElementById("shipping." + value);
			}
		</script>

		
		<div id="cartOrder" style="float:right">
			<div id="orderButton">
			
				<a class="button long" href="${base}/orders/${orderId}/shipping?view=select"><@spring.message "shipping.add"/></a>
			</div>
		</div>
		<div class="end"></div>
	</div>
</div>
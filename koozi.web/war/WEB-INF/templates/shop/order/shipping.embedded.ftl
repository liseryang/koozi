<#setting number_format="0">
<#import "/spring.ftl" as spring>
${myContext.fetch(base + "/orders/" + orderId + "/customer")}
<#assign customer =  myContext.getModelObject("customer")>

${myContext.fetch(base + "/orders/" + orderId)}
<#assign order =  myContext.getModelObject("order")>		

<div class="container">
	<div class="containerBorder">
	${order.id}
		<#if !order.shippingMethod??>
			<h3 class="title top"><@spring.message "shipping.title"/> 
			<select id="address.countryCode" name="address.countryCode" onchange="onSelectShippingCountry(this.value)"> 
				<option value="BE" <#if customer.address?? && customer.address.countryCode?default("") == "BE">selected="true"</#if>><@spring.message "country.BE"/></option> 
				<option value="NL" <#if customer.address?? && customer.address.countryCode?default("") == "NL">selected="true"</#if>><@spring.message "country.NL"/></option> 
				<option value="LB" <#if customer.address?? && customer.address.countryCode?default("") == "LB">selected="true"</#if>><@spring.message "country.LB"/></option> 
				<option value="FR" <#if customer.address?? && customer.address.countryCode?default("") == "FR">selected="true"</#if>><@spring.message "country.FR"/></option>
				<option value="GB" <#if customer.address?? && customer.address.countryCode?default("") == "GB">selected="true"</#if>><@spring.message "country.GB"/></option>
			</select> </h3>
			<div class="containerSeperator top"></div>
			<div class="end" style="height:5px;" ></div>
			<div id="shipping.BE" <#if (customer.address?? && customer.address.countryCode?default("") != "BE")>class="hidden"</#if>>
				<#include "${base}/order/shipping/shipping.embedded.BE.ftl"/>
			</div>
			<div id="shipping.LB" <#if !customer.address?? || customer.address.countryCode?default("") != "LB">class="hidden"</#if>>
				<#include "${base}/order/shipping/shipping.embedded.LB.ftl"/>
			</div>
			<div id="shipping.NL" <#if !customer.address?? || customer.address.countryCode?default("") != "NL">class="hidden"</#if>>
				<#include "${base}/order/shipping/shipping.embedded.NL.ftl"/>
			</div>
			<div id="shipping.FR" <#if !customer.address?? || customer.address.countryCode?default("") != "FR">class="hidden"</#if>>
				<#include "${base}/order/shipping/shipping.embedded.FR.ftl"/>
			</div>
			<div id="shipping.GB" <#if !customer.address?? || customer.address.countryCode?default("") != "GB">class="hidden"</#if>>
				<#include "${base}/order/shipping/shipping.embedded.GB.ftl"/>
			</div>
			<script type="text/javascript">
				function onSelectShippingCountry(value)
				{
					document.getElementById("shipping.BE").className = "hidden";
					document.getElementById("shipping.NL").className = "hidden";
					document.getElementById("shipping.FR").className = "hidden";
					document.getElementById("shipping.GB").className = "hidden";
					document.getElementById("shipping.LB").className = "hidden";
					value = document.getElementById("address.countryCode").value;
					document.getElementById("shipping." + value).className = "visible";
				}
			</script>
		<#elseif order.shippingMethod =="kiala">
			<@include_page path="${base}/orders/" + order.id + "/shipping/kiala" inherit_params=true params={"view": "selected"}/>
		</#if>
	</div>
</div>
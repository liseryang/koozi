<#setting number_format="0">
<#import "/spring.ftl" as spring>
<#import "/default/product/currency.ftl" as currency>

<h1 class="title"><@spring.message "order.title"/></h1>
${myContext.fetch(base + "/orders/" + order.id + "/orderItems/")}

<#assign orderItemList =  myContext.getModelObject("orderItemList")>

<div class="containerList">
	<@include_page path="${base}/orders/" + order.id + "/customer" inherit_params=true  params={"view": "embedded"}/>
	<@include_page path="${base}/orders/" + order.id + "/shipping" inherit_params=true params={"view": "embedded"}/>
	
	
	<#if RequestParameters["showItems"]??>
		<#list orderItemList as orderItem>
			<#include "${base}/order/orderItem.embedded.ftl"/>
		</#list>	
	</#if>	
	
	<div class="container">
		<div class="containerBorder">
					<h3 class="title top"><@spring.message "order.pay"/></h3> 
					<div id="totaalValue" class="price" >
					
					<#if order.price??>
						<@currency.print  order.price/>
					<#else>
						<@spring.message "product.noPrice"/>
					</#if>
					</div>
					<div class="end"/>
					<div id="cartOrder" style="float:right">
						<div id="orderButton">
							<a class="button normal" href="?showItems"><@spring.message "order.showDetails"/></a>
						</div>
						<div id="orderButton">
							<xform action="${base}/orders/${order.id}/customer" method="post" name="customerForm">
								<a  class="button disabled" href="javascript:document.orderForm.submit()"><@spring.message "order.pay"/></a>
							</xform>
						</div>
					</div>
			</div>
			<div class="end"/>
		</div>
	</div>
</div>
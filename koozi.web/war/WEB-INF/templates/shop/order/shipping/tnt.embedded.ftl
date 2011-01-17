<#setting number_format="0">
<#import "/spring.ftl" as spring>
<a class="Thumb" href="${base}/orders/${orderId}/shipping/default?view=bpost">
	<img src="/static/shop/images/shipping/TNT_Logo.jpg"></img>
</a>
<div id="cartOrder" style="float:right">
	<div id="orderButton">
		<a class="button long" href="${base}/orders/${orderId}/shipping"><@spring.message "shipping.select"/></a>
	</div>
</div>

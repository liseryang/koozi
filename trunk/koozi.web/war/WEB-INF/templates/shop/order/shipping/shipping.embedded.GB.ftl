<#setting number_format="0">
<#import "/spring.ftl" as spring>
<a class="Thumb" href="${base}/orders/${orderId}/shipping/kiala">
	<img src="/static/shop/images/shipping/TNT_Logo.jpg"></img>
</a>
<div id="cartOrder" style="float:right">
	<div id="containerButton">
		<a class="button long" href="${base}/orders/${orderId}/shipping"><@spring.message "shipping.select"/></a>
	</div>
</div>
<div class="end"></div>

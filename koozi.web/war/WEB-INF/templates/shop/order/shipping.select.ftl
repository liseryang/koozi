<#setting number_format="0">
<#import "/spring.ftl" as spring>
<h1 class="title"><@spring.message "shipping.title"/></h1>
<div class="container">
	<div class="containerBorder">
		
		<h3 class="title top"><@spring.message "shipping.belgium.title"/></h3> 
		<a class="Thumb" href="${base}/orders/${orderId}/shipping/kiala">
			<img src="/static/shop/images/shipping/kiala.logo.gif"></img>
		</a>
		<div id="cartOrder" style="float:right">
			<div id="orderButton">
				<a class="button long" href="${base}/orders/${orderId}/shipping"><@spring.message "shipping.select"/></a>
			</div>
		</div>
		<div class="end"></div>
				<a class="Thumb" href="${base}/orders/${orderId}/shipping/kiala">
			<img src="/static/shop/images/shipping/logo_bpost.jpg"></img>
		</a>
		<div id="cartOrder" style="float:right">
			<div id="orderButton">
				<a class="button long" href="${base}/orders/${orderId}/shipping"><@spring.message "shipping.select"/></a>
			</div>
		</div>
		<div class="end"></div>
		<h3 class="title top"><@spring.message "shipping.luxembourg.title"/></h3> 
		<a class="Thumb" href="${base}/orders/${orderId}/shipping/kiala">
			<img src="/static/shop/images/shipping/kiala.logo.gif"></img>
		</a>
		<div id="cartOrder" style="float:right">
			<div id="orderButton">
				<a class="button long" href="${base}/orders/${orderId}/shipping"><@spring.message "shipping.select"/></a>
			</div>
		</div>
		<div class="end"></div>
		
		<h3 class="title top"><@spring.message "shipping.france.title"/></h3> 
		<a class="Thumb" href="${base}/orders/${orderId}/shipping/kiala">
			<img src="/static/shop/images/shipping/kiala.logo.gif"></img>
		</a>
				<div id="cartOrder" style="float:right">
			<div id="orderButton">
				<a class="button long" href="${base}/orders/${orderId}/shipping"><@spring.message "shipping.select"/></a>
			</div>
		</div>
		<div class="end"></div>

	</div>
</div>
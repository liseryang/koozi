<#setting number_format="0">
<#import "/spring.ftl" as spring>
<h3 class="title"><@spring.message "shipping.kiala.select.title"/></h3>
<form name="shippingForm" id="shippingForm" action="${base}/orders/${orderId}/shipping/kiala" method="post">
	<input type="hidden" name="_method" value="put"/>
	<input type="hidden" name="redirect" value="${base}/orders/${orderId}"/>
	<div class="error ">
		<h3 class="title">Errors</h3>
		<ul/> 
	</div> 
	<@spring.formHiddenInput "kialaShipping.id"/>
	 
	<div class="row">
		<div id="kialaNameField" class="field" >
			<label for=kialaShipping.kialaName><@spring.message "shipping.kiala.kialaName"  /> *</label>
			<@spring.formInput "kialaShipping.name" "size='40'"/>
		</div>
		<div id="kialaPostCodeField" class="field">
			<label for="kialaPostCode"><@spring.message "shipping.kiala.kialaPostCode"/> *</label>
			<@spring.formInput "kialaShipping.postCode" "size='10'"/>
		</div>
	
	</div>
			
	<div class="containerButtons"><div class="containerButton"><a class="normal button" href="${base}/orders/${orderId}"><div><@spring.message "shipping.kiala.cancel"/></div></a></div><div class="containerButton"><a class="normal button" href="javascript:	getKiala()" xhref="javascript:document.shippingForm.submit()"><div><@spring.message "shipping.kiala.save"/></div></a></div></div>
	<div class="end"></div>
</form>
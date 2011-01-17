<#setting number_format="0">
<#import "/spring.ftl" as spring>
<h3  class="title top"><@spring.message "shipping.kiala.selected.title"/></h3>
<div class="row field" style=" float:left; clear:right;" >
	<table class="infoTable">
	 	<tbody>
	 		<tr class="dataRow">
	 			<th class="label"><@spring.message "shipping.kiala.name"/></th>
	 			<td class="data">${kialaShipping.name}</td>
	 		</tr>
	 		<tr class="dataRow">
	 			<th class="label"><@spring.message "shipping.kiala.address"/></th>
	 			<td class="data">${kialaShipping.street?default("")} <br/>
	 							${kialaShipping.postCode?default("")} ${kialaShipping.city?default("")}</td>
	 		</tr>
	 		<tr class="dataRow">
	 			<th class="label">Kostprijs</th>
	 			<td class="data price">0 EURO</td>
	 		</tr>
	 	</tbody>
	 </table>	
</div>
<a   style="float:right; " href="${base}/orders/${orderId}/shipping/kiala?view=picker">
	<img style="width:80px; "  src="/static/shop/images/shipping/kiala.logo.gif"></img>
</a>

<div  class="containerButtons">
	<div id="containerButton">
		<a class="button long" href="${base}/orders/${orderId}/shipping"><@spring.message "shipping.kiala"/></a>
	</div>
</div>
<div class="end"></div>

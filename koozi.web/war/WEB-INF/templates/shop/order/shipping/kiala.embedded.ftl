<#setting number_format="0">
<#import "/spring.ftl" as spring>
<h3  class="title top"><@spring.message "shipping.kiala.title"/></h3>
<a   style="float:right; " href="${base}/orders/${orderId}/shipping/kiala?view=picker">
	<img style="width:80px; "  src="/static/shop/images/shipping/kiala.logo.gif"></img>
</a>
<div class="field" style="float:left; width:570px">
	<p class="top">
		Je kan je pakket laten leveren door Koozi in een Kialapunt.
	</p><p>
		Kiala is een netwerk van afhaalpunten (kruidenierszaken, superettes, krantenwinkels, tankstations,?) die lang open zijn, ook tijdens het weekend, zonder lange wachtrijen. Heel gemakkelijk op momenten dat je weinig of onregelmatig thuis bent.
	</p>
</div>
<div class="row field" style="clear:both; float:left;" >
	<table class="infoTable">
	 	<tbody>
		 		<tr class="dataRow">
		 			<th class="label">Kostprijs</th>
		 			<td class="data">0 EURO</td>
		 		</tr>
		 </tbody>
	</table>
</div>
<div  class="containerButtons">
	<div id="containerButton">
		<a class="button long" href="${base}/orders/${orderId}/shipping"><@spring.message "shipping.kiala"/></a>
	</div>
</div>
<div class="end"></div>

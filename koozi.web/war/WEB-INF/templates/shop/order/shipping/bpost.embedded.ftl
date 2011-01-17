<#setting number_format="0">
<#import "/spring.ftl" as spring>

<h3 class="title top"><@spring.message "shipping.bpost.title"/></h3>
<a style="float:right; margin-right:-5px; margin-top:5px;" class="" href="${base}/orders/${orderId}/shipping/default?view=bpost">
	<img style="width:125px; " src="/static/shop/images/shipping/logo_bpost.jpg"></img>
</a>
<div class="field"  style="float:left; width:530px">
	<p class="top">
		Taxipost 24h levert uw pakket de volgende werkdag. Snel en efficiënt.
		</p><p>
		Wij bieden u bovendien tal van opties als de afhaling van uw pakket, de garantie op een levering vóór 11 uur, een bericht per sms of e-mail wanneer uw pakket geleverd is bij de geadresseerde, een bewijs van afgifte en nog veel meer.
	</p>
</div>
<div class="field" style="padding-top:0px;" >
	<div  style="clear:both; float:left;" >
		<table class="infoTable">
		 	<tbody>
			 		<tr class="dataRow">
			 			<th class="label">Kostprijs</th>
			 			<td class="data">0 EURO</td>
			 		</tr>
			 </tbody>
		</table>
	</div>
</div>
<div  class="containerButtons">
	<div class="containerButton">
		<a class="button long" href="${base}/orders/${orderId}/shipping"><@spring.message "shipping.bpost"/></a>
	</div>
</div>
<div class="end"></div>

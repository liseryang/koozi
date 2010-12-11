<h3>SMS's</h3>
<#if smsList??>
	<ul>
		<#list smsList as sms>
			<li><a href="${base}/sms/${sms.id}">${sms.smsResult.timestamp?date} ${sms.smsResult.timestamp?time} :  <b>${sms.number.number}</b></a>  ${sms.smsResult.statusMessage}</li>
		</#list>
	</ul>
</#if>

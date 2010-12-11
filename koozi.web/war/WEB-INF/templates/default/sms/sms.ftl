<h3>SMS status</h3>
<ul>
	<li><span class="key">To:  </span><span class="value">${sms.number.number} </span></li>
	<li>Time:  ${sms.smsResult.timestamp?date} ${sms.smsResult.timestamp?time}</li>
	<li>Description: ${smsResult.statusMessage}</li>
	<li>Content:  ${sms.content}</li>
</ul>

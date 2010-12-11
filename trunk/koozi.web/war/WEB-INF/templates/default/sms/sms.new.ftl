<h3>Send a test SMS</h3>
<form action="${base}/sms" method="post">

	<ul>
		<li>to: <input name="to" /></li>
		<li>text: <TEXTAREA rows = "3" cols="50" name="content" /></TEXTAREA></li>
	</ul>
	<div>
		<input type="submit"  value="Send SMS">
	</div>
	<p>If you want to send to multi-clients use ';' to separate your destinations.</p>
</form>



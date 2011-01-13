<#setting number_format="0">
<html>
	<head>
			<script type="text/javascript" src="/static/shop/scripts/magicthumb-packed.js"></script>
			<link type="text/css" rel="stylesheet" href="/static/shop/css/magicthumb.css"  media="screen, projection">
	</head>
	<body>
		<#if value??>
			<h1 class="title">${springMacroRequestContext.getMessage("catalogue.name." + value)}</h1>
		</#if>	
		<#include "${base}/catalogue/catalogue.embedded.ftl"/>
	</body>
</html>

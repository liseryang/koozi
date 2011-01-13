<#setting number_format="0">
<#import "/spring.ftl" as spring>
<html>
	<head>
		<link type="text/css" rel="stylesheet" href="/static/shop/css/KooziBlogger.css"> 
		<link type="text/css" rel="stylesheet" href="/static/shop/css/Google.css"> 
	</head>
	<body>
		<h1 class="title"><@spring.message "home.title"/></h1>
		<#include "${base}/home/banner.ftl"/>
	
		<div id="homePageWelcomeText">
			<#include "${base}/home/welcome.ftl"/>
			<div style="clear:both"></div>
		</div>	
		
		<#include "${base}/site/blog.feed.ftl"/>
		<#include "${base}/home/footer.ftl"/>
		<#include "${base}/site/share.this.ftl"/>
	</body>
</html>


												
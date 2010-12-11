<#import "/spring.ftl" as spring>
<h1 class="title"><@spring.message "home.title"/></h1>
<div id="homePageSlideShowContainer">
	<div id="homePageSlideShow">
		<@include_page path="${base}/catalogues/nieuw"  inherit_params=true params={"view": "slides"}/>
	</div>
</div>
<div id="homePageWelcomeText">
	<#include "${base}/home/welcome.ftl"/>
	<div style="clear:both"></div>
</div>	
<div id="welcomeProducten">
	<@include_page path="${base}/catalogues/nieuw" inherit_params=true params={"view": "embedded"}/>
</div>

<#include "${base}/site/blog.feed.ftl"/>
<#include "${base}/home/footer.ftl"/>
<#include "${base}/site/share.this.ftl"/>

												
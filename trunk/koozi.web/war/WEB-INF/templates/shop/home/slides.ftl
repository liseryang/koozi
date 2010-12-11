<div id="homePageSlideShowContainer">
	
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
<#setting number_format="0">
<#import "/spring.ftl" as spring>

<html>
	<head>
		<link type="text/css" rel="stylesheet" href="/static/shop/css/magicthumb.css" media="screen">
		<script type="text/javascript" src="/static/shop/scripts/magicthumb-packed.js"></script>
		<script type="text/javascript"> 
			MagicThumb.options = { keepThumbnail: true, allowMultipleImages: false, backgroundFadingColor: '#000', backgroundFadingDuration: 0.4, backgroundFadingOpacity : 0.6  }
		</script>
	</head>
	<body>
		${myContext.fetch(base + "/products/" +  product.id + "/metadata/locale")}
		${myContext.fetch(base + "/products/" +  product.id + "/pictures/")}
		
		
		<#assign metadata =  myContext.getModelObject("metadata")>
		<#assign pictureList =  myContext.getModelObject("pictureList")>
		
		<#if metadata != "null">
			<#assign metadataName = metadata.name>
		<#else>	
			<#assign metadataName = product.code>
		</#if>	
		
		
		<h1 class="title">${metadataName}	</h1>
		<form method="post" action="${base}/cart/myCart/cartItems" name="addToCart" >
			<input  name="productId" value="${product.id}" type="hidden">
			<div class="container">
				<div class="containerBorder">
						<@include_page path="${base}/products/${product.id}/pictures" inherit_params=false/>
						
						<@include_page path="${base}/products/${product.id}/tags" inherit_params=false params={"view": "label"}/>
						
						<@include_page path="${base}/products/${product.id}/pictures" inherit_params=false params={"view": "thumbnail"}/>
						
						<div id="productItemProperties">
						
						<table class="productItem">
						
						<!-- 	<tr class="productItem">
						
								<td class="key productItem">Collectie:</td>
						
								<td class="value productItem" id="productItemCollectie">${product.collection}</td> 
						
							</tr>-->
						
							<tr class="productItem">
						
								<td class="key productItem"><@spring.message "product.amount"/>:</td>
						
								<td class="value productItem" id="xx">
								<input class="value productItem" name="amount" value="1" type="text"></td>
						
							</tr>
							<@include_page path="${base}/products/${product.id}/options" inherit_params=false/>
						
							<tr class="productItem">
						
								<td class="key productItem"><@spring.message "product.price"/>:</td>
						
								<td class="value productItem price" id="productItemPrijs">
									<@include_page path="${base}/products/${product.id}/prices/locale" inherit_params=false/>
								</td>
						
							</tr>
					
						</table>
							</div>
		
						
						<div id="productItemAdd" class="addToCart"><a class="addToCart button" href="javascript:document.addToCart.submit()"><div><@spring.message "cart.add"/></div></a></div>
						
						
						
						<!--<input onClick="return checkform ( form )" type="button"/>-->
						
						<div id="end"></div>
						
						</div>
						
						</div>
		
				<#include "${base}/product/dekentje/info.ftl"/>
			</div>
		</form>
		<@include_page path="${base}/catalogues/${product.collection}" inherit_params=true params={"view": "embedded"}/>
			
	</body>
</html>

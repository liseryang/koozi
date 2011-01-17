<#setting number_format="0">
<#import "/spring.ftl" as spring>

${myContext.fetch(base + "/orders/" + orderId + "/customer")}
		
<#assign customer =  myContext.getModelObject("customer")>

<html>
	<head>
		<script type="text/javascript" src="/static/shop/scripts/jquery.js"></script>
		<script type="text/javascript" src="/static/shop/scripts/jquery.validation.js"></script>
	</head>
	<body>
		<h1 class="title"><@spring.message "shipping.kiala.title"/></h1>
		
		<#assign backUrl = "http://localhost:8888${base}/orders/${orderId}/shipping/kiala/postBack%3Fredirect%3D${base}/orders/${orderId}%26">
		<#assign language = springMacroRequestContext.locale>
		<#assign country = "BE">
		<#if customer?? && customer.address??>
			<#assign postCode = customer.address.postCode>
			<#assign street = customer.address.streetName + " " + customer.address.streetNumber>
			<#assign city = customer.address.city>
		<#else>
			<#assign postCode = "">
			<#assign street = "">
			<#assign city = "">
		</#if>	
		
		
		<div class="container">
			<div class="containerBorder">
			
				<div style="float:right; margin-right:114px;"><div style="position: absolute; z-index: 5;  margin-top:5px; margin-left:0px;"  ><img src="/static/shop/images/shipping/kiala.logo.gif"></img></div></div>
				<h3 style="clear:both;" class="title"><@spring.message "shipping.kiala.zoek.title"/></h3>
				<p>Zoek op postcode voor je dichstbijzijnde Kiala punt.</p>
				<div style="z-index: 9;  clear:both;"><iframe  id="kialaSearch" name="kialaSearch" frameborder="0"  style="margin-top:20px; border:none; width:690px; height:500px; margin-left:-5;" src="http://locateandselect.kiala.com/locateandselect/search?dspid=be&preparationdelay=&language=${language}&bckUrl=${backUrl}&target=_top&header=Test&thumbnails=&map=390x400&align=&css=http://localhost:8888/static/shop/css/kiala.css&pl=${language}&key=&countryid=${country}&zipfilter=&street=${street}&zip=${postCode}&city=${city}&search=Zoeken#"></iframe>
				</div>

			</div>
		</div>
		
		<script type="text/javascript"> 

			function getKiala() {
				var test = document.getElementById("kialaSearch").contentDocument;
				  alert(test);
			}
		 
			$.validator.setDefaults({
				submitHandler: function() { alert("submitted!"); }
			});
			
			
			$().ready(function() {
			
				jQuery.validator.addMethod("postcode", function(value, element) {
			
				    var regex =  /^\b[0-9]{4}\b$/;
					if (regex.test(value) || /^\b[0-9]{4}\s?[A-Z]{2}\b$/.test(value)) {
						return true;
					} else {
						return false;
					}
				}, "Amount must be greater than zero.");
			
			
				var container =  $('div.error');
				// validate signup form on keyup and submit
				$("#customerForm").validate({
					rules: {
						"kialaShipping.kialaName": "required",
						"kialaShipping.kialaPostCode": {
							required: true,
							postcode: true
						}
					},
					messages: {
							"kialaShipping.kialaName": "<@spring.message "shipping.kiala.name.required"/>",
							"kialaShipping.kialaPostCode": {
								required: "<@spring.message "shipping.kiala.postCode.required"/>",
								postcode: "<@spring.message "shipping.kiala.postCode.invalid"/>"
							}
					},
					errorContainer: container,
					errorLabelContainer: $("ul", container),
					wrapper: 'li',
					meta: "validate"
				});
			});
		</script> 

	</body>
</html>

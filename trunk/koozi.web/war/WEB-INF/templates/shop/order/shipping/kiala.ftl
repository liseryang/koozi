<#setting number_format="0">
<#import "/spring.ftl" as spring>
<html>
	<head>
		<script type="text/javascript" src="/static/shop/scripts/jquery.js"></script>
		<script type="text/javascript" src="/static/shop/scripts/jquery.validation.js"></script>
	</head>
	<body>
		<h1 class="title"><@spring.message "shipping.kiala.title"/></h1>
		
		<div class="container">
			<div class="containerBorder">
							<h3 class="title"><@spring.message "shipping.kiala.select.title"/></h3>
				<form name="shippingForm" id="shippingForm" action="${base}/orders/${orderId}/shipping/kiala" method="post">
					<input type="hidden" name="_method" value="put"/>
					<input type="hidden" name="redirect" value="${base}/orders/${orderId}"/>
					<div class="error ">
						<h3 class="title">Errors</h3>
						<ul/> 
					</div> 
					<@spring.formHiddenInput "kialaShipping.id"/>
					 
					<div class="row">
						<div id="kialaNameField" class="field" >
							<label for=kialaShipping.kialaName><@spring.message "shipping.kiala.kialaName"  /> *</label>
							<@spring.formInput "kialaShipping.name" "size='40'"/>
						</div>
						<div id="kialaPostCodeField" class="field">
							<label for="kialaPostCode"><@spring.message "shipping.kiala.kialaPostCode"/> *</label>
							<@spring.formInput "kialaShipping.postCode" "size='10'"/>
						</div>
		
					</div>
						
					<div class="containerButtons"><div class="containerButton"><a class="normal button" href="${base}/orders/${orderId}"><div><@spring.message "shipping.kiala.cancel"/></div></a></div><div class="containerButton"><a class="normal button" href="javascript:	getKiala()" xhref="javascript:document.shippingForm.submit()"><div><@spring.message "shipping.kiala.save"/></div></a></div></div>
					<div class="end"></div>
				</form>
				
				<div style="float:right; margin-right:114px;"><div style="position: absolute; z-index: 5;  margin-top:5px; margin-left:0px;"  ><img src="/static/shop/images/shipping/kiala.logo.gif"></img></div></div>
				<h3 style="clear:both;" class="title"><@spring.message "shipping.kiala.zoek.title"/></h3>
				<p>Zoek op postcode voor je dichstbijzijnde Kiala punt.</p>
				<div style="z-index: 9;  clear:both;"><iframe  id="kialaSearch" name="kialaSearch" frameborder="0"  style="margin-top:20px; border:none; width:690px; height:500px; margin-left:-5;" src="http://locateandselect.kiala.com/locateandselect/search?dspid=be&preparationdelay=&language=nl&bckUrl=http://localhost:8888/shop/orders/${orderId}/shipping/kiala/postBack%3F&amp;&target=_top&header=Test&thumbnails=&map=340x400&align=&css=http://localhost:8888/static/shop/css/kiala.css&pl=&key=&countryid=BE&zipfilter=&street=&zip=3000&city=&search=Zoeken#"></iframe>
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

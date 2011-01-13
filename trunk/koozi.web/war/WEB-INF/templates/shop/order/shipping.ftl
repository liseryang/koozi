<#setting number_format="0">
<#import "/spring.ftl" as spring>
<html>
	<head>
		<script type="text/javascript" src="/static/shop/scripts/jquery.js"></script>
		<script type="text/javascript" src="/static/shop/scripts/jquery.validation.js"></script>
	</head>
	<body>
		<h1 class="title"><@spring.message "customer.title"/></h1>
		<div class="container">
			<div class="containerBorder">
				<form name="customerForm" id="customerForm" action="${base}/orders/${orderId}/customer" method="post">
					<input type="hidden" name="_method" value="put"/>
					<input type="hidden" name="redirect" value="${base}/orders/${orderId}"/>
					<div class="error ">
						<h3 class="title">Errors</h3>
						<ul/> 
					</div> 
					
					<@spring.formHiddenInput "customer.id"/>
					 
					<h3 class="title top"><@spring.message "customer.identification.title"/></h3>
					<div class="row">
						<div id="firstnameField" class="field" >
							<label for="firstname"><@spring.message "customer.firstname" /> *</label>
							<@spring.formInput "customer.identity.firstname"/>
						</div>
						<div id="lastnameField" class="field">
							<label for="lastname"><@spring.message "customer.name"/> *</label>
							<@spring.formInput "customer.identity.name" "size='40'"/>
						</div>
		
					</div>
					<div class="row">
						<div id="emailField" class="field" >
							<label for="email"><@spring.message "customer.email"/> *</label>
							<@spring.formInput "customer.email.value" "size='50'"/>
						</div>
					</div>
					<div class="row">
						<div id="phoneField" class="field" >
							<label for="phone"><@spring.message "customer.phone"/></label>
							<@spring.formInput "customer.phoneNumber.value"/>
						</div>
					</div>
					<div class="end"></div>	
					
					<h3 class="title"><@spring.message "customer.address.title"/></h3>
					
					<div class="row">
						<div id="streetNameField" class="field" >
							<label for="streetName"><@spring.message "customer.streetName"/> *</label>
							<@spring.formInput "customer.address.streetName" "size='70'"/>
						</div>
		
						<div id="streetNumberField" class="field" >
							<label for="streetNumber"><@spring.message "customer.streetNumber"/> *</label>
							<@spring.formInput "customer.address.streetNumber" "size='10'"/>
						</div>
					</div>
					<div class="row">
						<div id="postCodeField" class="field" >
							<label for="postCode"><@spring.message "customer.postCode"/> *</label>
							<@spring.formInput "customer.address.postCode" "size='10'"/>
						</div>
						<div id="cityField" class="field" >
							<label for="city"><@spring.message "customer.city"/> *</label>
							<@spring.formInput "customer.address.city" "size='30'"/>
						</div>
						<div id="countryField" class="field" >
							<label for="country"><@spring.message "customer.country"/> *</label>
							<select id="address.countryCode" name="address.countryCode"> 
								<option value="BE" <#if customer.address.countryCode?default("") == "BE">selected="true"</#if>><@spring.message "country.BE"/></option> 
								<option value="NL" <#if customer.address.countryCode?default("") == "NL">selected="true"</#if>><@spring.message "country.NL"/></option> 
								<option value="LB" <#if customer.address.countryCode?default("") == "LB">selected="true"</#if>><@spring.message "country.LB"/></option> 
								<option value="FR" <#if customer.address.countryCode?default("") == "FR">selected="true"</#if>><@spring.message "country.FR"/></option>
								<option value="GB" <#if customer.address.countryCode?default("") == "GB">selected="true"</#if>><@spring.message "country.GB"/></option>
							</select>
						</div>
					</div>
						
					<div class="end"></div>	
					<h3 class="title"><@spring.message "customer.company.title"/></h3>
					<div class="row">
						<div id="companyNameField" class="field" >
							<label for="companyName"><@spring.message "customer.companyName"/></label>
							<@spring.formInput "customer.company.name" "size='40'"/>
						</div>
					</div>
					<div class="row">
						<div id="companyNumberField" class="field" >
							<label for="companyNumber"><@spring.message "customer.companyNumber"/></label>
							<@spring.formInput "customer.company.enterpriseNumber" "size='40'"/>
						</div>
					</div>
					<div class="end"></div>
				
					<div class="containerButtons"><div class="containerButton"><a class="normal button" href="${base}/orders/${orderId}"><div><@spring.message "customer.cancel"/></div></a></div><div class="containerButton"><a class="normal button" href="javascript:document.customerForm.submit()"><div><@spring.message "customer.save"/></div></a></div></div>
					<div class="end"></div>
				</form>
			</div>
		</div>
		
		<script type="text/javascript"> 
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
						"identity.firstname": "required",
						"identity.name": "required",
						"email.value": {
								required: true,
								email: true
							},
						"address.streetName": "required",
						"address.streetNumber": "required",
						"address.postCode": {
							required: true,
							postcode: true
						},
						"address.city": "required"
					},
					messages: {
							"identity.firstname": "<@spring.message "customer.firstName.required"/>",
							"identity.name": "<@spring.message "customer.lastName.required"/>",
							"email.value": {
								required: "<@spring.message "customer.email.required"/>",
								email: "<@spring.message "customer.email.invalid"/>"
							},
							"address.streetName": "<@spring.message "customer.streetName.required"/>",
							"address.streetNumber": "<@spring.message "customer.streetNumber.required"/>",
							"address.postCode": {
								required: "<@spring.message "customer.postCode.required"/>",
								postcode: "<@spring.message "customer.postCode.invalid"/>"
							},
							"address.city": "<@spring.message "customer.city.required"/>"
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

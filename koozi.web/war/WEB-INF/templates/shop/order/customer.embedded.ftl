<#setting number_format="0">
<#import "/spring.ftl" as spring>
<div class="container">
	<div class="containerBorder">
				<h3 class="title top"><@spring.message "customer.title"/></h3> 
				<div class="end"></div>
					<#if !customer?? || !customer.id?? || customer.id==0>
						<div class="containerButtons"><div class="containerButton">
							<a class="button long" href="${base}/orders/${orderId}/customer?view=edit"><@spring.message "customer.add"/></a>
						</div></div>
					<#else>
						<div class="error ">
							<h3 class="title">Errors</h3>
							<ul/> 
						</div> 
						 <div class="row">
						 <div id="streetNameField" class="field" >
						 <table class="infoTable">
						 	<tbody>
						 		<#if customer.company?? && customer.company.name?? && customer.company.name != "">
							 		<tr class="dataRow">
							 			<th class="label"><@spring.message "customer.companyName"/></th>
							 			<td class="data">${customer.company.name}</td>
							 		</tr>
						 		</#if>
						 		<#if customer.company?? && customer.company.enterpriseNumber?? && customer.company.enterpriseNumber != "">
							 		<tr class="dataRow">
							 			<th class="label"><@spring.message "customer.companyNumber"/></th>
							 			<td class="data">${customer.company.enterpriseNumber}</td>
							 		</tr>
						 		</#if>
						 		<tr class="dataRow">
						 			<th class="label"><@spring.message "customer.identification.title"/></th>
						 			<td class="data">${customer.identity.firstname} ${customer.identity.name}</td>
						 		</tr>
						 		<tr class="dataRow">
						 			<th class="label"></th>
						 			<td class="data"><a href="mailto:${customer.email.value}" target="_blank">${customer.email.value}</a></td>
						 		</tr>
						 		<tr class="dataRow">
						 			<th class="label"><@spring.message "customer.address.title"/></th>
						 			<td class="data">
						 				${customer.address.streetName} ${customer.address.streetNumber} <br/>
						 				${customer.address.postCode} ${customer.address.city} <br/> 
						 				<#if customer.address.countryCode?default("") == "BE"><@spring.message "country.BE"/></#if> 
										<#if customer.address.countryCode?default("") == "NL"><@spring.message "country.NL"/></#if> 
										<#if customer.address.countryCode?default("") == "LB"><@spring.message "country.LB"/></#if> 
										<#if customer.address.countryCode?default("") == "FR"><@spring.message "country.FR"/></#if>
										<#if customer.address.countryCode?default("") == "GB"><@spring.message "country.GB"/></#if>
						 			</td>
						 		</tr>
						 		<#if customer.phoneNumber?? && customer.phoneNumber.value?? && customer.phoneNumber.value != "">
							 		<tr class="dataRow">
							 			<th class="label"><@spring.message "customer.phone"/></th>
							 			<td class="data">${customer.phoneNumber.value}</td>
							 		</tr>
						 		</#if>
						 	</tbody>
						 </table>	
						 </div>	
						</div>

						<div class="end"></div>
						<div class="containerButtons"><div class="containerButton">
							<a class="button long" href="${base}/orders/${orderId}/customer?view=edit"><@spring.message "customer.update"/></a>
						</div></div>
					</#if>
		<div class="end"></div>
	</div>
</div>

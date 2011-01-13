<#setting number_format="0">
<#import "/spring.ftl" as spring>
<#list optionMap?keys as optionKey>
	<tr class="productItem">
		<td class="key productItem"><@spring.messageText "product.option.name.${optionKey}", "${optionKey}"/>:</td>	
		<td class="value productItem">
			<#if optionMap[optionKey][0].type == "select">
				<select class="value productItem" name="${optionKey}" >
					<#list optionMap[optionKey] as option>
						<option  value="${option.value}"><@spring.messageText "product.option.value.${optionKey}.${option.value}" "${option.value}"/></option>
					</#list>
				</select>
			<#elseif optionMap[optionKey][0].type == "radio">
				<#list optionMap[optionKey] as option>
					<input class="value productItem" type="radio" name="${optionKey}" value="${option.value}"/><@spring.messageText "product.option.value.${optionKey}.${option.value}" "${option.value}"/><br>
				</#list>
			<#else>
				<input  class="value productItem" name="${optionKey}" type="input" value=""/>
			</#if>
		</td>
	</tr>
</#list>

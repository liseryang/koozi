<#setting number_format="0">
<#import "/spring.ftl" as spring>
<#list optionMap?keys as optionKey>
	<tr class="productItem">
		<td class="key productItem"><@spring.message "product.option.name.${optionKey}"/></td>	
		<td class="value productItem">
			<#if optionMap[optionKey][0].type == "select">
				<select   class="ProductAmountField" name="${optionKey}" class="ProductOptionsField">
					<#list optionMap[optionKey] as option>
						<option value="${option.value}"><@spring.message "product.option.value.${option.value}"/></option>
					</#list>
				</select>
			<#else>
				<input  class="value productItem" name="${optionKey}" type="input" value=""/>
			</#if>
		</td>
	</tr>
</#list>

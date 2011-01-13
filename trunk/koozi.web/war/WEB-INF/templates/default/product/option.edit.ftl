<#setting number_format="0">
<#import "/default/product/currency.ftl" as currency>

<a href="${base}/products">products</a> - 
<a href="${base}/products/${option.productId}">product</a> -
<a href="${base}/products/${option.productId}/options">options	</a> -
<a href="${base}/products/${option.productId}/options/${option.id}">${option.id}</a>

${myContext.fetch(base + "/products/" +  option.productId + "/options/" + option.id + "/prices")}


<#assign priceList =  myContext.getModelObject("priceList")>


<h3>Product</h3>
 <@include_page path="${base}/products/${option.productId}" inherit_params=true  params={"view": "embedded"}/>

<h3>Edit option "<b>${option.key}</b>"</h3>

<form action="${base}/products/${option.productId}/options/${option.id}" method="post">
	<input type="hidden" name="_method" value="put"/>
	<li>Key: <input name="key" type="input" value="${option.key}" /></li>
	<li>Type: 	
		<select name="type">
	        <option value="select" <#if option.type == "select">SELECTED</#if> >select</option>
	        <option value="radio" <#if option.type == "radio">SELECTED</#if> >radio</option>
	        <option value="input" <#if option.type == "input">SELECTED</#if> >input</option>
    	</select>
	</li>
	<li>Value: <input name="value" type="input"  value="${option.value}"/></li>
	<li>Stock: <input name="stock" type="input" value="${option.stock}"/></li>
	</ul>
	<input type="submit"  value="Update option"/>
</form>

<form action="${base}/products/${option.productId}/options/${option.id}" method="post">
	<input type="hidden" name="_method" value="delete"/>
	<input type="submit"  value="Delete option"/>
</form>
<form action="${base}/products/${option.productId}/options" method="get">
	<input type="submit"  value="Cancel edit"/>
	<input type="hidden" name="view" value="edit"/>
</form>


<h3>Edit option price</h3>
<ul>
	<#list priceList as price>
		<li>
		<form action="${base}/products/${option.productId}/options/${option.id}/prices/${price.currency}" method="post">
				<@currency.print  price/>
				<input type="hidden" name="_method" value="delete"/>
				<input type="submit"  value="Delete price"/>
			</form>
		</li>
	</#list>
</ul>

<form action="${base}/products/${option.productId}/options/${option.id}/prices" method="post">
	<input name="amount" type="input" />
	<select name="currency">
		<option value="EUR">&euro;</option>
	    <option value="GBP">£</option>
	 </select>
	<input type="submit"  value="Add price"/>
</form>

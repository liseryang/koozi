<#setting number_format="0">
<#import "/default/product/currency.ftl" as currency>

<a href="${base}/products">products</a> - 
<a href="${base}/products/${productId}">product</a> -
<a href="${base}/products/${productId}/prices">prices</a>

<h3>Product</h3>
<@include_page path="${base}/products/${productId}/metadata/default" inherit_params=true  params={"view": "embedded"}/>
<@include_page path="${base}/products/${productId}" inherit_params=true  params={"view": "embedded"}/>

<h3>Edit product price</h3>
<ul>
	<#list priceList as price>
		<li>
		<form action="${base}/products/${productId}/prices/${price.currency}" method="post">
				<@currency.print  price/>
				<input type="hidden" name="_method" value="delete"/>
				<input type="submit"  value="Delete price"/>
			</form>
		</li>
	</#list>
</ul>

<form action="${base}/products/${productId}/prices" method="post">
	<input name="amount" type="input" />
	<select name="currency">
		<option value="EUR">&euro;</option>
	    <option value="GBP">£</option>
	 </select>
	<input type="submit"  value="Add price"/>
</form>

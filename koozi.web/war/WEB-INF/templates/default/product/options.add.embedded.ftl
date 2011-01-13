<#setting number_format="0">
<#import "/default/product/currency.ftl" as currency>

<h3>Add standard product option</h3>
<form action="${base}/products/${productId}/options" method="post">
	<input name="key" type="hidden" value="size" />
	<input name="type" type="hidden" value="select"/></li>
	<input name="value" type="hidden" value="24x45"/></li>
	 <input name="stock" type="hidden" value="0" /></li>
	<input type="submit" value="Add option 'Grootte' 24x45" />
</form>
<form action="${base}/products/${productId}/options" method="post">
	<input name="key" type="hidden" value="size" />
	<input name="type" type="hidden" value="select"/></li>
	<input name="value" type="hidden" value="50x45"/></li>
	 <input name="stock" type="hidden" value="0" /></li>
	<input type="submit" value="Add option 'Grootte' 50x45" />
</form>
<form action="${base}/products/${productId}/options" method="post">
	<input name="key" type="hidden" value="bedrukkingType" />
	<input name="type" type="hidden" value="select"/></li>
	<input name="value" type="hidden" value="groot"/></li>
	 <input name="stock" type="hidden" value="0" /></li>
	<input type="submit" value="Add option 'Bedrukking' grote letters" />
</form>
<form action="${base}/products/${productId}/options" method="post">
	<input name="key" type="hidden" value="bedrukkingType" />
	<input name="type" type="hidden" value="select"/></li>
	<input name="value" type="hidden" value="klein"/></li>
	 <input name="stock" type="hidden" value="0" /></li>
	<input type="submit" value="Add option 'Bedrukking' kleine letters" />
</form>
<form action="${base}/products/${productId}/options" method="post">
	<input name="key" type="hidden" value="bedrukking" />
	<input name="type" type="hidden" value="input"/></li>
	<input name="value" type="hidden" value="0"/></li>
	 <input name="stock" type="hidden" value="0" /></li>
	<input type="submit" value="Add option 'Bedrukking' opdruk" />
</form>
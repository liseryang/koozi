 <#setting number_format="0">
 <a href="${base}/products">products</a> - 
<a href="${base}/products/${productId}">product</a> -
<a href="${base}/products/${productId}/metadata">metadata	</a>
 
 <h3>Product</h3>
 <@include_page path="${base}/products/${productId}/metadata/default" inherit_params=true  params={"view": "embedded"}/>
 
 <@include_page path="${base}/products/${productId}" inherit_params=true  params={"view": "embedded"}/>

<#list metadataList as metadata>
	<h3>Edit Metadata <b>"${metadata.locale}"</b></h3>
	<ul>
		<li>Name: ${metadata.name}</li>
		<li>Description: ${metadata.description}</li>
	</ul>
	<form action="${base}/products/${metadata.productId}/metadata/${metadata.locale}" method="get">
		<input type="submit"  value="Edit product metadata"/>
		<input type="hidden" name="view" value="edit"/>
	</form>
</#list>

<h3>Add Metadata</h3>
<form action="${base}/products/${productId}/metadata" method="post">
	<ul>
		<select name="locale">
	        <option value="nl">Nederlands</option>
	        <option value="en">Englisch</option>
	        <option value="fr">Français</option>
    	</select>
    	<li>Name: <input type="text" name="name"/></li>
    	<li>Description: <input type="textarea	"  	name="description"/></li>
	</ul>
	<input type="submit"  value="Add metadata"/>
</form>


<h1 class="title">${product.code}	</h1>

	<div id="productItemContainer">
		<div class="innerPanel" id="productItemBorder" name="productItemBorder">
			<div id="productItem">
				<@include_page path="${base}/products/${product.id}/pictures" inherit_params=false/>
				
				<@include_page path="${base}/products/${product.id}/tags" inherit_params=false params={"view": "label"}/>
				
				<@include_page path="${base}/products/${product.id}/pictures" inherit_params=false params={"view": "thumbnail"}/>
				
				<div id="productItemProperties">
				
				<table class="productItem">
				
				<!-- 	<tr class="productItem">
				
						<td class="key productItem">Collectie:</td>
				
						<td class="value productItem" id="productItemCollectie">${product.collection}</td> 
				
					</tr>-->
				
					<tr class="productItem">
				
						<td class="key productItem">Aantal:</td>
				
						<td class="value productItem" id="productItemAantal"><input
							size="2" value="1" class="ProductAmountField" name="amount490"
							type="TEXT"></td>
				
					</tr>
				
					<tr class="productItemOptions">
				
						<td colspan="2" class="productItemOptions">
				
						<table>
							<tr>
								<td>Maat (LxB):</td>
								<td><select name="490option0" class="ProductOptionsField">
									<option value="56 x 48">56 x 48
								</select></td>
							</tr>
							<tr>
								<td>Bedrukking</td>
								<td><select name="490option1" class="ProductOptionsField">
									<option value="Geen opdruk">Geen opdruk
									<option value="Naam in kleine letters">Naam in kleine
									letters ( + 2,50€ )
									<option value="Naam in grote letters">Naam in grote
									letters ( + 3,50€ )
								</select></td>
							</tr>
						</table>
				
						</td>
				
					</tr>
				
					<tr class="productItem">
				
						<td class="key productItem">Prijs per stuk:</td>
				
						<td class="value productItem price" id="productItemPrijs">13,00€
				
						</td>
				
					</tr>
				
					<tr class="productItem">
				
						<td colspan="2" class="key">
				
						<div class="alert"></div>
				
						</td>
				
					</tr>
				
				</table>
				
				
				
				</div>
				
				<input name="pageID" type="hidden" value="4" />
				
				<div id="productItemSpacer"></div>
				
				<div id="productItemAdd"><input
					SRC="pictures/bt_toevoegen_aan_winkelwagen.gif" value=""
					class="ProductAddButton" name="add490" type="IMAGE"> <a
					name="DEKENTJE-AIKO"></a></div>
				
				<!--<input onClick="return checkform ( form )" type="button"/>-->
				
				<div id="end"></div>
				
				</div>
				
				</div>
				</div>

	<#include "${base}/product/dekentje/info.ftl"/>
</div>

<@include_page path="${base}/catalogues/${product.collection}" inherit_params=true params={"view": "embedded"}/>

package be.koozi.product;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import be.koozi.tools.CurrencyResolver;

import com.google.appengine.api.users.UserService;

@Controller
public class ProductController {

	private ProductDao productDao;
	private TagDao tagDao;
	private MetadataDao metadataDao;
	private PictureDao pictureDao;
	private PriceDao priceDao;
	private OptionDao optionDao;

	private CurrencyResolver currencyResolver;

	private UserService userService;

	public ProductController() {
	}

	@Autowired
	public ProductController(ProductDao productDao, TagDao productTagDao, PictureDao pictureDao, MetadataDao productMetadataDao, PriceDao priceDao,OptionDao optionDao, UserService userService, CurrencyResolver currencyResolver) {
		this.productDao = productDao;
		this.tagDao = productTagDao;
		this.pictureDao = pictureDao;
		this.optionDao = optionDao;
		this.metadataDao = productMetadataDao;
		this.priceDao = priceDao;
		this.userService = userService;
		this.currencyResolver = currencyResolver;

	}

	@RequestMapping(value = "/products", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findProducts(HttpServletRequest request, ModelMap model) {
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		Locale locale = localeResolver.resolveLocale(request);
		Collection<Product> productList = null;
		productList = productDao.findAll();
		model.addAttribute("productList", productList);
		model.addAttribute("user", userService.getCurrentUser());
		return "product/products";
	}

	@RequestMapping(value = "/products/{id}", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findProduct(@PathVariable("id") Long id, ModelMap model) {
		Product product = productDao.find(id);
		model.addAttribute("product", product);
		return "product/product";
	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products/new", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String createProduct(ModelMap model) {
		return "product/product.edit";
	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products/{productId}", method = org.springframework.web.bind.annotation.RequestMethod.PUT)
	public String updateProduct(@PathVariable("productId") Long id, @RequestParam("code") String code, @RequestParam("collection") String collection, @RequestParam("thumbnail") String thumbnail, ModelMap model) {
		Product product = productDao.find(id);
		product.setThumbnail(thumbnail);
		product.setCollection(collection);
		productDao.update(product);
		return "redirect:/products/" + id;
	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products", method = org.springframework.web.bind.annotation.RequestMethod.POST)
	public String createProduct(@RequestParam("code") String code, @RequestParam("collection") String collection, @RequestParam("thumbnail") String thumbnail, ModelMap model) {
		Product product = new Product(code);
		product.setThumbnail(thumbnail);
		product.setCollection(collection);
		productDao.create(product);
		return "redirect:/products/" + product.getId();
	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products/{id}", method = org.springframework.web.bind.annotation.RequestMethod.DELETE)
	public String deleteProduct(@PathVariable("id") Long id, ModelMap model) {
		productDao.delete(id);
		return "redirect:/products";
	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products/{productId}/tags", method = org.springframework.web.bind.annotation.RequestMethod.POST)
	public String createTag(@PathVariable("productId") Long productId, @RequestParam("value") String tag, ModelMap model) {
		Tag productTag = new Tag(tag, productId);
		tagDao.create(productTag);
		return "redirect:/products/" + productId + "/tags/" + productTag.getId();
	}

	@RequestMapping(value = "/products/{productId}/tags/{id}", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findProductTag(@PathVariable("productId") Long productId, @PathVariable("id") long productTagId, ModelMap model) {
		Tag productTag = tagDao.find(productTagId);
		model.addAttribute("tag", productTag);
		return "product/tag";
	}

	@RequestMapping(value = "/products/{productId}/tags", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findProductTags(@PathVariable("productId") Long productId, ModelMap model) {
		Collection<Tag> productTagList = tagDao.findByProduct(productId);
		model.addAttribute("tagList", productTagList);
		model.addAttribute("productId", productId);
		return "product/tags";
	}

	@RequestMapping(value = "/catalogues/{value}", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findProductTags(@PathVariable("value") String value, ModelMap model) {
		Collection<Tag> productTagList = tagDao.findByTagValue(value);
		model.addAttribute("tagList", productTagList);
		model.addAttribute("value", value);
		return "catalogue/catalogue";
	}

	@RequestMapping(value = "/catalogues", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findProductTags(ModelMap model) {
		Collection<String> productTagList = tagDao.findAllValues();
		model.addAttribute("tagValues", productTagList);
		return "catalogue/catalogues";
	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products/{productId}/tags/{productTagId}", method = org.springframework.web.bind.annotation.RequestMethod.DELETE)
	public String deleteProductTag(@PathVariable("productId") Long productId, @PathVariable("productTagId") long productTagId, ModelMap model) {
		tagDao.delete(productTagId);
		return "redirect:/products/" + productId + "/tags";
	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products/{productId}/pictures", method = org.springframework.web.bind.annotation.RequestMethod.POST)
	public String createPicture(@PathVariable("productId") Long productId, @RequestParam("href") String href, ModelMap model) {
		Picture picture = new Picture(href, productId);
		pictureDao.create(picture);
		return "redirect:/products/" + productId + "/pictures/" + picture.getId();
	}

	@RequestMapping(value = "/products/{productId}/pictures", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findPictures(@PathVariable("productId") Long productId, ModelMap model) {
		Collection<Picture> pictureList = pictureDao.findByProduct(productId);
		model.addAttribute("pictureList", pictureList);
		model.addAttribute("productId", productId);
		return "product/pictures";
	}

	@RequestMapping(value = "/products/{productId}/pictures/{id}", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findPicture(@PathVariable("id") Long id, ModelMap model) {
		Picture picture = pictureDao.find(id);
		model.addAttribute("picture", picture);
		return "product/picture";
	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products/{productId}/pictures/{pictureId}", method = org.springframework.web.bind.annotation.RequestMethod.PUT)
	public String updatePicture(@PathVariable("productId") Long productId, @PathVariable("pictureId") long pictureId, @RequestParam("href") String href, ModelMap model) {
		Picture picture = pictureDao.find(pictureId);
		picture.setHref(href);
		model.addAttribute("picture", picture);
		return "product/picture";
	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products/{productId}/pictures/{pictureId}", method = org.springframework.web.bind.annotation.RequestMethod.DELETE)
	public String deletePicture(@PathVariable("productId") Long productId, @PathVariable("pictureId") long pictureId, ModelMap model) {
		pictureDao.delete(pictureId);
		return "redirect:/products/" + productId + "/pictures";
	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products/{productId}/metadata", method = org.springframework.web.bind.annotation.RequestMethod.POST)
	public String createProductMetadata(@PathVariable("productId") Long productId, @RequestParam("locale") String localeId, @RequestParam("name") String name, @RequestParam("description") String description, ModelMap model) {
		Metadata productMetadata = new Metadata(localeId, name, description, productId);
		metadataDao.create(productMetadata);
		return "redirect:/products/" + productId + "/metadata/" + productMetadata.getLocale();
	}

	@RequestMapping(value = "/products/{productId}/metadata", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findProductMetadatas(@PathVariable("productId") Long productId, ModelMap model) {
		Collection<Metadata> productMetadataList = metadataDao.findByProduct(productId);
		model.addAttribute("metadataList", productMetadataList);
		model.addAttribute("productId", productId);
		return "product/metadatas";
	}

	@RequestMapping(value = "/products/{productId}/metadata/locale", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findProductMetadata(HttpServletRequest request, @PathVariable("productId") Long productId, ModelMap model) {
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		Locale locale = localeResolver.resolveLocale(request);

		Metadata productMetadata = metadataDao.findByProduct(locale.getLanguage(), productId);

		if (productMetadata == null) {
			Collection<Metadata> all = metadataDao.findByProduct(productId);
			if (all.size() > 0)
				productMetadata = all.iterator().next();
		}
		model.addAttribute("metadata", productMetadata);
		return "product/metadata";
	}

	@RequestMapping(value = "/products/{productId}/metadata/{locale}", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findProductMetadata(@PathVariable("locale") String locale, @PathVariable("productId") Long productId, ModelMap model) {
		Metadata productMetadata = metadataDao.findByProduct(locale, productId);

		if (productMetadata == null) {
			Collection<Metadata> all = metadataDao.findByProduct(productId);
			if (all.size() > 0)
				productMetadata = all.iterator().next();
		}
		model.addAttribute("metadata", productMetadata);
		return "product/metadata";
	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products/{productId}/metadata/{locale}", method = org.springframework.web.bind.annotation.RequestMethod.PUT)
	public String updateProductMetadata(@PathVariable("locale") String locale, @PathVariable("productId") Long productId, @RequestParam("name") String name, @RequestParam("description") String description, ModelMap model) {
		Metadata productMetadata = metadataDao.findByProduct(locale, productId);
		productMetadata.setDescription(description);
		productMetadata.setName(name);
		metadataDao.update(productMetadata);
		model.addAttribute("metadata", productMetadata);
		return "redirect:/products/" + productId + "/metadata/" + productMetadata.getLocale();

	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products/{productId}/metadata/{locale}", method = org.springframework.web.bind.annotation.RequestMethod.DELETE)
	public String deleteProductMetadata(@PathVariable("productId") Long productId, @PathVariable("locale") String localeId, ModelMap model) {
		Metadata productMetadata = metadataDao.findByProduct(localeId, productId);
		metadataDao.delete(productMetadata.getId());
		return "redirect:/products/" + productId + "/metadata";
	}

	//Product prices
	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products/{productId}/prices", method = org.springframework.web.bind.annotation.RequestMethod.POST)
	public String createPrice(@PathVariable("productId") Long productId, @RequestParam("amount") BigDecimal amount, @RequestParam("currency") String currency, ModelMap model) {
		Price price = new Price(amount, Currency.getInstance(currency), productId);
		priceDao.create(price);
		return "redirect:/products/" + productId + "/prices/" + price.getCurrency();
	}

	@RequestMapping(value = "/products/{productId}/prices", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findPrices(@PathVariable("productId") Long productId, ModelMap model) {
		Collection<Price> priceList = priceDao.findByProduct(productId);
		model.addAttribute("priceList", priceList);
		model.addAttribute("productId", productId);
		return "product/prices";
	}

	@RequestMapping(value = "/products/{productId}/prices/{currency}", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findPrice(@PathVariable("currency") String currency, @PathVariable("productId") Long productId, ModelMap model) {
		Price price = priceDao.findByProduct(Currency.getInstance(currency), productId);

		if (price == null) {
			Collection<Price> all = priceDao.findByProduct(productId);
			if (all.size() > 0)
				price = all.iterator().next();
		}
		model.addAttribute("price", price);
		return "product/price";
	}

	@RequestMapping(value = "/products/{productId}/prices/locale", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findPrice(HttpServletRequest request, @PathVariable("productId") Long productId, ModelMap model) {
		Currency currency = currencyResolver.resolveCurrency(request);
		Price price = priceDao.findByProduct(currency, productId);

		if (price == null) {
			Collection<Price> all = priceDao.findByProduct(productId);
			if (all.size() > 0)
				price = all.iterator().next();
		}
		model.addAttribute("price", price);
		return "product/price";
	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products/{productId}/price/{currency}", method = org.springframework.web.bind.annotation.RequestMethod.PUT)
	public String updatePrice(@PathVariable("currency") String currency, @PathVariable("productId") Long productId, @RequestParam("amount") BigDecimal amount, ModelMap model) {
		Price productPrice = priceDao.findByProduct(Currency.getInstance(currency), productId);
		productPrice.setAmount(amount);
		priceDao.update(productPrice);
		model.addAttribute("price", productPrice);
		return "redirect:/products/" + productId + "/prices/" + productPrice.getCurrency();

	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products/{productId}/prices/{currency}", method = org.springframework.web.bind.annotation.RequestMethod.DELETE)
	public String deletePrice(@PathVariable("productId") Long productId, @PathVariable("currency") String currency, ModelMap model) {
		Price productPrice = priceDao.findByProduct(Currency.getInstance(currency), productId);
		priceDao.delete(productPrice.getId());
		return "redirect:/products/" + productId + "/prices";
	}
	
	//Options

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products/{productId}/options", method = org.springframework.web.bind.annotation.RequestMethod.POST)
	public String createOption(@PathVariable("productId") Long productId,@RequestParam("stock") int stock, @RequestParam("key") String key, @RequestParam("type") String type, @RequestParam("value") String value, ModelMap model) {
		Option option = new Option(stock, key, type, value,  productId);
		optionDao.create(option);
		return "redirect:/products/" + productId + "/options/" + option.getId();
	}

	@RequestMapping(value = "/products/{productId}/options", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findOptions(@PathVariable("productId") Long productId, ModelMap model) {
		List<Option> optionList = optionDao.findByProduct(productId);
		model.addAttribute("optionList", optionList);
		model.addAttribute("optionMap", OptionBean.getOptionMap(optionList));
		model.addAttribute("productId", productId);
		return "product/options";
	}

	@RequestMapping(value = "/products/{productId}/options/{id}", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findOption(@PathVariable("id") Long productId, ModelMap model) {
		Option option = optionDao.find(productId);
		model.addAttribute("option", option);
		return "product/option";
	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products/{productId}/options/{id}", method = org.springframework.web.bind.annotation.RequestMethod.PUT)
	public String option(@PathVariable("id") long id, @PathVariable("productId") Long productId, @RequestParam("stock") int stock, @RequestParam("key") String key,@RequestParam("value") String value, @RequestParam("type") String type, ModelMap model) {
		Option option = optionDao.find(id);
		option.setProductId(productId);
		option.setKey(key);
		option.setStock(stock);		
		option.setType(type);
		option.setValue(value);
		optionDao.update(option);
		model.addAttribute("option", option);
		return "redirect:/products/" + productId + "/options/" + option.getId();

	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products/{productId}/options/{id}", method = org.springframework.web.bind.annotation.RequestMethod.DELETE)
	public String deleteOption(@PathVariable("productId") Long productId, @PathVariable("id") long id, ModelMap model) {
		optionDao.delete(id);
		return "redirect:/products/" + productId + "/options";
	}
}

package be.koozi.product;

import java.util.Collection;
import java.util.Locale;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.google.appengine.api.users.UserService;

@Controller
public class ProductController {

	private ProductDao productDao;
	private ProductTagDao productTagDao;
	private ProductMetadataDao productMetadataDao;
	private PictureDao pictureDao;

	private UserService userService;

	public ProductController() {
	}

	@Autowired
	public ProductController(ProductDao productDao, ProductTagDao productTagDao, PictureDao pictureDao, ProductMetadataDao productMetadataDao, UserService userService) {
		this.productDao = productDao;
		this.productTagDao = productTagDao;
		this.pictureDao = pictureDao;
		this.productMetadataDao = productMetadataDao;
		this.userService = userService;
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
	public String createTag(@PathVariable("productId") Long productId, @RequestParam("tag") String tag, ModelMap model) {
		ProductTag productTag = new ProductTag(tag, productId);
		productTagDao.create(productTag);
		return "redirect:/products/" + productId + "/tags/" + productTag.getId();
	}

	@RequestMapping(value = "/products/{productId}/tags/{id}", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findProductTag(@PathVariable("productId") Long productId, @PathVariable("id") long productTagId,  ModelMap model) {
		ProductTag productTag = productTagDao.find(productTagId);
		model.addAttribute("productTag", productTag);
		return "product/productTag";
	}
	
	@RequestMapping(value = "/products/{productId}/tags", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findProductTags(@PathVariable("productId") Long productId, ModelMap model) {
		Collection<ProductTag> productTagList = productTagDao.findByProduct(productId);
		model.addAttribute("productTagList", productTagList);
		model.addAttribute("productId", productId);
		return "product/productTags";
	}

	@RequestMapping(value = "/catalogues/{tag}", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findProductTags(@PathVariable("tag") String tag, ModelMap model) {
		Collection<ProductTag> productTagList = productTagDao.findByTagValue(tag);
		model.addAttribute("productTagList", productTagList);
		model.addAttribute("tag", tag);
		return "catalogue/catalogue";
	}

	@RequestMapping(value = "/catalogues", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findProductTags(ModelMap model) {
		Collection<String> productTagList = productTagDao.findAllValues();
		model.addAttribute("productTagValues", productTagList);
		return "catalogue/catalogues";
	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products/{productId}/tags/{productTagId}", method = org.springframework.web.bind.annotation.RequestMethod.DELETE)
	public String deleteProductTag(@PathVariable("productId") Long productId, @PathVariable("productTagId") long productTagId, ModelMap model) {
		productTagDao.delete(productTagId);
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
	public String updatePicture(@PathVariable("productId") Long productId, @PathVariable("pictureId") long pictureId, @RequestParam("href") String href,ModelMap model) {
		Picture picture = pictureDao.find(pictureId);
		picture.setHref(href);
		model.addAttribute("picture", picture);
		return "product/picture";
	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products/{productId}/pictures/{pictureId}", method = org.springframework.web.bind.annotation.RequestMethod.DELETE)
	public String deletePicture(@PathVariable("productId") Long productId, @PathVariable("pictureId") long pictureId, ModelMap model) {
		pictureDao.delete(pictureId);
		return "redirect:/products/" + productId;
	}
	
	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products/{productId}/metadata", method = org.springframework.web.bind.annotation.RequestMethod.POST)
	public String createProductMetadata(@PathVariable("productId") Long productId, @RequestParam("locale") String localeId, @RequestParam("name") String name, @RequestParam("description") String description, ModelMap model) {
		ProductMetadata productMetadata = new ProductMetadata(localeId, name, description, productId);
		productMetadataDao.create(productMetadata);
		return "redirect:/products/" + productId + "/metadata/" + productMetadata.getLocale();
	}

	@RequestMapping(value = "/products/{productId}/metadata", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findProductMetadatas(@PathVariable("productId") Long productId, ModelMap model) {
		Collection<ProductMetadata> productMetadataList = productMetadataDao.findByProduct(productId);
		model.addAttribute("productMetadataList", productMetadataList);
		model.addAttribute("productId", productId);
		return "product/productMetadatas";
	}

	@RequestMapping(value = "/products/{productId}/metadata/{locale}", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findProductMetadata(@PathVariable("locale") String locale, @PathVariable("productId") Long productId, ModelMap model) {
		ProductMetadata productMetadata = productMetadataDao.findByProduct(locale, productId);
		
		if(productMetadata == null)
		{
			Collection<ProductMetadata> all = productMetadataDao.findByProduct(productId);
			if(all.size() > 0)
				productMetadata = all.iterator().next();	
		}
		model.addAttribute("productMetadata", productMetadata);
		return "product/productMetadata";
	}
	
	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products/{productId}/metadata/{locale}", method = org.springframework.web.bind.annotation.RequestMethod.PUT)
	public String updateProductMetadata(@PathVariable("locale") String locale, @PathVariable("productId") Long productId, @RequestParam("name") String name,  @RequestParam("description") String description, ModelMap model) {
		ProductMetadata productMetadata = productMetadataDao.findByProduct(locale, productId);
		productMetadata.setDescription(description);
		productMetadata.setName(name);
		productMetadataDao.update(productMetadata);
		model.addAttribute("productMetadata", productMetadata);
		return "redirect:/products/" + productId + "/metadata/" + productMetadata.getLocale();

	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products/{productId}/metadata/{locale}", method = org.springframework.web.bind.annotation.RequestMethod.DELETE)
	public String deleteProductMetadata(@PathVariable("productId") Long productId, @PathVariable("locale") String localeId, ModelMap model) {
		ProductMetadata productMetadata = productMetadataDao.findByProduct(localeId, productId);
		pictureDao.delete(productMetadata.getId());
		return "redirect:/products/" + productId;
	}
}

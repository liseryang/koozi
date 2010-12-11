package be.koozi.product;

import java.util.Collection;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.appengine.api.users.UserService;

@Controller
public class ProductController {

	private ProductDao productDao;
	private ProductTagDao productTagDao;
	private UserService userService;

	public ProductController() {
	}

	@Autowired
	public ProductController(ProductDao productDao, ProductTagDao productTagDao, UserService userService) {
		this.productDao = productDao;
		this.productTagDao = productTagDao;
		this.userService = userService;
	}

	@RequestMapping(value = "/products", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findProducts(ModelMap model) {
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
		Product product = new Product("code", "name", "description");
		model.addAttribute("product", product);

		return "product/product.edit";
	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products/{productId}", method = org.springframework.web.bind.annotation.RequestMethod.PUT)
	public String updateProduct(@PathVariable("productId") Long id, @RequestParam("code") String code, @RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("thumbnail") String thumbnail, ModelMap model) {
		Product product = new Product(id, code, name, description);
		product.setThumbnail(thumbnail);
		productDao.update(product);
		return "redirect:/products/" + id;
	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products", method = org.springframework.web.bind.annotation.RequestMethod.POST)
	public String createProduct(@RequestParam("code") String code, @RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("thumbnail") String thumbnail, ModelMap model) {
		Product product = new Product(code, name, description);
		product.setThumbnail(thumbnail);
		productDao.create(product);
		return "redirect:/products/" + product.getId();
	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products/{id}", method = org.springframework.web.bind.annotation.RequestMethod.DELETE)
	public String deleteProduct(@PathVariable("id") Long id, ModelMap model) {
		productDao.delete(id);
		return "redirect:#";
	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/products/{productId}/tags", method = org.springframework.web.bind.annotation.RequestMethod.POST)
	public String createTag(@PathVariable("productId") Long productId, @RequestParam("tag") String tag, ModelMap model) {
		ProductTag productTag = new ProductTag(tag, productId);
		productTagDao.create(productTag);
		return "redirect:/products/" + productId;
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
		return "redirect:/products/" + productId;
	}
	

	

}

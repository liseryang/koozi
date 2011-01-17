package be.koozi.cart;

import java.util.Currency;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import be.koozi.product.Option;
import be.koozi.product.OptionDao;
import be.koozi.product.Price;
import be.koozi.tools.CookieCurrencyResolver;
import be.koozi.tools.CurrencyResolver;

@Controller
public class CartController {
	public static final String CART_REQUEST_ATTRIBUTE_NAME = CookieCurrencyResolver.class.getName() + ".CURRENCY";

	public static final String DEFAULT_COOKIE_NAME = CookieCurrencyResolver.class.getName() + ".CURRENCY";

	private CartItemDao cartItemDao;
	private CartResolver cartResolver;
	private OptionDao optionDao;
	private OptionValueDao optionValueDao;
	private CartBean cartBean;
	private CurrencyResolver currencyResolver;

	public CartController() {
	}

	@Autowired
	public CartController(CartItemDao cartItemDao, OptionDao optionDao, OptionValueDao optionValueDao, CartBean cartBean, CartResolver cartResolver, CurrencyResolver currencyResolver) {
		this.cartItemDao = cartItemDao;
		this.cartResolver = cartResolver;
		this.optionDao = optionDao;
		this.optionValueDao = optionValueDao;
		this.cartBean = cartBean;
		this.currencyResolver = currencyResolver;
	}

	// Cart is shortuct to the own shoppingcart from the cookie.
	@RequestMapping(value = "/cart/{id}", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findCarts(ModelMap model) {
		//Collection<Cart> cartList = null;
		// cartList = cartDao.findForUser(user);
		// model.addAttribute("productList", productList);
		// model.addAttribute("user", userService.getCurrentUser());
		return "cart/cart";
	}

	// Cart is shortuct to the own shoppingcart from the cookie.
	@RequestMapping(value = "/cart/myCart/cartItems", method = org.springframework.web.bind.annotation.RequestMethod.POST)
	public String createCartItem(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "amount", required = false) Long amount, @RequestParam("productId") Long productId, ModelMap model) {
		if (amount == null || !(amount > 0))
			amount = 1L;

		String cartId = cartResolver.resolveCartId(request);
		if (cartId == null) {
			cartId = cartBean.getNewCartId();
			cartResolver.setCartId(request, response, cartId);
		}
		CartItem cartItem = new CartItem(productId, cartId, amount);
		cartItemDao.create(cartItem);

		List<Option> options = optionDao.findByProduct(productId);
		for (Iterator<Option> iterator = options.iterator(); iterator.hasNext();) {
			Option option = iterator.next();
			String value = request.getParameter(option.getKey());
			if (value != null && ((option.getType().equals("input")) || option.getValue().equals(value))) {
				OptionValue optionValue = new OptionValue(value, cartItem.getId(), option.getId());
				optionValueDao.create(optionValue);
			}

		}
		return "redirect:/cart/myCart/cartItems/" + cartItem.getId();
	}

	// Cart is shortuct to the own shoppingcart from the cookie.
	@RequestMapping(value = "/cart/myCart/cartItems/{id}", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findCartItem(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id, ModelMap model) {
		CartItem cartItem = cartItemDao.find(id);
		model.addAttribute("cartItem", cartItem);
		return "/cart/cartItem";
	}

	// Cart is shortuct to the own shoppingcart from the cookie.
	@RequestMapping(value = "/cart/myCart/cartItems/{id}/optionValues", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findOptionValues(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id, ModelMap model) {
		List<OptionValue> optionValueList = optionValueDao.findByCartItem(id);
		model.addAttribute("optionValueList", optionValueList);
		// model.addAttribute("optionValueMap",
		// OptionValueBean.getOptionValueMap(optionValueList));
		return "/cart/optionValues";
	}

	// Cart is shortuct to the own shoppingcart from the cookie.
	@RequestMapping(value = "/cart/myCart/cartItems", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findCartItems(HttpServletRequest request, ModelMap model) {
		String cartId = cartResolver.resolveCartId(request);
		List<CartItem> cartItemList = cartItemDao.findByCart(cartId);
		model.addAttribute("cartId", cartId);
		model.addAttribute("cartItemList", cartItemList);
		return "/cart/cartItems";
	}

	// Cart is shortuct to the own shoppingcart from the cookie.
	@Transactional( propagation = Propagation.REQUIRES_NEW)
	@RequestMapping(value = "/cart/myCart/cartItems/{id}", method = org.springframework.web.bind.annotation.RequestMethod.PUT)
	public String updateCartItem(ModelMap model, @PathVariable("id") Long id, @RequestParam(value = "amount", required = false) Long amount) {
		CartItem cartItem = cartItemDao.find(id);
		cartItem.setAmount(amount);
		cartItemDao.update(cartItem);
		return "redirect:/cart/myCart/cartItems/" + cartItem.getId();
	}

	// Cart is shortuct to the own shoppingcart from the cookie.
	@RequestMapping(value = "/cart/myCart/cartItems/{id}", method = org.springframework.web.bind.annotation.RequestMethod.DELETE)
	public String deleteCartItem(ModelMap model, @PathVariable("id") Long id) {
		cartItemDao.delete(id);
		return "redirect:/cart/myCart/cartItems";
	}

	// Cart is shortuct to the own shoppingcart from the cookie.
	@RequestMapping(value = "/cart/myCart/cartItems", method = org.springframework.web.bind.annotation.RequestMethod.DELETE)
	public String deleteCartItems(HttpServletRequest request, ModelMap model) {
		String cartId = cartResolver.resolveCartId(request);
		List<CartItem> cartItemList = cartItemDao.findByCart(cartId);

		for (Iterator<CartItem> iterator = cartItemList.iterator(); iterator.hasNext();) {
			CartItem cartItem = iterator.next();
			cartItemDao.delete(cartItem.getId());
		}

		return "redirect:/cart/myCart/cartItems";
	}

	@RequestMapping(value = "/cart/myCart/prices/locale", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findCartPrice(HttpServletRequest request, ModelMap model) {
		Currency currency = currencyResolver.resolveCurrency(request);
		String cartId = cartResolver.resolveCartId(request);
		Price price = cartBean.getPrice(new Cart(cartId), currency);
		model.addAttribute("price", price);
		return "cart/cartPrice";
	}

	@RequestMapping(value = "/cart/myCart/cartItems/{id}/prices/locale", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findCartItemPrice(HttpServletRequest request, @PathVariable("id") Long id, ModelMap model) {
		Currency currency = currencyResolver.resolveCurrency(request);
		CartItem cartItem = cartItemDao.find(id);
		Price price = cartBean.getPrice(cartItem, currency);
		model.addAttribute("price", price);
		return "cart/cartItemPrice";
	}

}

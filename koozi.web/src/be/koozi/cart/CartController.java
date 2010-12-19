package be.koozi.cart;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Currency;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import be.koozi.product.Option;
import be.koozi.tools.CookieCurrencyResolver;
import be.koozi.users.UserDao;

import com.google.appengine.api.users.UserService;

@Controller
public class CartController {
	public static final String CART_REQUEST_ATTRIBUTE_NAME = CookieCurrencyResolver.class.getName() + ".CURRENCY";

	public static final String DEFAULT_COOKIE_NAME = CookieCurrencyResolver.class.getName() + ".CURRENCY";

	private CartItemDao cartItemDao;
	private CartDao cartDao;
	private CartResolver cartResolver;
	private UserDao userDao;
	private UserService userService;

	public CartController() {
	}

	@Autowired
	public CartController(CartDao cartDao, CartItemDao cartItemDao, UserDao userDao, UserService userService, CartResolver cartResolver) {
		this.cartDao = cartDao;
		this.cartItemDao = cartItemDao;
		this.userDao = userDao;
		this.userService = userService;
		this.cartResolver = cartResolver;
	}

	// Cart is shortuct to the own shoppingcart from the cookie.
	@RequestMapping(value = "/cart/{id}", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findCarts(ModelMap model) {
		Collection<Cart> cartList = null;
		// cartList = cartDao.findForUser(user);
		// model.addAttribute("productList", productList);
		// model.addAttribute("user", userService.getCurrentUser());
		return "cart/cart";
	}

	// Cart is shortuct to the own shoppingcart from the cookie.
	@RequestMapping(value = "/cart/myCart/cartItems", method = org.springframework.web.bind.annotation.RequestMethod.POST)
	public String createCart(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "amount", required = false) Long amount,  @RequestParam("productId") Long productId, ModelMap model) {
		if(amount == null || !(amount > 0) )
			amount = 1L;
			
		String cartId = cartResolver.resolveCartId(request);
		if(cartId == null)
		{
			cartId = getNewCartId();
			cartResolver.setCartId(request, response, cartId);
		}
		CartItem cartItem = new CartItem(productId, cartId, amount);
		cartItemDao.create(cartItem);
		
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
	@RequestMapping(value = "/cart/myCart/cartItems", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findCartItems(HttpServletRequest request,  ModelMap model) {
		String cartId = cartResolver.resolveCartId(request);
		List<CartItem> cartItemList = cartItemDao.findByCart(cartId);
		model.addAttribute("cartId", cartId);
		model.addAttribute("cartItemList", cartItemList);
		return "/cart/cartItems";
	}
	
	// Cart is shortuct to the own shoppingcart from the cookie.
	@RequestMapping(value = "/cart/myCart/cartItems/{id}", method = org.springframework.web.bind.annotation.RequestMethod.PUT)
	public String updateCartItem( ModelMap model, @PathVariable("id") Long id,  @RequestParam(value = "amount", required = false) Long amount) {
		CartItem cartItem = cartItemDao.find(id);
		cartItem.setAmount(amount);
		cartItemDao.update(cartItem);
		return "redirect:/cart/myCart/cartItems/" + cartItem.getId();
	}
	
	// Cart is shortuct to the own shoppingcart from the cookie.
	@RequestMapping(value = "/cart/myCart/cartItems/{id}", method = org.springframework.web.bind.annotation.RequestMethod.DELETE)
	public String deleteCartItem( ModelMap model, @PathVariable("id") Long id) {
		cartItemDao.delete(id);
		return "redirect:/cart/myCart/cartItems";
	}
	
	public String getNewCartId()
	{
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}
	
}

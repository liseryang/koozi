package be.koozi.cart;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import be.koozi.users.User;
import be.koozi.users.UserDao;

import com.google.appengine.api.users.UserService;
@Controller
public class CartController {
	

		private CartDao cartDao;
		private UserDao userDao;
		private UserService userService;

		public CartController() {
		}

		@Autowired
		public CartController(CartDao cartDao, UserDao userDao, UserService userService) {
			this.cartDao = cartDao;
			this.userDao = userDao;
			this.userService = userService;
		}

		//Cart is shortuct to the own shoppingcart from the cookie.
		@RequestMapping(value = "/cart", method = org.springframework.web.bind.annotation.RequestMethod.GET)
		public String findCart(ModelMap model) {
			Collection<Cart> cartList = null;
			//cartList = cartDao.findForUser(user);
//			model.addAttribute("productList", productList);
//			model.addAttribute("user", userService.getCurrentUser());
			return "cart/cart";
		}
}

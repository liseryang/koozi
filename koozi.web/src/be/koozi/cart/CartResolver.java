package be.koozi.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CartResolver {

	String resolveCartId(HttpServletRequest request);

	void setCartId(HttpServletRequest request, HttpServletResponse response, String cartId);

}

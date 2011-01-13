package be.koozi.order;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Currency;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import be.koozi.cart.Cart;
import be.koozi.cart.CartBean;
import be.koozi.cart.CartDao;
import be.koozi.cart.CartItem;
import be.koozi.cart.CartItemDao;
import be.koozi.cart.OptionValue;
import be.koozi.cart.OptionValueDao;
import be.koozi.product.Price;
import be.koozi.product.PriceDao;

public class OrderBean {

	OrderItemDao orderItemDao;
	OrderDao orderDao;

	CartDao cartDao;
	CartItemDao cartItemDao;
	OptionValueDao optionValueDao;
	PriceDao priceDao;
	CartBean cartBean;
	OrderItemOptionValueDao orderItemOptionValueDao;

	@Autowired
	public OrderBean(OrderItemOptionValueDao orderItemOptionValueDao, CartBean cartBean, OrderItemDao orderItemDao, OrderDao orderDao, CartDao cartDao, CartItemDao cartItemDao, OptionValueDao optionValueDao, PriceDao priceDao) {
		super();
		this.orderItemDao = orderItemDao;
		this.orderDao = orderDao;
		this.cartDao = cartDao;
		this.cartItemDao = cartItemDao;
		this.optionValueDao = optionValueDao;
		this.priceDao = priceDao;
		this.cartBean = cartBean;
		this.orderItemOptionValueDao = orderItemOptionValueDao;
	}

	public Order createOrder(String cartId, Currency currency) {
		BigDecimal total = new BigDecimal(0);

		Cart cart = new Cart(cartId);
		List<CartItem> cartItems = cartItemDao.findByCart(cartId);

		String orderId = getNewOrderId();

		for (Iterator<CartItem> iterator = cartItems.iterator(); iterator.hasNext();) {
			CartItem cartItem = iterator.next();
			Price productPrice = cartBean.getPrice(cart, currency);
			Value orderItemPrice = null;
			if (productPrice != null) {
				BigDecimal amountProduct = productPrice.getAmount();
				total = total.add(amountProduct);
				orderItemPrice = new Value(productPrice);
			}
			OrderItem orderItem = new OrderItem(cartItem.getProductId(), orderId, cartItem.getAmount(), orderItemPrice);
			orderItemDao.create(orderItem);

			List<OptionValue> optionValues = optionValueDao.findByCartItem(cartItem.getId());
			for (Iterator<OptionValue> iterator2 = optionValues.iterator(); iterator2.hasNext();) {
				OptionValue optionValue = iterator2.next();
				Price optionValuePrice = priceDao.findByOption(currency, optionValue.getOptionId());
				Value optionPrice = null;
				if (optionValuePrice != null) {
					BigDecimal amount = optionValuePrice.getAmount();
					total = total.add(amount);
					optionPrice = new Value(optionValuePrice);
				}
				OrderItemOptionValue orderItemOptionValue = new OrderItemOptionValue(optionValue.getValue(), orderItem.getId(), optionValue.getOptionId(), optionPrice);
				orderItemOptionValueDao.create(orderItemOptionValue);
			}
		}
		Order order = new Order(orderId);
		Value orderPrice = new Value(total, currency);
		order.setPrice(orderPrice);
		orderDao.create(order);
		return order;
	}

	public String getNewOrderId() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

}

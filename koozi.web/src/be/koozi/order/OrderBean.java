package be.koozi.order;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Currency;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import be.koozi.cart.Cart;
import be.koozi.cart.CartBean;
import be.koozi.cart.CartDao;
import be.koozi.cart.CartItem;
import be.koozi.cart.CartItemDao;
import be.koozi.cart.OptionValue;
import be.koozi.cart.OptionValueDao;
import be.koozi.product.Price;
import be.koozi.product.PriceDao;
import be.koozi.shipping.KialaShipping;
import be.koozi.shipping.KialaShippingDao;

public class OrderBean {

	OrderItemDao orderItemDao;
	OrderDao orderDao;

	CartDao cartDao;
	CartItemDao cartItemDao;
	OptionValueDao optionValueDao;
	PriceDao priceDao;
	CartBean cartBean;
	OrderItemOptionValueDao orderItemOptionValueDao;
	KialaShippingDao kialaShippingDao;

	public OrderBean(){}
			
	@Autowired
	public OrderBean(OrderItemOptionValueDao orderItemOptionValueDao, CartBean cartBean, OrderItemDao orderItemDao, OrderDao orderDao, CartDao cartDao, CartItemDao cartItemDao, OptionValueDao optionValueDao, PriceDao priceDao, KialaShippingDao kialaShippingDao) {
		super();
		this.orderItemDao = orderItemDao;
		this.orderDao = orderDao;
		this.cartDao = cartDao;
		this.cartItemDao = cartItemDao;
		this.optionValueDao = optionValueDao;
		this.priceDao = priceDao;
		this.cartBean = cartBean;
		this.orderItemOptionValueDao = orderItemOptionValueDao;
		this.kialaShippingDao = kialaShippingDao;
	}

	public Order createOrder(String cartId, Currency currency) {
		BigDecimal total = new BigDecimal(0);

		Cart cart = new Cart(cartId);
		List<CartItem> cartItems = cartItemDao.findByCart(cartId);

		Order order = new Order(getNewOrderId());
		Value orderPrice = new Value(total, currency);
		order.setPrice(orderPrice);
		//order.setShippingMethod("test");
		orderDao.create(order);
		
		for (Iterator<CartItem> iterator = cartItems.iterator(); iterator.hasNext();) {
			CartItem cartItem = iterator.next();
			Price productPrice = cartBean.getPrice(cart, currency);
			Value orderItemPrice = null;
			if (productPrice != null) {
				BigDecimal amountProduct = productPrice.getAmount();
				total = total.add(amountProduct);
				orderItemPrice = new Value(productPrice);
			}
			OrderItem orderItem = new OrderItem(cartItem.getProductId(), order.getId(), cartItem.getAmount(), orderItemPrice);
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

		return order;
	}
	
	@Transactional( propagation = Propagation.REQUIRES_NEW)
	public void updateOrder(String orderId, String shippingMethod, String paymentMethod)
	{
		Order updateOrder = orderDao.find(orderId);
		if(paymentMethod != null)
			updateOrder.setPaymentMethod(paymentMethod);
		if(shippingMethod != null)
			updateOrder.setShippingMethod(shippingMethod);
		orderDao.update(updateOrder);
	}
	
	@Transactional( propagation = Propagation.REQUIRES_NEW)
	public void updateShipping(String orderId,String name, String postCode,String shortCode, String street,
			String hint,String city, String openingHours)
	{
		KialaShipping kialaShipping = new KialaShipping();

		List<KialaShipping> shippingList = kialaShippingDao.findByOrder(orderId.toString());
		if (shippingList.size() != 0)
			kialaShipping = shippingList.get(0);
		else
			kialaShipping.setOrderId(orderId);

		kialaShipping.setName(name);
		kialaShipping.setCity(city);
		kialaShipping.setPostCode(postCode);
		kialaShipping.setShortCode(shortCode);
		kialaShipping.setStreet(street);
		kialaShipping.setHint(hint);
		kialaShippingDao.update(kialaShipping);

	}

	public String getNewOrderId() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

}

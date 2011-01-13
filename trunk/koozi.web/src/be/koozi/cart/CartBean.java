package be.koozi.cart;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Currency;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import be.koozi.product.Price;
import be.koozi.product.PriceDao;

public class CartBean {

	private PriceDao priceDao;
	private CartItemDao cartItemDao;
	private OptionValueDao optionValueDao;

	@Autowired
	public CartBean(PriceDao priceDao, CartItemDao cartItemDao, OptionValueDao optionValueDao) {
		super();
		this.priceDao = priceDao;
		this.cartItemDao = cartItemDao;
		this.optionValueDao = optionValueDao;
	}

	public String getNewCartId() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

	public Price getPrice(CartItem cartItem, Currency currency) {
		BigDecimal total = new BigDecimal(0);

		Price productPrice = priceDao.findByProduct(currency, cartItem.getProductId());
		if (productPrice != null) {
			BigDecimal amountProduct = productPrice.getAmount();
			total = total.add(amountProduct);
		}
		List<OptionValue> optionValues = optionValueDao.findByCartItem(cartItem.getId());
		for (Iterator<OptionValue> optionValueIterator = optionValues.iterator(); optionValueIterator.hasNext();) {
			OptionValue optionValue = (OptionValue) optionValueIterator.next();
			Price optionValuePrice = priceDao.findByOption(currency, optionValue.getOptionId());
			if (optionValuePrice != null) {
				BigDecimal amount = optionValuePrice.getAmount();
				total = total.add(amount);
			}
		}

		return new Price(total, currency);
	}

	public Price getPrice(Cart cart, Currency currency) {
		BigDecimal total = new BigDecimal(0);
		List<CartItem> cartItems = cartItemDao.findByCart(cart.getId());
		for (Iterator<CartItem> iterator = cartItems.iterator(); iterator.hasNext();) {
			CartItem cartItem = (CartItem) iterator.next();

			Price productPrice = priceDao.findByProduct(currency, cartItem.getProductId());
			if (productPrice != null) {
				BigDecimal amountProduct = productPrice.getAmount();
				total = total.add(amountProduct);
			}
			List<OptionValue> optionValues = optionValueDao.findByCartItem(cartItem.getId());
			for (Iterator<OptionValue> optionValueIterator = optionValues.iterator(); optionValueIterator.hasNext();) {
				OptionValue optionValue = (OptionValue) optionValueIterator.next();
				Price optionValuePrice = priceDao.findByOption(currency, optionValue.getOptionId());
				if (optionValuePrice != null) {
					BigDecimal amount = optionValuePrice.getAmount();
					total = total.add(amount);
				}
			}

		}
		return new Price(total, currency);
	}
}

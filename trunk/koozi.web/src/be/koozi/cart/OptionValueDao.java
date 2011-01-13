package be.koozi.cart;

import java.util.List;

import be.koozi.entity.EntityDao;

public interface OptionValueDao extends EntityDao<OptionValue, Long> {
	List<OptionValue> findByCartItem(Long cartItemId);
}

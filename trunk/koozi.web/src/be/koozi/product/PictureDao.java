package be.koozi.product;

import java.util.Collection;

import be.koozi.entity.EntityDao;

public interface PictureDao extends EntityDao<Picture, Long> {
	Collection<Picture> findByProduct(Long productId);
}

package be.koozi.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	String tag;
	Long productId;

	public ProductTag(String tag, Long productId) {
		this.tag = tag;
		this.productId = productId;
	}

	public String getTag() {
		return tag;
	}

	public Long getId() {
		return id;
	}

	public Long getProductId() {
		return productId;
	}

	@Override
	public boolean equals(Object aThat) {
		// check for self-comparison
		if (this == aThat)
			return true;

		if (!(aThat instanceof ProductTag))
			return false;
		// Alternative to the above line :
		// if ( aThat == null || aThat.getClass() != this.getClass() ) return
		// false;

		// cast to native object is now safe
		ProductTag that = (ProductTag) aThat;

		// now a proper field-by-field evaluation can be made
		return this.tag.equals(that.tag) && this.id.equals(that.getId());
	}

	public int hashCode() {
		return (int) ((tag != null ? tag.hashCode() : 0) + this.id + this.productId);
	}

}

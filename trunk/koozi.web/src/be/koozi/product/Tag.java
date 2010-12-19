package be.koozi.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	String value;
	Long productId;

	public Tag(String tag, Long productId) {
		this.value = tag;
		this.productId = productId;
	}

	public String getValue() {
		return value;
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

		if (!(aThat instanceof Tag))
			return false;
		// Alternative to the above line :
		// if ( aThat == null || aThat.getClass() != this.getClass() ) return
		// false;

		// cast to native object is now safe
		Tag that = (Tag) aThat;

		// now a proper field-by-field evaluation can be made
		return this.value.equals(that.value) && this.id.equals(that.getId());
	}

	public int hashCode() {
		return (int) ((value != null ? value.hashCode() : 0) + this.id + this.productId);
	}

}

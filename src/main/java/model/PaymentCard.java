package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

@Entity(name = "PaymentCard")
@Table(name = "payment_card")
public class PaymentCard extends BaseEntity {

	@Max(value = 16)
	@Min(value = 16)
	@NotBlank
	@Column(name = "card_number", length = 16, unique = true)
	private Integer cardNumber;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id")
	private User cardOwner;

	@NotBlank
	@Column(name = "cvv", length = 3)
	private Integer cvv;

	public Integer getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Integer cardNumber) {
		this.cardNumber = cardNumber;
	}

	public User getCardOwner() {
		return cardOwner;
	}

	public void setCardOwner(User cardOwner) {
		this.cardOwner = cardOwner;
	}

	public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((cardOwner == null) ? 0 : cardOwner.hashCode());
		result = prime * result + ((cvv == null) ? 0 : cvv.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentCard other = (PaymentCard) obj;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (cardOwner == null) {
			if (other.cardOwner != null)
				return false;
		} else if (!cardOwner.equals(other.cardOwner))
			return false;
		if (cvv == null) {
			if (other.cvv != null)
				return false;
		} else if (!cvv.equals(other.cvv))
			return false;
		return true;
	}

}

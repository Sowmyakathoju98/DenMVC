package com.virtusa.denorm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class CardDetail {
	@Id
	@GeneratedValue
	private int cardId;
	@NotBlank(message="CardNumber cannot be null")
	@Size(min=15,message="CardNumber must be 15 digits")
	private String cardNumber;
	@NotBlank(message="please enter card holder name")
	private String cardName;
	@NotBlank(message="Choose correct type")
	private String cardType;
	private String expiryDate;
	@NotBlank(message="CVV cannot be null")
	@Size(min=3,message="please enter 3 digits cvv")
	private String CVV;

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCVV() {
		return CVV;
	}

	public void setCVV(String CVV) {
		this.CVV = CVV;
	}

	public CardDetail(String cardNumber, String cardName, String cardType, String expiryDate, String CVV) {
		super();
		this.cardNumber = cardNumber;
		this.cardName = cardName;
		this.cardType = cardType;
		this.expiryDate = expiryDate;
		this.CVV = CVV;
	}

	@Override
	public String toString() {
		return "CardDetail [cardId=" + cardId + ", cardNumber=" + cardNumber + ", cardName=" + cardName + ", cardType="
				+ cardType + ", ExpiryDate=" + expiryDate + ", CVV=" + CVV + "]";
	}

	public CardDetail() {
	}

}

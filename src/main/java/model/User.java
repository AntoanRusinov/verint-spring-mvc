package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import annotation.ValidPhone;

@Entity(name = "User")
@Table(name = "user")
public class User extends BaseEntity {

	@NotBlank
	@Column(name = "firstName")
	private String firstName;

	@NotBlank
	@Column(name = "lastName")
	private String lastName;

	private String password;

	@OneToOne(mappedBy = "cardOwner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private PaymentCard paymentCard;

	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Article> articles = new ArrayList<>();

	@Email
	@NotBlank
	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender = Gender.UNKNOWN;

	@ValidPhone
	@Column(name = "phone")
	private String phone;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PaymentCard getPaymentCard() {
		return paymentCard;
	}

	public void setPaymentCard(PaymentCard paymentCard) {
		this.paymentCard = paymentCard;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
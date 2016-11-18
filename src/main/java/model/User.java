package model;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

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

	@OneToMany(mappedBy = "author", fetch = FetchType.LAZY, targetEntity = Article.class)
	private Collection<Article> articles = new HashSet<>();

	@Email
	@NotBlank
	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender = Gender.UNKNOWN;

	@Column(name = "money_amount")
	private Integer moneyAmount;

}
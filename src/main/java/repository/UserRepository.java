package repository;

import model.Article;
import model.PaymentCard;
import model.User;

public interface UserRepository {

	public User getUserById(Long userId);

	public void createUser(User user);

	public void updateUser(User user);

	public void deleteUser(Long userId);

	public void addArticle(Article article);

	public void deleteArticle(Article article);

	public void updatePaymentCardForUser(Long userId, PaymentCard paymentCard);

}
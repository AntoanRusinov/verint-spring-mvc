package repository;

import model.Article;
import model.PaymentCard;
import model.User;

public interface UserRepository {

	public void createUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(long userId);
	
	public void addArticle(Article article);
	
	public void deleteArticle(Article article);
	
	public void addPaymentCardToUser(long userId, PaymentCard paymentCard);
	
	public void replacePaymentCardToUser(long userId, PaymentCard paymentCard);
	
}
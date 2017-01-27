package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Article;
import model.PaymentCard;
import model.User;
import repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User getUserById(long userId) {
		return userRepository.getUserById(userId);
	}

	public void createUser(User user) {
		userRepository.createUser(user);
	}

	public void updateUser(User user) {
		userRepository.updateUser(user);
	}

	public void deleteUser(long userId) {
		userRepository.deleteUser(userId);
	}

	public void addArticle(Article article) {
		userRepository.addArticle(article);
	}

	public void deleteArticle(Article article) {
		userRepository.deleteArticle(article);
	}

	public void updatePaymentCardForUser(long userId, PaymentCard paymentCard) {
		userRepository.updatePaymentCardForUser(userId, paymentCard);
	}

}
package repository.impl;

import org.springframework.stereotype.Repository;

import model.Article;
import model.PaymentCard;
import model.User;
import repository.UserRepository;

@Repository
// @Transactional
public class UserRepositoryImpl implements UserRepository {

	@Override
	public void createUser(User user) {

	}

	@Override
	public void updateUser(User user) {

	}

	@Override
	public void deleteUser(long userId) {

	}

	@Override
	public void addArticle(Article article) {

	}

	@Override
	public void deleteArticle(Article article) {

	}

	@Override
	public void addPaymentCardToUser(long userId, PaymentCard paymentCard) {

	}

	@Override
	public void replacePaymentCardToUser(long userId, PaymentCard paymentCard) {

	}

}
package repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Article;
import model.PaymentCard;
import model.User;
import repository.UserRepository;

@Repository
// @Transactional
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private static SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public User getUserById(long userId) {
		return (User) getSession().get(User.class, userId);
	}

	@Override
	public void createUser(User user) {
		getSession().save(user);
	}

	@Override
	public void updateUser(User user) {
		getSession().update(user);
	}

	@Override
	public void deleteUser(long userId) {
		getSession().delete(getUserById(userId));
	}

	@Override
	public void addArticle(Article article) {
		getSession().save(article);
	}

	@Override
	public void deleteArticle(Article article) {
		getSession().delete(article);
	}

	@Override
	public void updatePaymentCardForUser(long userId, PaymentCard paymentCard) {
		User user = getUserById(userId);
		user.setPaymentCard(paymentCard);
		getSession().update(user);
	}

}
package application;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import configuration.WebConfig;
import model.Article;
import model.User;

//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = { WebConfig.class })
//@WebAppConfiguration
//@ActiveProfiles(value = "testing")
public class HibernateMappingTests {

	@Autowired
	@PersistenceContext
	private EntityManager entityManager;

	@Test
	public void test() {
		User person = new User();
		Article article1 = new Article();
		Article article2 = new Article();

		ArrayList<Article> list = new ArrayList<>();
		list.add(article1);
		list.add(article2);

		person.setArticles(list);

		entityManager.persist(person);
		entityManager.flush();

		System.out.println("Articles after set " + person.getArticles());
		ArrayList<Article> emptyList = new ArrayList<>();

		person.setArticles(emptyList);

		entityManager.persist(person);
		entityManager.flush();

		System.out.println("Articles after removing " + person.getArticles());
	}
}

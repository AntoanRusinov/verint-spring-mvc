package application;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import configuration.AppConfig;
import configuration.WebConfig;
import model.Article;
import model.User;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {AppConfig.class, WebConfig.class})
//@WebAppConfiguration
public class HibernateMappingTests {

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
		
		entityManager.persist( person );
		entityManager.flush();

		System.out.println("Articles after set " + person.getArticles());
		ArrayList<Article> emptyList = new ArrayList<>();
		
		person.setArticles(emptyList);
		
		entityManager.persist( person );
		entityManager.flush();

		System.out.println("Articles after removing " + person.getArticles());
	}
}

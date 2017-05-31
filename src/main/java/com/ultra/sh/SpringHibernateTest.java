package com.ultra.sh;

import java.util.Arrays;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

import com.ultra.sh.service.BookManager;
import com.ultra.sh.service.BookService;

public class SpringHibernateTest {

	private ApplicationContext context;
	private BookManager bookManager;
	private BookService bookService;

	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext("application.xml");
		bookManager = (BookManager) context.getBean("bookManager");
		bookService = (BookService) context.getBean("bookService");
	}

	@Test
	public void testTransactionPropagation() {
		bookManager.buy(1, Arrays.asList(1, 2));
	}

	@Test
	public void testTransaction() {
		bookService.buy(1, 1);
	}

	@Test
	public void testDataSource() {
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		System.out.println(dataSource);
	}
}

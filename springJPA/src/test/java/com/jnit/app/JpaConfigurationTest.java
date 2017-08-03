package com.jnit.app;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.jnit.app.model.Students;

public class JpaConfigurationTest extends ParentTest{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Test
	public void testIntegration(){
		System.out.println("Everything good");
	}
	
	@Test
	@Transactional
	public void testHibernateSession(){
		Session session = entityManager.unwrap(Session.class);
		Students students = new Students("ryz147@gmail.com", "Riyaz", "Md", "ryz147", LocalDate.of(1988, 18, 07), null);
		session.save(students);
		//session.flush();
		session.getTransaction().commit();
		//Students students1 = session.get(Students.class, 1L);
		//System.out.println(students1.getfName());
		
	}
}

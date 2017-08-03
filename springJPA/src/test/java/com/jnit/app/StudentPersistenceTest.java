package com.jnit.app;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jnit.app.model.Students;
import com.jnit.app.repositories.StudentRepository;

public class StudentPersistenceTest extends ParentTest {

	@Autowired
	private StudentRepository studentRepository;

	 @Test
	public void testStudentCreation() {
		Students students = new Students("ryz147@gmail.com", "Riyaz", "Md", "ryz147", LocalDate.of(1988, 07, 18), null);
		Students createdStudents = studentRepository.save(students);
		assertNotNull("student id not present", createdStudents.getStudentName());
	}

	@Test
	public void testFindStudentById() {
		Students students = studentRepository.findOne(1L);
		assertNotNull("student name not present", students.getStudentName());
	}

	@Test
	public void testFindAll() {
		List<Students> students = studentRepository.findAll();
		assertTrue("student list is empty", students.size() > 0);
	}

	// @Test
	public void testDeleteStudent() {
		Students students = studentRepository.findOne(1L);
		studentRepository.delete(students);
		Students studentObj = studentRepository.findOne(1L);
		assertNull("student seems to be not deleted", studentObj);
	}

	@Test
	public void testFindUserByStudentName() {
		Optional<Students> userOptional = studentRepository.findByStudentName("ryz147@gmail.com");
		assertTrue("student not found with the student name", userOptional.isPresent());
	}

	@Test
	public void testFindByLastName() {
		List<Students> students = studentRepository.findByLName("Riyaz");
		assertTrue("students list is empty", students.size() > 0);
	}

	@Test
	public void testCountByLastName() {
		Long count = studentRepository.countByLName("MD");
		assertTrue("students list is empty", count > 0);
	}

	@Test
	public void testFindByFNameAndLName() {
		Optional<Students> studentOptional = studentRepository.findByFNameAndLName("Riyaz", "MD");
		assertTrue("students not found with the provided first and lname", studentOptional.isPresent());
	}

	@Test
	public void testFindByFNameOrLName() {
		List<Students> students = studentRepository.findByFNameOrLName("Riyaz", "MD");
		assertTrue("students list is empty", students.size() > 0);
	}

	
	@Test
	public void testFindFirst3ByLNameOrderByStudentNameAsc() {
		List<Students> students = studentRepository.findFirst3ByLNameOrderByStudentNameAsc("MD");
		assertTrue("users list is empty", students.size() > 0);
	}

}

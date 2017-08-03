package com.jnit.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jnit.app.model.Students;

//find�By, read�By, query�By, count�By, and get�By.
public interface StudentRepository extends JpaRepository<Students, Long> {

	public Optional<Students> findByStudentName(String studentName);

	public List<Students> findByLName(String lastName);

	public Long countByLName(String lastName);

	public Optional<Students> findByFNameAndLName(String FirstName, String lastName);

	public List<Students> findByFNameOrLName(String FirstName, String lastName);

	public List<Students> findFirst3ByLNameOrderByStudentNameAsc(String lastName);

	

}

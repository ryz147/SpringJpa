package com.jnit.app.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

	@Entity
	public class Students implements Serializable {

		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long studentId;

		@Email(message = "user name is invalid")
		@NotBlank(message = "user name can not be blank")
		private String studentName;

		@NotBlank(message = "first name can not be blank")
		private String fName;

		@NotBlank(message = "last name can not be blank")
		private String lName;


		@NotBlank(message = "password can not be blank")
		private String password;

		@NotNull(message = "date of birth can not be blank")
		private LocalDate dob;

		private String phoneNumber;

		private LocalDateTime createdDateTime;

		@Version
		private LocalDateTime updatedDateTime;
		


		public Students() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Students( String studentName, String fName, String lName, String password, LocalDate dob,
				String phoneNumber) {
			super();

			this.studentName = studentName;
			this.fName = fName;
			this.lName = lName;
			this.password = password;
			this.dob = dob;
			this.phoneNumber = phoneNumber;
			
		}

		public Long getStudentId() {
			return studentId;
		}

		public void setStudentId(Long studentId) {
			this.studentId = studentId;
		}

		public String getStudentName() {
			return studentName;
		}

		public void setStudentName(String studentName) {
			this.studentName = studentName;
		}

		public String getfName() {
			return fName;
		}

		public void setfName(String fName) {
			this.fName = fName;
		}

		public String getlName() {
			return lName;
		}

		public void setlName(String lName) {
			this.lName = lName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public LocalDate getDob() {
			return dob;
		}

		public void setDob(LocalDate dob) {
			this.dob = dob;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public LocalDateTime getCreatedDateTime() {
			return createdDateTime;
		}

		public void setCreatedDateTime(LocalDateTime createdDateTime) {
			this.createdDateTime = createdDateTime;
		}

		public LocalDateTime getUpdatedDateTime() {
			return updatedDateTime;
		}

		public void setUpdatedDateTime(LocalDateTime updatedDateTime) {
			this.updatedDateTime = updatedDateTime;
		}
		
		
}

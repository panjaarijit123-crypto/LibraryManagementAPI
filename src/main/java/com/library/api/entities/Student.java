package com.library.api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int studentId;

    @NotBlank(message = "Student name cannot be blank")
    @Size(min = 3, max = 100,
          message = "Student name must be between 3 and 100 characters")
    @Column(name = "student_name", length = 100, nullable = false)
    private String studentName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please enter a valid email address")
    @Column(name = "email", length = 150, nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Department cannot be blank")
    @Size(min = 2, max = 100,
          message = "Department must be between 2 and 100 characters")
    @Column(name = "department", length = 100, nullable = false)
    private String department;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^[0-9]{10,15}$",
             message = "Phone number must contain 10 to 15 digits")
    @Column(name = "phone_number", length = 15, nullable = false)
    private String phoneNumber;
}
package com.projects.lakeSide_hotel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="user_name")
    private String userName;
    @Column(name= "date_of_birth")
    private LocalDate dob;
    @Column(name= "gender")
    private String gender;
    @Column(name= "password")
    private String password;
    @Column(name= "security_question")
    private String securityQuestion;
    @Column(name= "security_answer")
    private String securityAnswer;
}

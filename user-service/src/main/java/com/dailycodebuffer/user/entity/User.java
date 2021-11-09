package com.dailycodebuffer.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private Long departmentId;

    public User(String firstName, String lastName, String email, long departmentId)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departmentId = departmentId;
    }
}

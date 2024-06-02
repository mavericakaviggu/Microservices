package com.microservice.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor
// @Entity
// @Table(name="user") 
public class User {	
	// @Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	private String userName;
	private String userPhoneNumber;	   
    private Post post;
    private Notification notification;

    public User(Integer userId, String userName, String userPhoneNumber) {
        this.userId = userId;
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
    }

}
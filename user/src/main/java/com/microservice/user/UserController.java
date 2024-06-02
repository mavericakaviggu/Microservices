package com.microservice.user;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") int userId) {
        User user = new User(userId,"vignesh","9036116577");
        Post post = restTemplate.getForObject("http://localhost:8081/post/1",Post.class);
        user.setPost(post);
        Notification notification = restTemplate.getForObject("http://localhost:8082/notification/1",Notification.class);
        user.setNotification(notification);
        return user;
    }
    
    

}

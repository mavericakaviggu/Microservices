package com.microservice.user;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") int userId) {
        User user = new User(userId, "vignesh", "9036116577");
        try{
        Post post = restTemplate.getForObject("http://post/post/1", Post.class);
        user.setPost(post);
        }
        catch (RestClientException e) {
            logger.error("Error fetching post service", e);
            // Handle the error, set a default or fallback value
            user.setPost(new Post(1, "Default post"));
        }
        try{
        Notification notification = restTemplate.getForObject("http://notification/notification/1", Notification.class);
        user.setNotification(notification);
        }
        catch (RestClientException e) {
            logger.error("Error fetching notification service", e);
            // Handle the error, set a default or fallback value
            user.setNotification(new Notification(1, "Default notification"));
        }
        return user;
    }

}

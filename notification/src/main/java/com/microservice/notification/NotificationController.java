package com.microservice.notification;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @GetMapping("/{notificationId}")
    public Notification getPost(@PathVariable("notificationId") int notificationId) {
        Notification notification = new Notification(notificationId, "vignesh notification is on");
        return notification;
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "Test endpoint is working";
    }

}
package com.cyberclick.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
public class UserController {

    // Inject the rest template from the main class
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value="/{userId}")
    public User getUser(@PathVariable("userId") String userId){
        User userOne = new User(userId, "User Name"+ userId,
                "xxxxxx" +userId);

        Posts post = restTemplate.getForObject("http://localhost:8081/post/1",
                Posts.class);
        userOne.setPost(post);

        Notification notification = restTemplate.getForObject("http://localhost:8080/notification/1",
                Notification.class);
        userOne.setNotification(notification);


        return userOne;
    }


}

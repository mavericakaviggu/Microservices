package com.microservice.post;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/post")
public class PostController {

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable("postId") int postId) {
        Post post = new Post(postId,"vignesh is hard working");
        return post;
    } 
    

}

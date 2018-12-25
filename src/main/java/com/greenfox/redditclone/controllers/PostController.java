package com.greenfox.redditclone.controllers;

import com.greenfox.redditclone.repositories.entities.Post;
import com.greenfox.redditclone.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("posts")
public class PostController {


    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping({"","/","/list"})
    public String listPosts(Model model, @RequestParam(defaultValue = "0", value = "page")int page) {
        model.addAttribute("posts", postService.findAllPageOrderByVote(page));
        model.addAttribute("page", page);
        return "postList";
    }

    @GetMapping("/add")
    public String submitNewGet(Model model) {
        model.addAttribute("newpost", new Post());
        return "addPost";
    }

    @PostMapping("/add")
    public String submitNewPost(@ModelAttribute Post post) {
        postService.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/upvote/{id}")
    public String upVote(@PathVariable Long id, @RequestParam String page) {
            postService.upVote(id);
        return "redirect:/posts/?page=" + page;
    }

    @GetMapping("/downvote/{id}")
    public String downVote(@PathVariable Long id, @RequestParam String page) {
        postService.downVote(id);
        return "redirect:/posts/?page=" + page;
    }


}

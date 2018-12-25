package com.greenfox.redditclone.services;

import com.greenfox.redditclone.repositories.PostRepository;
import com.greenfox.redditclone.repositories.UserRepository;
import com.greenfox.redditclone.repositories.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public void save(Post post){
        post.setUser(userService.getCurrentUser());
        postRepository.save(post);
    }

    public Iterable<Post> findAll(){
        return postRepository.findAll();
    }

    public void upVote(Long id) {
        if(postRepository.findById(id).isPresent()) {
            Post tempPost = postRepository.findById(id).get();
            Long tempVote = tempPost.getVote();
            tempVote++;
            tempPost.setVote(tempVote);
            postRepository.save(tempPost);
        }
    }

    public void downVote(Long id) {
        if(postRepository.findById(id).isPresent()) {
            Post tempPost = postRepository.findById(id).get();
            Long tempVote = tempPost.getVote();
            tempVote--;
            tempPost.setVote(tempVote);
            postRepository.save(tempPost);
        }
    }


    public Page<Post> findAllPageOrderByVote(int page) {
        return postRepository.findAll(new PageRequest(page, 10, Sort.by("vote").descending()));

    }
}

package com.greenfox.redditclone;

import com.greenfox.redditclone.repositories.PostRepository;
import com.greenfox.redditclone.repositories.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URL;

@SpringBootApplication
public class RedditcloneApplication implements CommandLineRunner {

    private final PostRepository postRepository;

    @Autowired
    public RedditcloneApplication(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(RedditcloneApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 30; i++) {
            postRepository.save(new Post("cute kittes" +i ,new URL("https://www.youtube.com/watch?v=rLtyfmOBlIY" +i)));
        }
    }
}


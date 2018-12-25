package com.greenfox.redditclone.services;

import com.greenfox.redditclone.repositories.UserRepository;
import com.greenfox.redditclone.repositories.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService {

    private User currentUser;

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        currentUser = userRepository.save(user);
    }

    public User getCurrentUser() {
        return currentUser;
    }
}

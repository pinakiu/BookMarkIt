package com.bookTrackerProject.bookMarkIt.service;

import com.bookTrackerProject.bookMarkIt.domain.User;
import com.bookTrackerProject.bookMarkIt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository uRepo;
    @Autowired
    private AuthenticationManager authManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        uRepo.save(user);
        return user;
    }

    public String verify(User user) {
        //hey AuthManager, let me know if user is logged in or not and assign to object auth
        Authentication auth = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(auth.isAuthenticated())
            return "Success";
        return "Fail";
    }
}

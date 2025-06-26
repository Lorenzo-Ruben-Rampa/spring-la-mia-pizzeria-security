package org.lessons.java.pizza_security.security;
import java.util.Optional;
import org.lessons.java.pizza_security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.lessons.java.pizza_security.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class DatabaseUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
       Optional<User> userAttempt = userRepository.findByUsername(username);
       if (userAttempt.isEmpty()) {
           throw new UsernameNotFoundException("There are no users available with username: " + username);
       }
           return new DatabaseUserDetails(userAttempt.get());
    }
}
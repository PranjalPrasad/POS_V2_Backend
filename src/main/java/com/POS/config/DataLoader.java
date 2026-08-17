package com.POS.config;

import com.POS.entity.User;
import com.POS.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            userRepository.save(new User("8237876579", "pos@123", "Pranjal Prasad"));
            userRepository.save(new User("9123456780", "test@1234", "Priya Verma"));
            userRepository.save(new User("9998887776", "mypassword", "Amit Singh"));
        }
    }
}

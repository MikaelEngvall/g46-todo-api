package se.lexicon.g46todoapi.startup;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g46todoapi.domain.entity.User;
import se.lexicon.g46todoapi.repository.UserRepository;

import java.util.Optional;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Creating user
        User userOne = new User("test@test.com", "1234");
        // Setting expired to false
        userOne.setExpired(false);
        // Persisiting user
        userRepository.save(userOne);

        // These commit the change(s) and open for new transaction(s). Otherwise, it won't work.
        entityManager.flush();
        entityManager.clear();

        // Updating user with email test@test.com with expired value true
        userRepository.updateExpiredByEmail("test@test.com", true);
    }
}

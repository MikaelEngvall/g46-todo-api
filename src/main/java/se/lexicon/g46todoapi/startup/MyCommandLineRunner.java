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
        // Creating a user
        User userOne = new User("test@test.com", "1234");
        // Setting the value of expired to false
        userOne.setExpired(false);
        // Persisting the user into the DB
        entityManager.persist(userOne);

        // These commit the change and open for new transaction. Otherwise, it won't work
        entityManager.flush();
        entityManager.clear();

        // Updating the user's expired value to true by finding it by its email
        userRepository.updateExpiredByEmail("test@test.com", true);
    }

}

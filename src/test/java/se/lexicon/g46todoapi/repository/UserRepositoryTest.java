package se.lexicon.g46todoapi.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import se.lexicon.g46todoapi.domain.entity.User;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    EntityManager entityManager;


    @Test
    @Transactional
    @Rollback(false)
    void testUpdateExpiredByEmail() {
        // Create a new user and save it to the database.
        User userOne = new User("test@test.com", "1234");
        userOne.setExpired(false);
        userRepository.save(userOne);

        // These commit the change and open for new transaction. Otherwise, it won't work
        entityManager.flush();
        entityManager.clear();


        // Updates the user from the database by changing the value of expired from false to true.
        userRepository.updateExpiredByEmail("test@test.com", true);

        // Assert that the expired value of the user is now true.
        assertTrue(userRepository.getUserByEmail("test@test.com").isExpired());
    }

}

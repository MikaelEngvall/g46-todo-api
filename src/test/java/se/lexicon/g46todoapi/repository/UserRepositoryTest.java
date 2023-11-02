package se.lexicon.g46todoapi.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.annotation.Rollback;
import se.lexicon.g46todoapi.domain.entity.User;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository sut;

    @Test
    @Transactional
    @Rollback(false)
    void testUpdateExpiredByEmail() {
        // Create a new user and save it to the database.
        User userOne = new User("test@test.com", "1234");
        userOne.setExpired(false);
        sut.save(userOne);

        // Update the expired value of the user.


        // Refresh the user from the database.
        sut.updateExpiredByEmail("test@test.com", true);  // Not working, why? TODO

        // Assert that the expired value of the user is now true.
        assertTrue(sut.getUserByEmail("test@test.com").isExpired());
    }

}

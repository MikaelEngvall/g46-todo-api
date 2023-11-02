package se.lexicon.g46todoapi.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import se.lexicon.g46todoapi.domain.entity.Role;
import se.lexicon.g46todoapi.domain.entity.User;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    @Modifying
    void testUpdateExpiredByEmail() {
        // Create a new user and save it to the database.
        User userOne = new User("test@test.com", "1234");
        userOne.setExpired(false);
        userRepository.save(userOne);

        // Update the expired value of the user. So I hoped but I dunno if it is actually happening
        userRepository.updateExpiredByEmail("test@test.com", true);

        // Get the user from the db
        User updatedUser = userRepository.findById("test@test.com").orElse(null);

        // Assert that the expired value of the user is now true. But it isn't!!!!!
        assertThat(updatedUser.isExpired()).isTrue();
    }

}

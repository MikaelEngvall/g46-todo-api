package se.lexicon.g46todoapi.startup;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g46todoapi.domain.entity.Person;
import se.lexicon.g46todoapi.domain.entity.Role;
import se.lexicon.g46todoapi.domain.entity.Task;
import se.lexicon.g46todoapi.domain.entity.User;
import se.lexicon.g46todoapi.repository.PersonRepository;
import se.lexicon.g46todoapi.repository.RoleRepository;
import se.lexicon.g46todoapi.repository.TaskRepository;
import se.lexicon.g46todoapi.repository.UserRepository;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Creating users
        User userOne = new User("test@test.com", "1234");
        User userTwo = new User("mail@test.com", "4321");

        // Creating and persisting roles
        Role roleOne = new Role("ADMIN");
        roleRepository.save(roleOne);
        Role roleTwo = new Role("USER");
        roleRepository.save(roleTwo);

        // Setting the value of expired to false
        userOne.setExpired(false);

        // Adding the roles to the users
        userOne.addRole(roleOne);
        userTwo.addRole(roleTwo);

        // Persisting the user into the DB
        userRepository.save(userOne);
        userRepository.save(userTwo);

        // Creating and persisting people
        Person personOne = new Person("Mikael");
        personRepository.save(personOne);
        Person personTwo = new Person("Anders");
        personRepository.save(personTwo);

        // Creating and persisting tasks
        Task taskOne = new Task("Change tires", "Shift from summer to winter", LocalDate.now().plusDays(5), false, personTwo);
        taskRepository.save(taskOne);
        Task taskTwo = new Task("Attend meeting", "Scrum meeting", LocalDate.now().plusDays(5), false, personOne);
        taskRepository.save(taskTwo);

        // These commit the change and open for new transaction. Otherwise, it won't work
        entityManager.flush();
        entityManager.clear();

        // Updating the user's expired value to true by finding it by its email
        userRepository.updateExpiredByEmail("test@test.com", true);
    }

}

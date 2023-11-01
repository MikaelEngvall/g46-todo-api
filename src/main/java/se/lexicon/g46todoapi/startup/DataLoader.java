package se.lexicon.g46todoapi.startup;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.g46todoapi.domain.entity.Person;
import se.lexicon.g46todoapi.domain.entity.Role;
import se.lexicon.g46todoapi.domain.entity.Task;
import se.lexicon.g46todoapi.domain.entity.User;
import se.lexicon.g46todoapi.repository.PersonRepository;
import se.lexicon.g46todoapi.repository.RoleRepository;
import se.lexicon.g46todoapi.repository.TaskRepository;
import se.lexicon.g46todoapi.repository.UserRepository;


import java.time.LocalDate;

@Component
@Transactional
public class DataLoader implements CommandLineRunner {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private PersonRepository personRepository;


    @Override
    public void run(String... args) throws Exception {
        // execute this logic...
        // How to call save method of UserRepository Interface?

        // Roles
        Role admin = new Role("ADMIN");
        roleRepository.save(admin);
        Role userRole = new Role("USER");
        roleRepository.save(userRole);
        Role guest = new Role("GUEST");
        roleRepository.save(guest);

        //Users
        User mikaelUser = new User("test@email.se", "12345");
        mikaelUser.addRole(admin);
        userRepository.save(mikaelUser);
        User andersUser = new User("test2@email.se", "qwerty");
        andersUser.addRole(userRole);
        userRepository.save(andersUser);

        // People
        Person mikael = new Person("Mikael");
        mikael.setUser(mikaelUser);
        personRepository.save(mikael);
        Person anders = new Person("Anders");
        anders.setUser(andersUser);
        personRepository.save(anders);

        // Tasks   Startdate should be added to the task when created, right?
        Task task = new Task("Tjena", "Tjeeeenare", LocalDate.now().plusDays(3), false, mikael);
        taskRepository.save(task);
        Task task2 = new Task("Tjabba", "Tjabba", LocalDate.now().plusDays(4), false, null);
        taskRepository.save(task2);
        Task task3 = new Task("Hallo", "Halloj", LocalDate.now().plusDays(7), true, anders);
        taskRepository.save(task3);
        // add more roles as needed

        // Updates user with email test@mail.se to expired
        userRepository.updateExpiredByEmail("test@email.se", true);  //@Transactional must be added in order for this to work


        System.out.println("\u001B[31mTo be done in 7 days : \u001B[0m" + taskRepository.findTasksByDeadline(LocalDate.now().plusDays(7)));
        System.out.println("\u001B[31mNot done yet : \u001B[0m" + taskRepository.findTasksByNotDone());
        System.out.println("\u001B[31mNot assigned : \u001B[0m" + taskRepository.findTasksByUnassigned());
        System.out.println("\u001B[31mBetween today and in 3 days : \u001B[0m" + taskRepository.findTasksByDateBetweenStartAndEnd(LocalDate.now(), LocalDate.now().plusDays(3)));
    }
}

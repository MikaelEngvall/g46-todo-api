package se.lexicon.g46todoapi.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.g46todoapi.domain.entity.Person;
import se.lexicon.g46todoapi.domain.entity.Task;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Transactional
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private PersonRepository personRepository;

    @Test
    void testFindByTitleContainsIgnoreCase() {
        Task oneTask = new Task(); // inside
        Task oneTask2 = new Task(); // outside
        Task oneTask3 = new Task(); // rim
        oneTask.setTitle("title");
        oneTask2.setTitle("qwerty");
        oneTask3.setTitle("tl");

        taskRepository.save(oneTask);
        assertEquals(1, taskRepository.findByTitleContainsIgnoreCase("iTl").size());
    }

    @Test
    void testFindByPerson_Id() {
        Person twoPerson = new Person();
        personRepository.save(twoPerson);

        Person foundPerson = personRepository.findAll().get(0);
        Long foundPersonId = foundPerson.getId();

        Task twoTask = new Task();
        twoTask.setPerson(foundPerson);
        taskRepository.save(twoTask);

        assertEquals(1, taskRepository.findTasksByPerson_Id(foundPersonId).size());
    }

    @Test
    void testFindByDone() {
        Task threeTask = new Task();
        threeTask.setDone(true);
        taskRepository.save(threeTask);
        assertEquals(1, taskRepository.findByDone(true).size());
    }

    @Test
    void testFindByDeadLineBetween() {
        Task task1 = new Task(); // inside
        Task task2 = new Task(); // rim
        Task task3 = new Task(); // outside
        task1.setDeadline(LocalDate.now());
        task2.setDeadline(LocalDate.now().plusDays(1));
        task3.setDeadline(LocalDate.now().plusDays(2));
        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);

        LocalDate from = LocalDate.now();
        LocalDate to = LocalDate.now().plusDays(1);

        assertEquals(2, taskRepository.findByDeadlineBetween(from, to).size());
    }

    @Test
    void testFindByDeadline() {
        Task fourTask = new Task();
        fourTask.setDeadline(LocalDate.now());
        taskRepository.save(fourTask);

        assertEquals(1, taskRepository.findByDeadline(LocalDate.now()).size());
    }

    @Test
    void testFindByPersonNull() {
        Person fivePerson = new Person("name");
        personRepository.save(fivePerson);

        Task fiveTask1 = new Task();
        Task fiveTask2 = new Task();
        fiveTask2.setPerson(fivePerson);
        taskRepository.save(fiveTask1);
        taskRepository.save(fiveTask2);

        assertEquals(1, taskRepository.findByPersonNull().size());
    }

    @Test
    void testFindByPersonIsNotNull() {
        Person sixPerson = new Person();
        personRepository.save(sixPerson);

        Task sixTask1 = new Task();
        Task sixTask2 = new Task();
        sixTask1.setPerson(sixPerson);
        taskRepository.save(sixTask1);
        taskRepository.save(sixTask2);

        assertEquals(1, taskRepository.findByPersonNotNull().size());
    }

    @Test
    void findByDoneFalse() {
        Task task7 = new Task();
        Task task8 = new Task();
        task8.setDone(true);
        taskRepository.save(task7);
        taskRepository.save(task8);

        assertEquals(1, taskRepository.findByDoneFalse().size());
    }

    @Test
    void testFindByDoneFalseAndDeadlineAfter() {
        Task task11 = new Task();
        Task task12 = new Task();
        Task task13 = new Task();
        Task task14 = new Task();

        task11.setDeadline(LocalDate.now());
        task11.setDone(true);

        task12.setDeadline(LocalDate.now()); // false

        task13.setDeadline(LocalDate.now().minusDays(1));
        task13.setDone(true);

        task14.setDeadline(LocalDate.now().minusDays(1)); // false

        taskRepository.save(task11);
        taskRepository.save(task12);
        taskRepository.save(task13);
        taskRepository.save(task14);

        assertEquals(1, taskRepository.findByDoneFalseAndDeadlineBefore(LocalDate.now()).size());
    }
}
package se.lexicon.g46todoapi.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.g46todoapi.domain.entity.Person;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
//@ExtendWith(SpringExtension.class)
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void testFindPersonByTasksIsEmpty() {
        Person person = new Person();
        personRepository.save(person);

        Optional<Person> foundPerson = personRepository.findById(1L);
        foundPerson.ifPresent(found -> assertTrue(foundPerson.get().getTasks().isEmpty()));
    }
}

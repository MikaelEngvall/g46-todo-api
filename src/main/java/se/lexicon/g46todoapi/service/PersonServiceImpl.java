package se.lexicon.g46todoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.g46todoapi.converter.PersonConverter;
import se.lexicon.g46todoapi.domain.dto.PersonDTOView;
import se.lexicon.g46todoapi.domain.entity.Person;
import se.lexicon.g46todoapi.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class PersonServiceImpl implements PersonService{
    private final PersonRepository personRepository;
    public final PersonConverter personConverter;
    @Autowired
    public PersonServiceImpl(
            PersonRepository personRepository,
            PersonConverter personConverter
    ) {
        this.personRepository = personRepository;
        this.personConverter = personConverter;
    }
    @Override
    public List<PersonDTOView> getAll() {
        List<Person> persons = personRepository.findAll();
        List<PersonDTOView> personDTOList = new ArrayList<>();
        for (Person entity : persons) {
            personDTOList.add(personConverter.toPersonDTOView(entity));
        }
        return personDTOList;
    }
}

package se.lexicon.g46todoapi.converter;

import org.springframework.stereotype.Component;
import se.lexicon.g46todoapi.domain.dto.PersonDTOView;
import se.lexicon.g46todoapi.domain.dto.RoleDTOView;
import se.lexicon.g46todoapi.domain.entity.Person;
import se.lexicon.g46todoapi.domain.entity.Role;

@Component
public class PersonConverterImpl implements PersonConverter {

    @Override
    public PersonDTOView toPersonDTOView(Person entity) {
        return PersonDTOView.builder()
                .name(entity.getName())
                .build();
        //return new PersonDTOView(entity.getId(), entity.getName());
    }

    @Override
    public Person toPersonEntity(PersonDTOView dtoView) {
        return Person.builder().name(dtoView.getName()).build();
        //return new Person(dtoView.getId(), dtoView.getName());
    }

}

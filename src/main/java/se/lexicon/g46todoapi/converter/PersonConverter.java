package se.lexicon.g46todoapi.converter;


import se.lexicon.g46todoapi.domain.dto.PersonDTOView;
import se.lexicon.g46todoapi.domain.entity.Person;

public interface PersonConverter {

    PersonDTOView toPersonDTOView(Person entity);

    Person toPersonEntity(PersonDTOView dtoView);
}

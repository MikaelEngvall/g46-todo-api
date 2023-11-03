package se.lexicon.g46todoapi.service;

import se.lexicon.g46todoapi.domain.dto.PersonDTOView;

import java.util.List;

public interface PersonService {
    List<PersonDTOView> getAll();
}

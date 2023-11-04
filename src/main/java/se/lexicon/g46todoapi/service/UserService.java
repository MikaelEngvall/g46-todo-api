package se.lexicon.g46todoapi.service;

import se.lexicon.g46todoapi.domain.dto.UserDTOForm;
import se.lexicon.g46todoapi.domain.dto.UserDTOView;

import java.util.List;

public interface UserService {
    List<UserDTOView> getAll();
    UserDTOView register(UserDTOForm userDTOForm);

    UserDTOView getByEmail(String email);

    void disableByEmail(String email);

    void enableByEmail(String email);


}

package se.lexicon.g46todoapi.converter;

import org.springframework.stereotype.Component;
import se.lexicon.g46todoapi.domain.dto.UserDTOView;
import se.lexicon.g46todoapi.domain.entity.User;

@Component
public class UserConverterImpl implements UserConverter{

    @Override
    public UserDTOView toUserDTOView(User entity) {
        return new UserDTOView();
    }
}

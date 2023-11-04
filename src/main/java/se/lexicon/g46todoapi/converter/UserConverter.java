package se.lexicon.g46todoapi.converter;

import org.springframework.stereotype.Repository;
import se.lexicon.g46todoapi.domain.dto.RoleDTOView;
import se.lexicon.g46todoapi.domain.dto.UserDTOView;
import se.lexicon.g46todoapi.domain.entity.Role;
import se.lexicon.g46todoapi.domain.entity.User;

@Repository
public interface UserConverter {

    UserDTOView toUserDTOView(User entity);
    User toUserEntity(UserDTOView dtoView);
}

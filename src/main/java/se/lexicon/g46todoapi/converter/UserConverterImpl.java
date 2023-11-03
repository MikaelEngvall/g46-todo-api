package se.lexicon.g46todoapi.converter;

import org.springframework.stereotype.Component;
import se.lexicon.g46todoapi.domain.dto.UserDTOView;
import se.lexicon.g46todoapi.domain.entity.User;
import se.lexicon.g46todoapi.domain.dto.RoleDTOView;
import se.lexicon.g46todoapi.domain.entity.Role;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserConverterImpl implements UserConverter {

    private final RoleConverter roleConverter; // You will need a RoleConverter to convert roles.

    public UserConverterImpl(RoleConverter roleConverter) {
        this.roleConverter = roleConverter;
    }

    @Override
    public UserDTOView toUserDTOView(User entity) {
        if (entity == null) {
            return null;
        }

        UserDTOView userDTOView = new UserDTOView();
        userDTOView.setEmail(entity.getEmail());

        // Convert the Set of roles to Set of RoleDTOView
        userDTOView.setRoles(entity.getRoles().stream()
                .map(roleConverter::toRoleDTOView)
                .collect(Collectors.toSet()));

        return userDTOView;
    }

    @Override
    public User toUserEntity(UserDTOView dtoView) {
        if (dtoView == null) {
            return null;
        }

        User user = new User();
        user.setEmail(dtoView.getEmail());

        // Convert the Set of RoleDTOView to Set of Role
        Set<Role> roles = dtoView.getRoles().stream()
                .map(roleConverter::toRoleEntity)
                .collect(Collectors.toSet());
        user.setRoles(roles);

        return user;
    }
}

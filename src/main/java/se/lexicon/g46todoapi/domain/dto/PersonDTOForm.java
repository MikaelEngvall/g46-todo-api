package se.lexicon.g46todoapi.domain.dto;

import lombok.*;
import se.lexicon.g46todoapi.domain.entity.Task;
import se.lexicon.g46todoapi.domain.entity.User;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDTOForm {
    private String name;
    private User user;
    private List<Task> tasks = new ArrayList<>();

}

package se.lexicon.g46todoapi.domain.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTOView {
    private String title;
    private String description;
    private LocalDate deadline;
    private boolean done;

}

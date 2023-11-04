package se.lexicon.g46todoapi.domain.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTOForm {
    private String title;
    private String description;
    private LocalDate deadline;
    private boolean done;
}

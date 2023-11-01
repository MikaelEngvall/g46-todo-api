package se.lexicon.g46todoapi.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = "person")
@ToString(exclude = "person")
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Task {

    public Task(String title, String description, LocalDate deadline, boolean done, Person person) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.startDate = LocalDate.now(); // Set the start date to the current date
        this.done = done;
        this.person = person;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate deadline;
    private boolean done;

    @Column(name = "start_date")
    private LocalDate startDate; // Add a property for the start date

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}

package se.lexicon.g46todoapi.converter;

import org.springframework.stereotype.Component;
import se.lexicon.g46todoapi.domain.dto.TaskDTOView;
import se.lexicon.g46todoapi.domain.entity.Task;

@Component
public class TaskConverterImpl implements TaskConverter {

    @Override
    public TaskDTOView toTaskDTOView(Task entity) {
        return TaskDTOView.builder()
                .title(entity.getTitle())
                .description(entity.getDescription())
                .deadline(entity.getDeadline())
                .done(entity.isDone())
                .build();
    }

    @Override
    public Task toTaskEntity(TaskDTOView dtoView) {
        return Task.builder().title(dtoView.getTitle())
                .description(dtoView.getDescription())
                .deadline(dtoView.getDeadline())
                .done(dtoView.isDone())
                .build();
    }
    
}

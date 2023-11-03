package se.lexicon.g46todoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.g46todoapi.converter.TaskConverter;
import se.lexicon.g46todoapi.domain.dto.TaskDTOView;
import se.lexicon.g46todoapi.domain.entity.Task;
import se.lexicon.g46todoapi.repository.TaskRepository;


import java.util.ArrayList;
import java.util.List;
@Service
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;

    @Autowired
    public TaskServiceImpl(
            TaskRepository taskRepository,
            TaskConverter taskConverter
    ) {
        this.taskRepository = taskRepository;
        this.taskConverter = taskConverter;
    }
    @Override
    public List<TaskDTOView> getAll() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskDTOView> taskDTOList = new ArrayList<>();
        for (Task entity : tasks) {
            taskDTOList.add(taskConverter.toTaskDTOView(entity));
        }
        return taskDTOList;
    }
}

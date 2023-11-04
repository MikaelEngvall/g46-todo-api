package se.lexicon.g46todoapi.service;

import se.lexicon.g46todoapi.domain.dto.TaskDTOView;

import java.util.List;

public interface TaskService {
    List<TaskDTOView> getAll();

    List<TaskDTOView> getTitleContainsIgnoreCase(String title);
}

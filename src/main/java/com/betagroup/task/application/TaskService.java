package com.betagroup.task.application;

import com.betagroup.task.core.entities.Ack;
import com.betagroup.task.core.entities.Task;

import java.util.List;

public class TaskService implements ITaskService {

    private ITaskRepository taskRepository;

    public TaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task create(Task task) {
        task.setId(null);
        task.setCreateDate(null);
        return taskRepository.create(task);
    }

    @Override
    public Task modify(String id, Task task) {
        task.setId(id);
        task.setCreateDate(null);
        return taskRepository.modify(task);
    }

    @Override
    public Ack delete(String id) {
        return null;
    }

    @Override
    public Ack setDone(String id, boolean status) {
        return taskRepository.setDone(id, status);
    }

    @Override
    public List<Task> get() {
        return taskRepository.get();
    }

    @Override
    public Task getOne(String id) {
        return taskRepository.getOne(id);
    }
}

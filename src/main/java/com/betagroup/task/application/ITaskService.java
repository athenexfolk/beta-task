package com.betagroup.task.application;

import com.betagroup.task.core.entities.Ack;
import com.betagroup.task.core.entities.Task;

import java.util.List;

public interface ITaskService {
    public Task create(Task task);
    public Task modify(String id, Task task);
    public Ack delete(String id);
    public Ack setDone(String id, boolean status);
    public List<Task> get();
    public Task getOne(String id);
}

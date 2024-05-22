package com.betagroup.task.application;

import com.betagroup.task.core.entities.Ack;
import com.betagroup.task.core.entities.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {
    //Mock
    private ITaskRepository taskRepository;

    //Class under test
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskRepository = new StubTaskRepository();
        taskService = new TaskService(taskRepository);
    }

    @AfterEach
    void tearDown() {
        taskRepository = null;
        taskService = null;
    }

    @Test
    void create() {
        Task task = taskService.create(new Task());

        assertEquals("3", task.getId());
        assertEquals("C", task.getTitle());
        assertEquals(false, task.isDone());
        assertEquals(new Date(2001,10,10), task.getDueDate());
    }

    @Test
    void modify() {
        Task task = taskService.modify("2", new Task());

        assertEquals("1", task.getId());
        assertEquals("A", task.getTitle());
        assertEquals(false, task.isDone());
        assertEquals(new Date(2001,12,12), task.getDueDate());
    }

    @Test
    void delete() {
        Ack ack = taskService.delete("1");

        assertEquals(true, ack.isSuccess);
    }

    @Test
    void setDone() {
        Ack ack = taskService.setDone("1", true);

        assertEquals(true, ack.isSuccess);
    }

    @Test
    void get() {
        List<Task> tasks = new ArrayList<Task>();
        Task createdTask = new Task();
        createdTask.setId("1");
        createdTask.setTitle("A");
        createdTask.setDone(false);
        createdTask.setDueDate(new Date(2001,12,12));

        Task createdTask2 = new Task();
        createdTask2.setId("2");
        createdTask2.setTitle("B");
        createdTask2.setDone(false);
        createdTask2.setDueDate(new Date(2001,11,11));

        tasks.add(createdTask);
        tasks.add(createdTask2);

        List<Task> actualResult = taskService.get();

        assertEquals(tasks, actualResult);
    }

    @Test
    void getOne() {
        Task createdTask = new Task();
        createdTask.setId("1");
        createdTask.setTitle("A");
        createdTask.setDone(false);
        createdTask.setDueDate(new Date(2001,12,12));

        Task actualResult = taskService.getOne("1");

        assertEquals(createdTask, actualResult);
    }
}
class StubTaskRepository implements ITaskRepository {

    private List<Task> tasks = new ArrayList<Task>();
    public StubTaskRepository() {
        Task createdTask = new Task();
        createdTask.setId("1");
        createdTask.setTitle("A");
        createdTask.setDone(false);
        createdTask.setDueDate(new Date(2001, 12,12));

        Task createdTask2 = new Task();
        createdTask2.setId("2");
        createdTask2.setTitle("B");
        createdTask2.setDone(false);
        createdTask2.setDueDate(new Date(2001,11,11));

        tasks.add(createdTask);
        tasks.add(createdTask2);
    }

    @Override
    public Task create(Task task) {
        Task createdTask = new Task();
        createdTask.setId("3");
        createdTask.setTitle("C");
        createdTask.setDone(false);
        createdTask.setDueDate(new Date(2001,10,10));

        tasks.add(createdTask);

        return createdTask;
    }

    @Override
    public Task modify(Task task) {
        return tasks.get(0);
    }

    @Override
    public Ack delete(String id) {
        return new Ack(true);
    }

    @Override
    public Ack setDone(String id, boolean status) {
        return new Ack(true);
    }

    @Override
    public List<Task> get() {
        return tasks;
    }

    @Override
    public Task getOne(String id) {
        return tasks.get(0);
    }
}
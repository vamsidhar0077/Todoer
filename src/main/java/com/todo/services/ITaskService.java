package com.todo.services;

import com.todo.model.Task;

import java.util.List;

public interface ITaskService {
    public boolean addTask(Task task);

    public boolean deleteTask(long id);

    public List<Task> getAllTasks(String userId);
}

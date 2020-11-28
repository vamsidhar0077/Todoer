package com.todo.services;

import com.todo.model.Task;
import com.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By ReddyGadu
 * Created On 11/28/20
 * Project Name Todoer
 **/
@Service
public class TaskService implements ITaskService {
    @Autowired
    private TaskRepository taskRepository;

    public boolean addTask(Task task) {
        try {
            taskRepository.save(task);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteTask(long id) {
        try {
            taskRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Task> getAllTasks(String userId) {
        List<Task> allTasks = taskRepository.findAllByUserId(userId);
        return allTasks;
    }
}

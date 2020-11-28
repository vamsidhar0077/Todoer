package com.todo.repository;

import com.todo.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
    public List<Task> findAllByUserId(String userId);
}

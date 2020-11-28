package com.todo.controller;

import com.todo.model.Task;
import com.todo.services.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created By ReddyGadu
 * Created On 11/28/20
 * Project Name Todoer
 **/
@Controller
@RequestMapping("todoer/task/")
public class TaskController {

    @Autowired
    private ITaskService iTaskService;


    @RequestMapping(value = "add", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity addTask(@RequestBody Task task) {
        ResponseEntity result;
        boolean added = iTaskService.addTask(task);
        if (added) {
            result = ResponseEntity.ok(task.getTitle() + " added successfully");
        } else {
            result = ResponseEntity.badRequest().body("Unable to add " + task.getTitle());
        }
        return result;
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public ResponseEntity deleteTask(@RequestParam(name = "id") long id) {
        boolean deleted = iTaskService.deleteTask(id);
        if (deleted) {
            return ResponseEntity.ok("Task deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Unable to delete task");
        }
    }

    @RequestMapping(value = "retrieveTasks", method = RequestMethod.GET)
    public ResponseEntity getAllTasks(@RequestParam(name = "userid") String userId) {
        List<Task> allTasks = iTaskService.getAllTasks(userId);
        return ResponseEntity.ok().body(allTasks);
    }


}



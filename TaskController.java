package com.kaiburr.taskmanager.controller;

import com.kaiburr.taskmanager.model.Task;
import com.kaiburr.taskmanager.model.TaskExecution;
import com.kaiburr.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    
    @Autowired
    private TaskService taskService;
    
    @GetMapping
    public ResponseEntity<?> getTasks(@RequestParam(required = false) String id) {
        if (id != null) {
            Optional<Task> task = taskService.getTaskById(id);
            return task.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
        }
        return ResponseEntity.ok(taskService.getAllTasks());
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Task>> findTasksByName(@RequestParam String name) {
        return ResponseEntity.ok(taskService.findTasksByName(name));
    }
    
    @PutMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }
    
    @DeleteMapping
    public ResponseEntity<String> deleteTask(@RequestParam String id) {
        if (taskService.deleteTask(id)) {
            return ResponseEntity.ok("Task deleted");
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/execute")
    public ResponseEntity<?> executeTask(@RequestParam String id) {
        Optional<TaskExecution> execution = taskService.executeTask(id);
        return execution.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }
}
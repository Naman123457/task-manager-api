package com.kaiburr.taskmanager.service;

import com.kaiburr.taskmanager.model.Task;
import com.kaiburr.taskmanager.model.TaskExecution;
import com.kaiburr.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;
    
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    
    public Optional<Task> getTaskById(String id) {
        return taskRepository.findById(id);
    }
    
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
    
    public boolean deleteTask(String id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public List<Task> findTasksByName(String name) {
        return taskRepository.findByNameContaining(name);
    }
    
    public Optional<TaskExecution> executeTask(String taskId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            TaskExecution execution = executeCommand(task.getCommand());
            task.addTaskExecution(execution);
            taskRepository.save(task);
            return Optional.of(execution);
        }
        
        return Optional.empty();
    }
    
    private TaskExecution executeCommand(String command) {
        try {
            Date startTime = new Date();
            
            ProcessBuilder processBuilder = new ProcessBuilder();
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                processBuilder.command("cmd.exe", "/c", command);
            } else {
                processBuilder.command("sh", "-c", command);
            }
            
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
            
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            
            int exitVal = process.waitFor();
            Date endTime = new Date();
            
            if (exitVal == 0) {
                return new TaskExecution(startTime, endTime, output.toString());
            } else {
                return new TaskExecution(startTime, endTime, 
                    "Command failed: " + exitVal);
            }
            
        } catch (Exception e) {
            Date endTime = new Date();
            Date startTime = new Date(endTime.getTime() - 1000);
            return new TaskExecution(startTime, endTime, 
                "Error: " + e.getMessage());
        }
    }
}
package main.controllers;

import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RestController
public class TaskController {

    private TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks/") //
    public ResponseEntity list() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        List<Task> taskList = new ArrayList<>();
        for (Task task : taskIterable) {
            taskList.add(task);
        }
        if (taskList.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(taskList, HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalTask.get(), HttpStatus.OK);
    }


    @DeleteMapping("/tasks/")
    public ResponseEntity deleteAllTasks() {
        taskRepository.deleteAll();
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity deleteTask(@PathVariable("id") int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            taskRepository.deleteById(id);
            return new ResponseEntity(id + " task is delete", HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/tasks/")
    public ResponseEntity addTask(Task task) {
        Task newTask = taskRepository.save(task);
        return new ResponseEntity(newTask.getId(), HttpStatus.OK);
    }

    @PostMapping("/tasks/{id}")
    public ResponseEntity addById(@PathVariable("id") int id) {
        return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
    }


    @PutMapping("/tasks/")
    public ResponseEntity updateTask(Task newTask) {
        Optional<Task> optionalTask = taskRepository.findById(newTask.getId());
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        taskRepository.save(newTask);
        return new ResponseEntity(newTask, HttpStatus.OK);
    }

}

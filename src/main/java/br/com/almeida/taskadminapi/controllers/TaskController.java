package br.com.almeida.taskadminapi.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.almeida.taskadminapi.dtos.TaskDto;
import br.com.almeida.taskadminapi.models.CategoryModel;
import br.com.almeida.taskadminapi.models.TaskModel;
import br.com.almeida.taskadminapi.repositories.CategoryRepository;
import br.com.almeida.taskadminapi.repositories.TaskRepository;
import br.com.almeida.taskadminapi.repositories.TeamRepository;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TeamRepository teamRepository;

    @GetMapping("/task")
    public ResponseEntity<List<TaskModel>> getAllTasks(@RequestParam(required = false) String title) {
        try {
            List<TaskModel> tasks = new ArrayList<TaskModel>();
            if (title == null) {
                taskRepository.findAll().forEach(tasks::add);
            } else {
                taskRepository.findByTitle(title).forEach(tasks::add);
            }
            if (tasks.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            // for (TaskModel taskModel : tasks) {

            // System.out.println(taskModel.getId());
            // }
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<TaskModel> getTaskById(@PathVariable("id") UUID id) {
        Optional<TaskModel> taskData = taskRepository.findById(id);
        if (taskData.isPresent()) {
            return new ResponseEntity<>(taskData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/task")
    public ResponseEntity<TaskModel> createTask(@RequestBody @Valid TaskDto task) {
        try {
            CategoryModel _category = categoryRepository.findById(task.getCategory()).get();

            // List<TeamModel> _teams = new ArrayList<TeamModel>();

            // task.getTeams().forEach((teamId) ->
            // _teams.add(teamRepository.findById(teamId).get()));

            TaskModel _task = taskRepository
                    .save(new TaskModel(
                            task.getCreatorId(),
                            task.getTitle(),
                            task.getDescription(),
                            task.getCreationDate(),
                            task.getStartDate(),
                            task.getFinishDate(),
                            task.getAlertDate(),
                            task.getPriority(),
                            task.getStatus(),
                            _category
                    // _teams
                    ));
            return new ResponseEntity<>(_task, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<TaskModel> updateTask(@PathVariable("id") UUID id,
            @RequestBody TaskModel task) {
        Optional<TaskModel> taskData = taskRepository.findById(id);

        if (taskData.isPresent()) {
            TaskModel _task = taskData.get();
            _task.setTitle(task.getTitle());
            _task.setDescription(task.getDescription());
            _task.setCreationDate(task.getCreationDate());
            _task.setStartDate(task.getStartDate());
            _task.setFinishDate(task.getFinishDate());
            _task.setAlertDate(task.getAlertDate());
            _task.setPriority(task.getPriority());
            _task.setStatus(task.getStatus());
            _task.setCategory(task.getCategory());
            _task.setComentaries(task.getComentaries());
            return new ResponseEntity<>(taskRepository.save(_task), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("task/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") UUID id) {
        try {
            taskRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/task")
    public ResponseEntity<TaskModel> deleteAlltasks() {
        try {
            taskRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

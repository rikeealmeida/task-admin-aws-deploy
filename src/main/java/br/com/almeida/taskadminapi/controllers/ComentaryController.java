package br.com.almeida.taskadminapi.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.almeida.taskadminapi.dtos.ComentaryDto;
import br.com.almeida.taskadminapi.models.ComentaryModel;
import br.com.almeida.taskadminapi.models.TaskModel;
import br.com.almeida.taskadminapi.models.UserModel;
import br.com.almeida.taskadminapi.repositories.ComentaryRepository;
import br.com.almeida.taskadminapi.repositories.TaskRepository;
import br.com.almeida.taskadminapi.repositories.UserRepository;

@RestController
@RequestMapping("/api/task")
public class ComentaryController {

    @Autowired
    ComentaryRepository comentaryRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/comment")
    public ResponseEntity<List<ComentaryModel>> getAllComments() {
        try {
            List<ComentaryModel> comentaries = new ArrayList<ComentaryModel>();

            comentaryRepository.findAll().forEach(comentaries::add);

            if (comentaries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(comentaries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/comment")
    public ResponseEntity<ComentaryModel> createTeam(@RequestBody @Valid ComentaryDto comentary) {
        try {
            TaskModel _task = taskRepository.findById(comentary.getTaskId())
                    .get();
            UserModel _user = userRepository.findById(comentary.getUserId()).get();

            ComentaryModel _comentary = comentaryRepository
                    .save(new ComentaryModel(
                            _user,
                            _task,
                            comentary.getDate(),
                            comentary.getContent()));

            return new ResponseEntity<>(_comentary, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

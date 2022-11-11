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

import br.com.almeida.taskadminapi.dtos.TeamDto;
import br.com.almeida.taskadminapi.models.TeamMemberModel;
import br.com.almeida.taskadminapi.models.TeamModel;
import br.com.almeida.taskadminapi.repositories.TeamMemberRepository;
import br.com.almeida.taskadminapi.repositories.TeamRepository;
import br.com.almeida.taskadminapi.repositories.UserRepository;

@RestController
@RequestMapping("/api")
public class TeamController {
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    TeamMemberRepository teamMemberRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/team")
    public ResponseEntity<List<TeamModel>> getAllTeams(@RequestParam(required = false) String name) {
        try {
            List<TeamModel> teams = new ArrayList<TeamModel>();
            if (name == null) {
                teamRepository.findAll().forEach(teams::add);
            } else {
                teamRepository.findByName(name).forEach(teams::add);
            }
            if (teams.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(teams, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/team/{id}")
    public ResponseEntity<TeamModel> getTeamById(@PathVariable("id") UUID id) {
        Optional<TeamModel> teamData = teamRepository.findById(id);
        if (teamData.isPresent()) {
            return new ResponseEntity<>(teamData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/team")
    public ResponseEntity<TeamModel> createTeam(@RequestBody @Valid TeamDto team) {
        try {
            TeamModel _team = teamRepository
                    .save(new TeamModel(
                            team.getName()));

            team.getMembers().forEach((memberId) -> teamMemberRepository.save(
                    new TeamMemberModel(
                            0, userRepository.findById(memberId).get(), _team)));

            return new ResponseEntity<>(_team, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/team/{id}")
    public ResponseEntity<TeamModel> updateTeam(@PathVariable("id") UUID id,
            @RequestBody TeamModel team) {
        Optional<TeamModel> teamData = teamRepository.findById(id);

        if (teamData.isPresent()) {
            TeamModel _team = teamData.get();
            _team.setName(team.getName());
            return new ResponseEntity<>(teamRepository.save(_team), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("team/{id}")
    public ResponseEntity<HttpStatus> deleteTeam(@PathVariable("id") UUID id) {
        try {
            teamRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/team")
    public ResponseEntity<TeamModel> deleteAllTeams() {
        try {
            teamRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/team/member/{id}")
    public ResponseEntity<TeamMemberModel> updateMember(@PathVariable("id") UUID id,
            @RequestBody TeamMemberModel member) {
        Optional<TeamMemberModel> teamMemberData = teamMemberRepository.findById(id);

        if (teamMemberData.isPresent()) {
            TeamMemberModel _member = teamMemberData.get();
            _member.setRole_id(member.getRole_id());
            return new ResponseEntity<>(teamMemberRepository.save(_member), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

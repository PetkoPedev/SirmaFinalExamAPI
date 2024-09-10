package com.finalExam.api.FinalExamAPI.controller;

import com.finalExam.api.FinalExamAPI.model.Team;
import com.finalExam.api.FinalExamAPI.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public List<Team> getAllTeams(){
        return teamService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id){
        try{
            Team team = teamService.findById(id);
            return ResponseEntity.ok(team);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Team> createTeam(@ModelAttribute Team team){
        Team newTeam = teamService.save(team);
        return ResponseEntity.ok(newTeam);
    }

    @PutMapping("/{id}")
    ResponseEntity<Team> updateTeam(@PathVariable Long id, @ModelAttribute Team team){
        try{
            Team existingTeam = teamService.save(team);
            return ResponseEntity.ok(existingTeam);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id){
        try {
            teamService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}

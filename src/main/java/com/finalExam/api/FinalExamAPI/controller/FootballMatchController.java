package com.finalExam.api.FinalExamAPI.controller;

import com.finalExam.api.FinalExamAPI.model.FootballMatch;
import com.finalExam.api.FinalExamAPI.service.FootballMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class FootballMatchController {

    @Autowired
    private FootballMatchService footballMatchService;

    @GetMapping
    public List<FootballMatch> getAllMatches(){
        return footballMatchService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FootballMatch> getMatchById(@PathVariable Long id){
        try{
            FootballMatch match = footballMatchService.findById(id);
            return ResponseEntity.ok(match);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<FootballMatch> createMatch(@ModelAttribute FootballMatch match){
        FootballMatch newMatch = footballMatchService.save(match);
        return ResponseEntity.ok(newMatch);
    }

    @PutMapping("/{id}")
    ResponseEntity<FootballMatch> updateMatch(@PathVariable Long id, @ModelAttribute FootballMatch match){
        try{
            FootballMatch existingMatch = footballMatchService.save(match);
            return ResponseEntity.ok(existingMatch);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id){
        try {
            footballMatchService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}

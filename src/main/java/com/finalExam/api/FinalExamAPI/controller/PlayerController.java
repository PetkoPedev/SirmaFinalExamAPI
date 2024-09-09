package com.finalExam.api.FinalExamAPI.controller;

import com.finalExam.api.FinalExamAPI.model.Player;
import com.finalExam.api.FinalExamAPI.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public List<Player> getAllPlayers(){
        return playerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id){
        try{
            Player player = playerService.findById(id);
            return ResponseEntity.ok(player);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@ModelAttribute Player player){
        Player newPlayer = playerService.save(player);
        return ResponseEntity.ok(newPlayer);
    }

    @PutMapping("/{id}")
    ResponseEntity<Player> updatePlayer(@PathVariable Long id, @ModelAttribute Player player){
        try{
            Player existingPlayer = playerService.save(player);
            return ResponseEntity.ok(existingPlayer);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id){
        try {
            playerService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}

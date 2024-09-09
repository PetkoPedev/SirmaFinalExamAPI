package com.finalExam.api.FinalExamAPI.controller;

import com.finalExam.api.FinalExamAPI.model.Player;
import com.finalExam.api.FinalExamAPI.service.PlayerPairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/player-pair")
public class PlayerPairController {
    @Autowired
    private PlayerPairService playerPairService;

    @GetMapping("/longest-playing-pair")
    public ResponseEntity<Map<List<Player>, Integer>> getLongestPlayingPair() {
        Map<List<Player>, Integer> result = playerPairService.findPlayerPairsWithLongestTime();
        return ResponseEntity.ok(result);
    }
}

package com.finalExam.api.FinalExamAPI.service;

import com.finalExam.api.FinalExamAPI.model.Player;
import com.finalExam.api.FinalExamAPI.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService extends CsvReaderService{

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> findAll(){
        return playerRepository.findAll();
    }

    public Player findById(Long id){
        return playerRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id: " + id));
    }

    public Player save(Player player){
        return playerRepository.save(player);
    }

    public void deleteById(Long id){
        if(playerRepository.existsById(id)){
            playerRepository.deleteById(id);
        }
        else{
            throw new RuntimeException("User not found with id: " + id);
        }
    }
}

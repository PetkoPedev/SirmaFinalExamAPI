package com.finalExam.api.FinalExamAPI.service;

import com.finalExam.api.FinalExamAPI.model.Team;
import com.finalExam.api.FinalExamAPI.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService extends CsvReaderService{

    @Autowired
    private TeamRepository teamRepository;

    public List<Team> findAll(){
        return teamRepository.findAll();
    }

    public Team findById(Long id){
        return teamRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id: " + id));
    }

    public Team save(Team team){
        return teamRepository.save(team);
    }

    public void deleteById(Long id){
        if(teamRepository.existsById(id)){
            teamRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Invalid team with id: " + id);
        }
    }
}

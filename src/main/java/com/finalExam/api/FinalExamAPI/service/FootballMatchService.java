package com.finalExam.api.FinalExamAPI.service;

import com.finalExam.api.FinalExamAPI.model.FootballMatch;
import com.finalExam.api.FinalExamAPI.repository.FootballMatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FootballMatchService extends CsvReaderService{

    @Autowired
    private FootballMatchRepository footballMatchRepository;

    public List<FootballMatch> findAll(){
        return footballMatchRepository.findAll();
    }

    public FootballMatch findById(Long id){
        return footballMatchRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id: " + id));
    }

    public FootballMatch save(FootballMatch match){
        return footballMatchRepository.save(match);
    }

    public void deleteById(Long id){
        if(footballMatchRepository.existsById(id)){
            footballMatchRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Invalid match with id: " + id);
        }
    }
}

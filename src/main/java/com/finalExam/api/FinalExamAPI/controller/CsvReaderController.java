package com.finalExam.api.FinalExamAPI.controller;

import com.finalExam.api.FinalExamAPI.service.CsvReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class CsvReaderController {

    @Autowired
    private CsvReaderService csvReaderService;

    @PostMapping("/players")
    public ResponseEntity<String> uploadPlayersFromCSV(@RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
        }

        try{
            csvReaderService.readPlayersFromScvFile(file);
            return new ResponseEntity<>("Players CSV file successfully processed.", HttpStatus.OK);
        } catch (IOException e){
            return new ResponseEntity<>("Failed to process CSV file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/teams")
    public ResponseEntity<String> uploadTeamsFromCSV(@RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
        }

        try{
            csvReaderService.readTeamsFromScvFile(file);
            return new ResponseEntity<>("Teams CSV file successfully processed.", HttpStatus.OK);
        } catch (IOException e){
            return new ResponseEntity<>("Failed to process CSV file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/matches")
    public ResponseEntity<String> uploadMatchesFromCSV(@RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
        }

        try{
            csvReaderService.readMatchesFromCsv(file);
            return new ResponseEntity<>("Matches CSV file successfully processed.", HttpStatus.OK);
        } catch (IOException e){
            return new ResponseEntity<>("Failed to process CSV file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/match-records")
    public ResponseEntity<String> uploadMatchRecordsFromCSV(@RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
        }

        try{
            csvReaderService.readMatchRecordsFromCsv(file);
            return new ResponseEntity<>("Records CSV file successfully processed.", HttpStatus.OK);
        } catch (IOException e){
            return new ResponseEntity<>("Failed to process CSV file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

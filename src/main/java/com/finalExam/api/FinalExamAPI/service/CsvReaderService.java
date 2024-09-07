package com.finalExam.api.FinalExamAPI.service;

import com.finalExam.api.FinalExamAPI.model.FootballMatch;
import com.finalExam.api.FinalExamAPI.model.MatchRecord;
import com.finalExam.api.FinalExamAPI.model.Player;
import com.finalExam.api.FinalExamAPI.model.Team;
import com.finalExam.api.FinalExamAPI.repository.FootballMatchRepository;
import com.finalExam.api.FinalExamAPI.repository.MatchRecordRepository;
import com.finalExam.api.FinalExamAPI.repository.PlayerRepository;
import com.finalExam.api.FinalExamAPI.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class CsvReaderService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private FootballMatchRepository footballMatchRepository;

    @Autowired
    private MatchRecordRepository matchRecordRepository;

    public void readPlayersFromScvFile(String filepath) throws IOException {
        try(BufferedReader br = Files.newBufferedReader(Paths.get(filepath))){
            String line;

            while((line = br.readLine()) != null){
                if(line.startsWith("ID")){
                    continue;
                }
                String[] values = line.split(",");
                Player player = new Player();
                player.setId(Long.parseLong(values[0].trim()));
                player.setTeamNumber(Integer.parseInt(values[1].trim()));
                player.setPosition(values[2].trim());
                player.setFullName(values[3].trim());
                player.setTeam(teamRepository.findById(Long.parseLong(values[4].trim())).orElse(null));
                playerRepository.save(player);
            }
        }
    }

    public void readTeamsFromScvFile(String filepath) throws IOException {
        try(BufferedReader br = Files.newBufferedReader(Paths.get(filepath))){
            String line;

            while((line = br.readLine()) != null){
                if(line.startsWith("ID")){
                    continue;
                }
                String[] values = line.split(",");
                Team team = new Team();
                team.setId(Long.parseLong(values[0].trim()));
                team.setName(values[1].trim());
                team.setManagerFullName(values[2].trim());
                team.setGroup(values[3].trim());
                teamRepository.save(team);
            }
        }
    }

    public void readMatchesFromCsv(String filePath) throws IOException {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("ID")) {
                    continue;
                }
                String[] values = line.split(",");
                FootballMatch match = new FootballMatch();
                match.setId(Long.parseLong(values[0].trim()));
                match.setTeamA(teamRepository.findById(Long.parseLong(values[1].trim())).orElse(null));
                match.setTeamB(teamRepository.findById(Long.parseLong(values[2].trim())).orElse(null));
                match.setDate(LocalDate.parse(values[3].trim(), DateTimeFormatter.ofPattern("M/d/yyyy")));
                match.setScore(values[4].trim());
                footballMatchRepository.save(match);
            }
        }
    }

    public void readMatchRecordsFromCsv(String filePath) throws IOException {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("ID")) {
                    continue;
                }
                String[] values = line.split(",");
                MatchRecord record = new MatchRecord();
                record.setId(Long.parseLong(values[0].trim()));
                record.setPlayer(playerRepository.findById(Long.parseLong(values[1].trim())).orElse(null));
                record.setMatch(footballMatchRepository.findById(Long.parseLong(values[2].trim())).orElse(null));
                record.setStartMinute(values[3].trim().equals("NULL") ? 0 : Integer.parseInt(values[3].trim()));
                record.setEndMinute(values[4].trim().equals("NULL") ? 90 : Integer.parseInt(values[4].trim()));
                matchRecordRepository.save(record);
            }
        }
    }

}

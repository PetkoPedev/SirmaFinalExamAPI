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
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public void readPlayersFromScvFile(MultipartFile file) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))){
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

    public void readTeamsFromScvFile(MultipartFile file) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))){
            String line;

            while((line = br.readLine()) != null){
                System.out.println(line);
                if(line.startsWith("ID")){
                    continue;
                }

                String[] values = line.split(",");

                Team team = new Team();
                team.setId(Long.parseLong(values[0].trim()));
                team.setName(values[1].trim());
                team.setManagerFullName(values[2].trim());
                team.setGroup(values[3].trim());

                try{
                    teamRepository.save(team);
                }
                catch(Exception e){
                    System.err.println("Error saving team: " + e.getMessage());
                }

            }
        }
    }

    public void readMatchesFromCsv(MultipartFile file) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("ID")) {
                    continue;
                }
                String[] values = line.split(",");
                FootballMatch match = new FootballMatch();
                match.setId(Long.parseLong(values[0].trim()));

                Team teamA = teamRepository.findById(Long.parseLong(values[1].trim())).orElse(null);
                Team teamB = teamRepository.findById(Long.parseLong(values[2].trim())).orElse(null);

                if(teamA == null || teamB == null){
                    System.err.println("One or both teams are missing for a match with ID: " + values[0]);
                    continue;
                }

                match.setTeamA(teamA);
                match.setTeamB(teamB);

                match.setDate(LocalDate.parse(values[3].trim(), DateTimeFormatter.ofPattern("M/d/yyyy")));
                match.setScore(values[4].trim());
                footballMatchRepository.save(match);
            }
        }
    }

    public void readMatchRecordsFromCsv(MultipartFile file) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
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

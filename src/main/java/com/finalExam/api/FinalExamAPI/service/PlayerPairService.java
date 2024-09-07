package com.finalExam.api.FinalExamAPI.service;

import com.finalExam.api.FinalExamAPI.model.MatchRecord;
import com.finalExam.api.FinalExamAPI.model.Player;
import com.finalExam.api.FinalExamAPI.repository.MatchRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayerPairService {

    @Autowired
    private MatchRecordRepository matchRecordRepository;

    public Map<List<Player>, Integer> findPlayerPairsWithLongestTime() {
        List<MatchRecord> matchRecords = matchRecordRepository.findAll();
        Map<List<Player>, Integer> playerPairDurations = new HashMap<>();

        for (MatchRecord record1 : matchRecords) {
            for (MatchRecord record2 : matchRecords) {
                if (record1.getMatch().equals(record2.getMatch()) &&
                        !record1.getPlayer().equals(record2.getPlayer())) {

                    int commonMinutes = Math.min(
                            record1.getEndMinute() != null ? record1.getEndMinute() : 90,
                            record2.getEndMinute() != null ? record2.getEndMinute() : 90) -
                            Math.max(record1.getStartMinute(), record2.getStartMinute());

                    if (commonMinutes > 0) {
                        List<Player> pair = Arrays.asList(record1.getPlayer(), record2.getPlayer());
                        pair.sort(Comparator.comparingLong(Player::getId));

                        playerPairDurations.put(pair,
                                playerPairDurations.getOrDefault(pair, 0) + commonMinutes);
                    }
                }
            }
        }

        Map.Entry<List<Player>, Integer> longestPair = playerPairDurations.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new RuntimeException("No player pairs found"));

        Map<List<Player>, Integer> result = new HashMap<>();
        result.put(longestPair.getKey(), longestPair.getValue());

        return result;
    }
}

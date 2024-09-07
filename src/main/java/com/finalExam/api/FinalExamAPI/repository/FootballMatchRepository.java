package com.finalExam.api.FinalExamAPI.repository;

import com.finalExam.api.FinalExamAPI.model.FootballMatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FootballMatchRepository extends JpaRepository<FootballMatch, Long> {
}

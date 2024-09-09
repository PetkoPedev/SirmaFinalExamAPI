package com.finalExam.api.FinalExamAPI.model;

import jakarta.persistence.*;

@Entity
@Table(name = "records")
public class MatchRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PlayerID")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "MatchID")
    private FootballMatch match;

    @Column(name = "fromMinutes")
    private Integer startMinute;

    @Column(name = "toMinutes", nullable = true)
    private Integer endMinute;

    public MatchRecord() {
    }

    public MatchRecord(Long id, FootballMatch match, Player player, Integer startMinute, Integer endMinute) {
        this.id = id;
        this.match = match;
        this.player = player;
        this.startMinute = startMinute;
        this.endMinute = endMinute;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FootballMatch getMatch() {
        return match;
    }

    public void setMatch(FootballMatch match) {
        this.match = match;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Integer getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(Integer startMinute) {
        this.startMinute = startMinute;
    }

    public Integer getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(Integer endMinute) {
        this.endMinute = endMinute;
    }
}

package com.finalExam.api.FinalExamAPI.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "ManagerFullName")
    private String managerFullName;

    @Column(name = "team_group")
    private String group;

    @OneToMany(mappedBy = "team")
    private List<Player> players;

    public Team() {
    }

    public Team(Long id, String name, String managerFullName, String group) {
        this.id = id;
        this.name = name;
        this.managerFullName = managerFullName;
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerFullName() {
        return managerFullName;
    }

    public void setManagerFullName(String managerFullName) {
        this.managerFullName = managerFullName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}

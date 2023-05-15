package com.spotify.dbMapping;

import jakarta.persistence.*;

@Entity
@Table(name="table1")  //map the table with this class
public class Table1Entity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "personal_code")
    private Long personalCode;

    public Table1Entity() {
    }
    public Table1Entity(long id, String fullName, long personalCode) {
        super();
        this.id = id;
        this.fullName = fullName;
        this.personalCode = personalCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFull_name() {
        return fullName;
    }

    public void setFull_name(String full_name) {
        this.fullName = full_name;
    }

    public Long getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(Long personalCode) {
        this.personalCode = personalCode;
    }




}

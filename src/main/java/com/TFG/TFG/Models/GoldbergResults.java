package com.TFG.TFG.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "goldbergresults", schema = "burnout")
public class GoldbergResults {

    public GoldbergResults(){}

    @Id
    @Column(name = "idgoldbergresult")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name="data")
    @Temporal(TemporalType.DATE)
    private Date date;

    public boolean isMalestarPsicologic() {
        return MalestarPsicologic;
    }

    public void setMalestarPsicologic(boolean malestarPsicologic) {
        MalestarPsicologic = malestarPsicologic;
    }

    @Column(name = "malestarpsicologic")
    private boolean MalestarPsicologic;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "iduser")
    @JsonBackReference
    private User user_id;

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }
}

package com.TFG.TFG.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "maslachresults", schema = "burnout")
public class MaslachResults {
    public MaslachResults(){}

    public Long getId() {
        return id;
    }

    @Id
    @Column(name = "idmaslachresult")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name="data")
    @Temporal(TemporalType.DATE)
    private Date date;


    public int getEmotionalExhaust() {
        return EmotionalExhaust;
    }

    public void setEmotionalExhaust(int emotionalExhaust) {
        EmotionalExhaust = emotionalExhaust;
    }

    @Column(name="emotionalexhaust")
    private int EmotionalExhaust;


    public int getDespersonalization() {
        return Despersonalization;
    }

    public void setDespersonalization(int despersonalization) {
        Despersonalization = despersonalization;
    }

    @Column(name="depersonalization")
    private int Despersonalization;


    public int getPersonalFullfillment() {
        return PersonalFullfillment;
    }

    public void setPersonalFullfillment(int personalFullfillment) {
        PersonalFullfillment = personalFullfillment;
    }

    @Column(name="personalfulfilment")
    private int PersonalFullfillment;

    public String getEmotionalExhaustValoration() {
        return EmotionalExhaustValoration;
    }

    public void setEmotionalExhaustValoration(String emotionalExhaustValoration) {
        EmotionalExhaustValoration = emotionalExhaustValoration;
    }

    @Column(name="emotionalexhaustvaloration")
    private String EmotionalExhaustValoration;

    public String getDespersonalizationValoration() {
        return DespersonalizationValoration;
    }

    public void setDespersonalizationValoration(String despersonalizationValoration) {
        DespersonalizationValoration = despersonalizationValoration;
    }

    @Column(name="depersonalizationvaloration")
    private String DespersonalizationValoration;


    public String getPersonalFullfillmentValoration() {
        return PersonalFullfillmentValoration;
    }

    public void setPersonalFullfillmentValoration(String personalFullfillmentValoration) {
        PersonalFullfillmentValoration = personalFullfillmentValoration;
    }

    @Column(name="personalfulfilmentvaloration")
    private String PersonalFullfillmentValoration;

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

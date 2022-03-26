package com.TFG.TFG.Models;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "user", schema = "burnout")
public class User {


    public long getId() {
        return id;
    }

    @Id
    @Column(name = "iduser")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    @Column(name = "username")
    private String mUsername;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    private String Password;

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    @Email
    @Column(name = "email")
    private String mEmail;


    @Column(name = "cognom1")
    private String Cognom1;

    public String getCognom1() {
        return Cognom1;
    }

    public void setCognom1(String cognom1) {
        Cognom1 = cognom1;
    }

    @Column(name = "cognom2")
    private String Cognom2;

    public String getCognom2() {
        return Cognom2;
    }

    public void setCognom2(String cognom2) {
        Cognom2 = cognom2;
    }

    @Column(name = "nom")
    private String Nom;

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    @Column(name="edat")
    private int Edat;

    public int getEdat(){
        return Edat;
    }
    public void setEdat(int edat){
        Edat = edat;
    }

    @Column(name="professio")
    private String Professio;

    public String getProfessio(){
        return Professio;
    }
    public void setProfessio(String professio){
        Professio = professio;
    }

    public String getGenere() {
        return Genere;
    }

    public void setGenere(String genere) {
        Genere = genere;
    }

    @Column(name = "gender")
    private String Genere;



    @Column(name = "anysexp")
    private int AnysExp;

    public int getAnysExp(){
        return AnysExp;
    }
    public void setAnysExp(int anysExp){
        AnysExp=anysExp;
    }

    @OneToMany(mappedBy = "user_id", cascade=CascadeType.PERSIST)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIgnore
    private List<HAlimentacio> hAlimentacio = new ArrayList<>();

    public List<HAlimentacio> gethAlimentacio() {
        return hAlimentacio;
    }

    public void sethAlimentacio(List<HAlimentacio> hAlimentacio) {
        hAlimentacio = hAlimentacio;
    }

    @OneToMany(mappedBy = "user_id", cascade=CascadeType.PERSIST)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIgnore
    private List<HActivitatFisica> hActivitatFisica = new ArrayList<>();

    public List<HActivitatFisica> gethActivitatFisica() {
        return hActivitatFisica;
    }

    public void sethActivitatFisica(List<HActivitatFisica> hActivitatFisica) {
        hActivitatFisica = hActivitatFisica;
    }

    @OneToMany(mappedBy = "user_id", cascade=CascadeType.PERSIST)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIgnore
    private List<HToxics> hToxics = new ArrayList<>();

    public List<HToxics> gethToxics() {
        return hToxics;
    }

    public void sethToxics(List<HToxics> hToxics) {
        hToxics = hToxics;
    }


    public List<GoldbergResults> getGoldbergStats() {
        return GoldbergStats;
    }

    public void setGoldbergStats(List<GoldbergResults> goldbergStats) {
        GoldbergStats = goldbergStats;
    }

    @OneToMany(mappedBy = "user_id", cascade=CascadeType.PERSIST)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonManagedReference
    private List<GoldbergResults> GoldbergStats = new ArrayList<>();


    public List<MaslachResults> getBurnOutStats() {
        return BurnOutStats;
    }

    public void setBurnOutStats(List<MaslachResults> burnOutStats) {
        BurnOutStats = burnOutStats;
    }

    @OneToMany(mappedBy = "user_id", cascade=CascadeType.PERSIST)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonManagedReference
    private List<MaslachResults> BurnOutStats = new ArrayList<>();
    public User(){ }
}

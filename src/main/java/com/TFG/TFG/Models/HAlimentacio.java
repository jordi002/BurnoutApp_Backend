package com.TFG.TFG.Models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;

@Entity
@Table(name = "alimentacio", schema = "burnout")
public class HAlimentacio {

    public HAlimentacio(){}

    @Id
    @Column(name = "idpreguntaalimentacio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "iduser")
    private User user_id;

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    @Column(name = "pregunta")
    private String Pregunta;

    public String getPregunta() {
        return Pregunta;
    }
    public void setPregunta(String pregunta){
        Pregunta=pregunta;
    }

    @Column(name = "resposta")
    private String Resposta;

    public String getResposta() {
        return Resposta;
    }
    public void setResposta(String respuesta){
        Resposta=respuesta;
    }
}
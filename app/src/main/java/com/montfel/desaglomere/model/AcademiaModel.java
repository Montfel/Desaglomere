package com.montfel.desaglomere.model;

import java.io.Serializable;

public class AcademiaModel implements Serializable {
    private Long id;
    private String horario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}

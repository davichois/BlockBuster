package com.alex.blockbuster.model;

public class Tipo {
    private int idTipo;
    private String noTipo;

    public Tipo(int idTipo, String niTipo) {
        this.idTipo = idTipo;
        this.noTipo = noTipo;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNiTipo() {
        return noTipo;
    }

    public void setNiTipo(String noTipo) {
        this.noTipo = noTipo;
    }
}

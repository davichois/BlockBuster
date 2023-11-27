package com.alex.blockbuster.model;

public class Tipo {
    private int idTipo;
    private String noTipo;

    public Tipo(int idTipo, String noTipo) {
        this.idTipo = idTipo;
        this.noTipo = noTipo;
    }

    public Tipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNoTipo() {
        return noTipo;
    }

    public void setNoTipo(String noTipo) {
        this.noTipo = noTipo;
    }
}

package com.alex.blockbuster.model;

public class Etiqueta {
    //atributos
    private int idEtiqueta;
    private String noEtiqueta;
    private int isActivo;

    //constructor

    public Etiqueta(int idEtiqueta, String noEtiqueta, int isActivo) {
        this.idEtiqueta = idEtiqueta;
        this.noEtiqueta = noEtiqueta;
        this.isActivo = isActivo;
    }

    public Etiqueta(int idEtiqueta) {
        this.idEtiqueta = idEtiqueta;
    }

    //metodos
    public int getIdEtiqueta() {
        return idEtiqueta;
    }

    public void setIdEtiqueta(int idEtiqueta) {
        this.idEtiqueta = idEtiqueta;
    }

    public String getNoEtiqueta() {
        return noEtiqueta;
    }

    public void setNoEtiqueta(String noEtiqueta) {
        this.noEtiqueta = noEtiqueta;
    }

    public int getIsActivo() {
        return isActivo;
    }

    public void setIsActivo(int isActivo) {
        this.isActivo = isActivo;
    }
}

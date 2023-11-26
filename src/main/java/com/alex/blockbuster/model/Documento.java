package com.alex.blockbuster.model;

public class Documento {
    //atributos
    private int idDocumento;
    private String noDocumento;
    private int caDocumento;
    private String coDocumento;
    private String auDocumento;
    private String edDocumento;
    private String apDocumento;
    private Etiqueta etiqueta;
    private Tipo tipo;

    //constructor
    public Documento(int idDocumento, String noDocumento, int caDocumento, String coDocumento, String auDocumento, String edDocumento, String apDocumento, Etiqueta etiqueta, Tipo tipo) {
        this.idDocumento = idDocumento;
        this.noDocumento = noDocumento;
        this.caDocumento = caDocumento;
        this.coDocumento = coDocumento;
        this.auDocumento = auDocumento;
        this.edDocumento = edDocumento;
        this.apDocumento = apDocumento;
        this.etiqueta = etiqueta;
        this.tipo = tipo;
    }

    //constructor vacio
    public Documento() {
    }

    //metodos
    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getNoDocumento() {
        return noDocumento.toUpperCase();
    }

    public void setNoDocumento(String noDocumento) {
        this.noDocumento = noDocumento;
    }

    public int getCaDocumento() {
        return caDocumento;
    }

    public void setCaDocumento(int caDocumento) {
        this.caDocumento = caDocumento;
    }

    public String getCoDocumento() {
        return coDocumento;
    }

    public void setCoDocumento(String coDocumento) {
        this.coDocumento = coDocumento;
    }

    public String getAuDocumento() {
        return auDocumento;
    }

    public void setAuDocumento(String auDocumento) {
        this.auDocumento = auDocumento;
    }

    public String getEdDocumento() {
        return edDocumento;
    }

    public void setEdDocumento(String edDocumento) {
        this.edDocumento = edDocumento;
    }

    public String getApDocumento() {
        return apDocumento;
    }

    public void setApDocumento(String apDocumento) {
        this.apDocumento = apDocumento;
    }

    public Etiqueta getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(Etiqueta etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}

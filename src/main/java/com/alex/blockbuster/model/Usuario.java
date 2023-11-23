package com.alex.blockbuster.model;

public class Usuario {
    //atributos
    private int idUsuario;
    private String coUsuario;
    private String pasUsuario;
    private int tiUsuario;

    //controlador
    public Usuario(int idUsuario, String coUsuario, String pasUsuario, int tiUsuario) {
        this.idUsuario = idUsuario;
        this.coUsuario = coUsuario;
        this.pasUsuario = pasUsuario;
        this.tiUsuario = tiUsuario;
    }

    //controlador vacio
    public Usuario() {
    }

    //metodos
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCoUsuario() {
        return coUsuario;
    }

    public void setCoUsuario(String coUsuario) {
        this.coUsuario = coUsuario;
    }

    public String getPasUsuario() {
        return pasUsuario;
    }

    public void setPasUsuario(String pasUsuario) {
        this.pasUsuario = pasUsuario;
    }

    public int getTiUsuario() {
        return tiUsuario;
    }

    public void setTiUsuario(int tiUsuario) {
        this.tiUsuario = tiUsuario;
    }
}

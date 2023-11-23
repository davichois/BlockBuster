package com.alex.blockbuster.model;

public class Cliente {
    //atributos
    private int idCliente;
    private String noCliente;
    private String apCliente;
    private String dniCliente;
    private String emCliente;
    private String diCliente;
    private String ncCliente;
    private int geCliente;

    //constructor
    public Cliente(int idCliente, String noCliente, String apCliente, String dniCliente, String emCliente, String diCliente, String ncCliente, int geCliente) {
        this.idCliente = idCliente;
        this.noCliente = noCliente;
        this.apCliente = apCliente;
        this.dniCliente = dniCliente;
        this.emCliente = emCliente;
        this.diCliente = diCliente;
        this.ncCliente = ncCliente;
        this.geCliente = geCliente;
    }

    //metodo
    public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNoCliente() {
        return noCliente;
    }

    public void setNoCliente(String noCliente) {
        this.noCliente = noCliente;
    }

    public String getApCliente() {
        return apCliente;
    }

    public void setApCliente(String apCliente) {
        this.apCliente = apCliente;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getEmCliente() {
        return emCliente;
    }

    public void setEmCliente(String emCliente) {
        this.emCliente = emCliente;
    }

    public String getDiCliente() {
        return diCliente;
    }

    public void setDiCliente(String diCliente) {
        this.diCliente = diCliente;
    }

    public String getNcCliente() {
        return ncCliente;
    }

    public void setNcCliente(String ncCliente) {
        this.ncCliente = ncCliente;
    }

    public int getGeCliente() {
        return geCliente;
    }

    public void setGeCliente(int geCliente) {
        this.geCliente = geCliente;
    }
}

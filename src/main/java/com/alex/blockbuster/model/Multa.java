package com.alex.blockbuster.model;

import java.util.Date;

public class Multa {
    //atributos
    private int idMulta;
    private Date tiMulta;
    private String prMulta;
    private int sdMulta;
    private Cliente cliente;
    private Prestamo prestamo;

    //constructor
    public Multa(int idMulta, Date tiMulta, String prMulta, int sdMulta, Cliente cliente, Prestamo prestamo) {
        this.idMulta = idMulta;
        this.tiMulta = tiMulta;
        this.prMulta = prMulta;
        this.sdMulta = sdMulta;
        this.cliente = cliente;
        this.prestamo = prestamo;
    }

    //metodos
    public int getIdMulta() {
        return idMulta;
    }

    public void setIdMulta(int idMulta) {
        this.idMulta = idMulta;
    }

    public Date getTiMulta() {
        return tiMulta;
    }

    public void setTiMulta(Date tiMulta) {
        this.tiMulta = tiMulta;
    }

    public String getPrMulta() {
        return prMulta;
    }

    public void setPrMulta(String prMulta) {
        this.prMulta = prMulta;
    }

    public int getSdMulta() {
        return sdMulta;
    }

    public void setSdMulta(int sdMulta) {
        this.sdMulta = sdMulta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }
}

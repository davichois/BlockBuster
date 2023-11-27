package com.alex.blockbuster.model;

public class Multa {
    //atributos
    private int idMulta;
    private String prMulta;
    private int sdMulta;
    private Cliente cliente;
    private Prestamo prestamo;

    //constructor
    public Multa(int idMulta, String prMulta, int sdMulta, Cliente cliente, Prestamo prestamo) {
        this.idMulta = idMulta;
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

package com.alex.blockbuster.model;

import java.time.LocalDate;

public class Prestamo {
    //atributos
    private int idDocumento;
    private int isActivo;
    private LocalDate fePrestamo;
    private LocalDate ffPrestamo;
    private Cliente cliente;
    private Documento documento;

    //constructor
    public Prestamo(int idDocumento, int isActivo, LocalDate fePrestamo, LocalDate ffPrestamo, Cliente cliente, Documento documento) {
        this.idDocumento = idDocumento;
        this.isActivo = isActivo;
        this.fePrestamo = fePrestamo;
        this.ffPrestamo = ffPrestamo;
        this.cliente = cliente;
        this.documento = documento;
    }

    //metodos
    public int getIsActivo() {
        return isActivo;
    }

    public void setIsActivo(int isActivo) {
        this.isActivo = isActivo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public LocalDate getFePrestamo() {
        return fePrestamo;
    }

    public void setFePrestamo(LocalDate fePrestamo) {
        this.fePrestamo = fePrestamo;
    }

    public LocalDate getFfPrestamo() {
        return ffPrestamo;
    }

    public void setFfPrestamo(LocalDate ffPrestamo) {
        this.ffPrestamo = ffPrestamo;
    }
}

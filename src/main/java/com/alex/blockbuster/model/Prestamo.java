package com.alex.blockbuster.model;

import java.time.LocalDateTime;

public class Prestamo {
    //atributos
    private int idDocumento;
    private int isActivo;
    private LocalDateTime fePrestamo;
    private Cliente cliente;
    private Documento documento;

    //constructor
    public Prestamo(int idDocumento, int isActivo, LocalDateTime fePrestamo,Cliente cliente, Documento documento) {
        this.idDocumento = idDocumento;
        this.isActivo = isActivo;
        this.cliente = cliente;
        this.documento = documento;
        this.fePrestamo = fePrestamo;
    }

    //metodos
    public int getIdLibro() {
        return idDocumento;
    }

    public void setIdLibro(int idLibro) {
        this.idDocumento = idLibro;
    }

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

    public LocalDateTime getFePrestamo() {
        return fePrestamo;
    }

    public void setFePrestamo(LocalDateTime fePrestamo) {
        this.fePrestamo = fePrestamo;
    }
}

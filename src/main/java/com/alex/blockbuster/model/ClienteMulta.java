package com.alex.blockbuster.model;

import java.time.LocalDate;

public class ClienteMulta {

    private int idMulta;
    private int idPrestamo;
    private int idCliente;
    private String noCliente;
    private String dniCliente;
    private String noDocumento;
    private LocalDate fePrestamo;
    private LocalDate ffPrestamo;
    private int isActivo;
    private String prMulta;
    private int sdPrestamo;

    public ClienteMulta(int idMulta, int idPrestamo, int idCliente, String noCliente, String dniCliente, String noDocumento, LocalDate fePrestamo, LocalDate ffPrestamo, int isActivo, String prMulta, int sdPrestamo) {
        this.idMulta = idMulta;
        this.idPrestamo = idPrestamo;
        this.idCliente = idCliente;
        this.noCliente = noCliente;
        this.dniCliente = dniCliente;
        this.noDocumento = noDocumento;
        this.fePrestamo = fePrestamo;
        this.ffPrestamo = ffPrestamo;
        this.isActivo = isActivo;
        this.prMulta = prMulta;
        this.sdPrestamo = sdPrestamo;
    }

    public String getNoCliente() {
        return noCliente;
    }

    public void setNoCliente(String noCliente) {
        this.noCliente = noCliente;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getNoDocumento() {
        return noDocumento;
    }

    public void setNoDocumento(String noDocumento) {
        this.noDocumento = noDocumento;
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

    public int getIsActivo() {
        return isActivo;
    }

    public void setIsActivo(int isActivo) {
        this.isActivo = isActivo;
    }

    public String getPrMulta() {
        return prMulta;
    }

    public void setPrMulta(String prMulta) {
        this.prMulta = prMulta;
    }

    public int getIdMulta() {
        return idMulta;
    }

    public void setIdMulta(int idMulta) {
        this.idMulta = idMulta;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getSdPrestamo() {
        String message = "";
        if (sdPrestamo == 0){
            message = "Si tiene";
        } else {
            message = "No tiene";
        }
        return message;
    }

    public void setSdPrestamo(int sdPrestamo) {
        this.sdPrestamo = sdPrestamo;
    }
}

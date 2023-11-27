package com.alex.blockbuster.controller;

import com.alex.blockbuster.model.Cliente;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Date;

public class ClienteMultaController {
    //atributos
    public TableView<Cliente> tbl_multa_cliente;
    public TableColumn<Cliente, String> tc_nombre;
    public TableColumn<Cliente, String> tc_dni;
    public TableColumn<Cliente, String> tc_documento;
    public TableColumn<Cliente, Date> tc_fe;
    public TableColumn<Cliente, Date> tc_fr;
    public TableColumn<Cliente, Integer> tc_multa;
    public TableColumn<Cliente, String> tc_deuda;
    public TextField tc_busca_multa;
    public Button btn_buscar;
    public Button btn_regresar;

    private ObservableList<Cliente> clientes;

    //metodos
    public void BuscarMulta(ActionEvent actionEvent) {

    }

    public void RegresarPrestamo(ActionEvent actionEvent) {
        Stage stage = (Stage) this.btn_regresar.getScene().getWindow();
        stage.close();
    }

}

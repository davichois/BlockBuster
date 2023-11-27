package com.alex.blockbuster.controller;

import com.alex.blockbuster.model.*;
import com.alex.blockbuster.utils.Conectionsbd;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteMultaController implements Initializable{
    //atributos
    public TableView<ClienteMulta> tbl_multa_cliente;
    public TableColumn<ClienteMulta, String> tc_nombre;
    public TableColumn<ClienteMulta, String> tc_dni;
    public TableColumn<ClienteMulta, String> tc_documento;
    public TableColumn<ClienteMulta, Date> tc_fe;
    public TableColumn<ClienteMulta, Date> tc_fr;
    public TableColumn<ClienteMulta, String> tc_multa;
    public TableColumn<ClienteMulta, String> tc_deuda;
    public TextField tc_busca_multa;
    public Button btn_regresar;
    public Button btn_recalcular;

    private ObservableList<ClienteMulta> clienteMultas;
    private ObservableList<ClienteMulta> clienteMultasFilter;
    private static Conectionsbd db = new Conectionsbd();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clienteMultas = FXCollections.observableArrayList();
        clienteMultasFilter = FXCollections.observableArrayList();

        this.tbl_multa_cliente.setItems(clienteMultas);
        this.tc_nombre.setCellValueFactory(new PropertyValueFactory<ClienteMulta, String>("noCliente"));
        this.tc_dni.setCellValueFactory(new PropertyValueFactory<ClienteMulta, String>("dniCliente"));
        this.tc_documento.setCellValueFactory(new PropertyValueFactory<ClienteMulta, String>("noDocumento"));
        this.tc_fe.setCellValueFactory(new PropertyValueFactory<ClienteMulta, Date>("fePrestamo"));
        this.tc_fr.setCellValueFactory(new PropertyValueFactory<ClienteMulta, Date>("ffPrestamo"));
        this.tc_multa.setCellValueFactory(new PropertyValueFactory<ClienteMulta, String>("isActivo"));
        this.tc_deuda.setCellValueFactory(new PropertyValueFactory<ClienteMulta, String>("prMulta"));


        try {
            traerDatosClienteMultas();
        } catch (SQLException ex) {
            Logger.getLogger(RegistroDataController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //metodos
    public void RegresarPrestamo(ActionEvent actionEvent) {
        Stage stage = (Stage) this.btn_regresar.getScene().getWindow();
        stage.close();
    }

    public void RecalcularDeuda(ActionEvent actionEvent) {
    }

    private void traerDatosClienteMultas() throws SQLException {
        Connection conex = db.openConnection();
        PreparedStatement ps = conex.prepareStatement("select c.no_cliente, c.dni_cliente, d.no_documento, p.fe_prestamo, p.ff_prestamo, p.is_activo, m.pr_multa from multa as m join cliente as c on m.id_cliente = c.id_cliente join prestamo as p on m.id_prestamo = p.id_prestamo join documento as d on p.id_documento = d.id_documento");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ClienteMulta newClienteMulta = new ClienteMulta(rs.getString("no_cliente"), rs.getString("dni_cliente"), rs.getString("no_documento"), rs.getDate("fe_prestamo").toLocalDate(), rs.getDate("ff_prestamo").toLocalDate(), rs.getInt("is_activo"), rs.getString("pr_multa"));
            this.clienteMultas.add(newClienteMulta);
            this.tbl_multa_cliente.refresh();
        }
    }

    public void BuscarMultaCliente(KeyEvent keyEvent) {
        String filtrador = this.tc_busca_multa.getText();
        if (filtrador.isEmpty()) {
            this.tbl_multa_cliente.setItems(clienteMultas);
        } else {
            this.clienteMultasFilter.clear();
            for (ClienteMulta cm : clienteMultas) {
                if (cm.getNoCliente().toLowerCase().contains(filtrador.toLowerCase())) {
                    this.clienteMultasFilter.add(cm);
                }
            }
            this.tbl_multa_cliente.setItems(clienteMultasFilter);
            this.tbl_multa_cliente.refresh();
        }
    }
}

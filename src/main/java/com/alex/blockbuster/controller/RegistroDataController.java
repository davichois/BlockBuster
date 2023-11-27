package com.alex.blockbuster.controller;

import com.alex.blockbuster.model.Documento;
import com.alex.blockbuster.model.Etiqueta;
import com.alex.blockbuster.utils.Conectionsbd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

public class RegistroDataController implements Initializable {
    //atributos
    public Button btn_buscar;
    public TextField txt_ingrese_dni;
    public Button btn_enviar_email;
    public Button btn_confirmar;
    public Button btn_cancelar;
    public ComboBox<String> cb_nombre;
    public ComboBox<Date> cb_fecha_entrega;
    public Button btn_multa_previa;
    public ComboBox<Date> cb_fecha_regreso;
    public ComboBox<String> cb_tipo_catalogo;
    public ComboBox<String> cb_tema_catalogo;
    public Label lb_dni_prestamo;
    public Label lb_nombre_prestamo;
    public Label lb_email_prestamo;

    private ObservableList<Documento> documentos;
    private ObservableList<String> tipo_documentos;
    private ObservableList<String> etiquetas;

    //metodos
    private static Conectionsbd db = new Conectionsbd();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        documentos = FXCollections.observableArrayList();
        tipo_documentos = FXCollections.observableArrayList();
        etiquetas = FXCollections.observableArrayList();

        try {
            traerDatosProducto();
            traerDatosEtiqueta();
            traerDatosTipoDocumentos();
        } catch (SQLException ex) {
            Logger.getLogger(RegistroDataController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void traerDatosProducto() throws SQLException {
        Connection conex = db.openConnection();
        PreparedStatement ps = conex.prepareStatement("select * from Documentos");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Etiqueta newEtiqueta = new Etiqueta(rs.getInt("id_Documento"), rs.getString("no_documento"));
            this.etiquetas.add(newEtiqueta.getIdEtiqueta() +" : "+ newEtiqueta.getNoEtiqueta());
            this.cb_nombre.setItems(documentos);
    }

    public void BuscarDatosCliente(ActionEvent actionEvent) {
    }

    public void EnviarEmailCliente(ActionEvent actionEvent) {
    }

    public void ConfirmarPrestamo(ActionEvent actionEvent) {

    }

    public void CancelarPrestamo(ActionEvent actionEvent) {
        Stage stage = (Stage) this.btn_cancelar.getScene().getWindow();
        stage.close();
    }

    public void MultaPreviaCliente(ActionEvent actionEvent) {
    }
}

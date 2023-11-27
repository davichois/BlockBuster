package com.alex.blockbuster.controller;

import com.alex.blockbuster.Application;
import com.alex.blockbuster.model.Cliente;
import com.alex.blockbuster.model.Documento;
import com.alex.blockbuster.model.Etiqueta;
import com.alex.blockbuster.model.Tipo;
import com.alex.blockbuster.utils.Conectionsbd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistroDataController implements Initializable {
    //atributos
    public Button btn_buscar;
    public TextField txt_ingrese_dni;
    public Button btn_confirmar;
    public Button btn_cancelar;
    public ComboBox<String> cb_nombre;
    public Button btn_multa_previa;
    public ComboBox<String> cb_tipo_catalogo;
    public ComboBox<String> cb_tema_catalogo;
    public Label lb_dni_prestamo;
    public Label lb_nombre_prestamo;
    public Label lb_email_prestamo;
    public DatePicker dp_fecha_regreso;
    public DatePicker dp_fecha_entrega;

    private ObservableList<String> documentos;
    private ObservableList<String> tipo_documentos;
    private ObservableList<String> etiquetas;
    private LocalDate fe_prestamo;
    private LocalDate ff_prestamo;
    private int idCliente;
    private int idDocumento;


    //metodos
    private static Conectionsbd db = new Conectionsbd();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        documentos = FXCollections.observableArrayList();
        tipo_documentos = FXCollections.observableArrayList();
        etiquetas = FXCollections.observableArrayList();

        try {
            traerDatoDocumentos();
            traerDatosEtiqueta();
            traerDatosTipoDocumentos();
        } catch (SQLException ex) {
            Logger.getLogger(RegistroDataController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void traerDatoDocumentos() throws SQLException {
        Connection conex = db.openConnection();
        PreparedStatement ps = conex.prepareStatement("select * from Documento");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Documento newDocument = new Documento(rs.getInt("id_documento"), capitalize(rs.getString("no_documento")), rs.getInt("nu_cantidad"), rs.getString("co_documento"), rs.getString("au_documento"), rs.getString("ed_documento"), rs.getString("ap_documento"), new Etiqueta(rs.getInt("id_etiqueta")), new Tipo(rs.getInt("id_tipo")));
            this.documentos.add(newDocument.getIdDocumento() +" : "+ newDocument.getNoDocumento());
            this.cb_nombre.setItems(documentos);
        }
    }

    private void traerDatosEtiqueta() throws SQLException {
        Connection conex = db.openConnection();
        PreparedStatement ps = conex.prepareStatement("select * from Etiqueta where is_activo = 0");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Etiqueta newEtiqueta = new Etiqueta(rs.getInt("id_etiqueta"), rs.getString("no_etiqueta"), rs.getInt("is_activo"));
            this.etiquetas.add(newEtiqueta.getIdEtiqueta() +" : "+ newEtiqueta.getNoEtiqueta());
            this.cb_tema_catalogo.setItems(etiquetas);
        }
    }

    private void traerDatosTipoDocumentos() throws SQLException {
        Connection conex = db.openConnection();
        PreparedStatement ps = conex.prepareStatement("select * from Tipo");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Tipo newTipo = new Tipo(rs.getInt("id_tipo"), rs.getString("no_tipo"));
            this.tipo_documentos.add(newTipo.getIdTipo() +" : "+ newTipo.getNoTipo());
            this.cb_tipo_catalogo.setItems(tipo_documentos);
        }
    }

    public String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public void BuscarDatosCliente(ActionEvent actionEvent) throws SQLException {
        Connection conex = db.openConnection();
        PreparedStatement ps = conex.prepareStatement("select * from Cliente where dni_cliente=?");
        ps.setString(1, this.txt_ingrese_dni.getText());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Cliente newCliente = new Cliente(rs.getInt("id_cliente"), rs.getString("no_cliente"), rs.getString("ap_cliente"), rs.getString("dni_cliente"), rs.getString("em_cliente"), rs.getString("di_cliente"), rs.getString("nc_cliente"), rs.getInt("ge_cliente"));
            this.lb_dni_prestamo.setText(newCliente.getDniCliente());
            this.lb_nombre_prestamo.setText(newCliente.getNoCliente());
            this.lb_email_prestamo.setText(newCliente.getEmCliente());
            idCliente = newCliente.getIdCliente();
        }
    }

    public void ConfirmarPrestamo(ActionEvent actionEvent) throws SQLException {
        Connection conex = db.openConnection();
        PreparedStatement ps = conex.prepareStatement("insert into prestamo(id_cliente, id_documento, fe_prestamo, ff_prestamo) values (?,?,?,?)");
        ps.setInt(1, this.idCliente);
        ps.setInt(2, this.idDocumento);
        ps.setDate(3, Date.valueOf(this.fe_prestamo));
        ps.setDate(4, Date.valueOf(this.ff_prestamo));

        int resultado = ps.executeUpdate();

        Alert alert;
        if (resultado > 0) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("Prestamo creado correctamente.");
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error al insertar prestamo");
        }
        alert.showAndWait();
    }

    public void CancelarPrestamo(ActionEvent actionEvent) {
        Stage stage = (Stage) this.btn_cancelar.getScene().getWindow();
        stage.close();
    }

    public void MultaPreviaCliente(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Application.class.getResource("ClienteMultaView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Registrar");
        stage.setScene(scene);
        stage.show();
    }

    public void GetInicioFecha(ActionEvent actionEvent) {
        fe_prestamo = this.dp_fecha_entrega.getValue();
    }

    public void GetFinalFecha(ActionEvent actionEvent) {
        ff_prestamo = this.dp_fecha_regreso.getValue();
    }

    public void getDocumeto(ActionEvent actionEvent) {
        idDocumento = Integer.parseInt(this.cb_nombre.getValue().split(":")[0].trim());
    }
}

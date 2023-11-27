package com.alex.blockbuster.controller;

import com.alex.blockbuster.model.Documento;
import com.alex.blockbuster.model.Etiqueta;
import com.alex.blockbuster.model.Tipo;
import com.alex.blockbuster.utils.Conectionsbd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeleccionCatalogoController implements Initializable {
    //atributos
    public TableView<Documento> tbl_documentos;
    public ComboBox<String> cb_tipo_informacion;
    public ComboBox<String> cb_tema_interes;
    public TextField txt_buscar;
    public TableColumn<Documento, Integer> tc_id;
    public TableColumn<Documento, String> tc_nombre;
    public Button btn_regresar;

    private ObservableList<Documento> producto;
    private ObservableList<String> tipo_documentos;
    private ObservableList<String> etiquetas;

    //metodos
    private static Conectionsbd db = new Conectionsbd();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        producto = FXCollections.observableArrayList();
        tipo_documentos = FXCollections.observableArrayList();
        etiquetas = FXCollections.observableArrayList();

        this.tbl_documentos.setItems(producto);

        this.tc_id.setCellValueFactory(new PropertyValueFactory<Documento, Integer>("idDocumento"));
        this.tc_nombre.setCellValueFactory(new PropertyValueFactory<Documento, String>("noDocumento"));

        try {
            traerDatosProducto();
            traerDatosEtiqueta();
            traerDatosTipoDocumentos();
        } catch (SQLException ex) {
            Logger.getLogger(SeleccionCatalogoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void traerDatosProducto() throws SQLException {
        Connection conex = db.openConnection();
        PreparedStatement ps = conex.prepareStatement("select * from Documento");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Documento newDocument = new Documento(rs.getInt("id_documento"), rs.getString("no_documento"), rs.getInt("nu_cantidad"), rs.getString("co_documento"), rs.getString("au_documento"), rs.getString("ed_documento"), rs.getString("ap_documento"), new Etiqueta(rs.getInt("id_etiqueta")), new Tipo(rs.getInt("id_tipo")));
            this.producto.add(newDocument);
            this.tbl_documentos.refresh();
        }
    }

    private void traerDatosEtiqueta() throws SQLException {
        Connection conex = db.openConnection();
        PreparedStatement ps = conex.prepareStatement("select * from Etiqueta where is_activo = 0");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Etiqueta newEtiqueta = new Etiqueta(rs.getInt("id_etiqueta"), rs.getString("no_etiqueta"), rs.getInt("is_activo"));
            this.etiquetas.add(newEtiqueta.getIdEtiqueta() +" : "+ newEtiqueta.getNoEtiqueta());
            this.cb_tema_interes.setItems(etiquetas);
        }
    }

    private void traerDatosTipoDocumentos() throws SQLException {
        Connection conex = db.openConnection();
        PreparedStatement ps = conex.prepareStatement("select * from Tipo");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Tipo newTipo = new Tipo(rs.getInt("id_tipo"), rs.getString("no_tipo"));
            this.tipo_documentos.add(newTipo.getIdTipo() +" : "+ newTipo.getNoTipo());
            this.cb_tipo_informacion.setItems(tipo_documentos);
        }
    }

    public void RegresarVentana(ActionEvent actionEvent) {
        Stage stage = (Stage) this.btn_regresar.getScene().getWindow();
        stage.close();
    }
}

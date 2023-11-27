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
import javafx.scene.input.KeyEvent;
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

    private ObservableList<Documento> documentos;
    private ObservableList<Documento> documentosFiltrado;
    private ObservableList<String> tipo_documentos;
    private ObservableList<String> etiquetas;

    //metodos
    private static Conectionsbd db = new Conectionsbd();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        documentos = FXCollections.observableArrayList();
        documentosFiltrado = FXCollections.observableArrayList();
        tipo_documentos = FXCollections.observableArrayList();
        etiquetas = FXCollections.observableArrayList();

        this.tbl_documentos.setItems(documentos);

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
            Documento newDocument = new Documento(rs.getInt("id_documento"), capitalize(rs.getString("no_documento")), rs.getInt("nu_cantidad"), rs.getString("co_documento"), rs.getString("au_documento"), rs.getString("ed_documento"), rs.getString("ap_documento"), new Etiqueta(rs.getInt("id_etiqueta")), new Tipo(rs.getInt("id_tipo")));
            this.documentos.add(newDocument);
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

    public void BusquedaDocumento(KeyEvent keyEvent) {
        String filtrador = this.txt_buscar.getText();
        if (filtrador.isEmpty()) {
            this.tbl_documentos.setItems(documentos);
        } else {
            this.documentosFiltrado.clear();
            for (Documento d : documentos) {
                if (d.getNoDocumento().toLowerCase().contains(filtrador.toLowerCase())) {
                    this.documentosFiltrado.add(d);
                }
            }
            this.tbl_documentos.setItems(documentosFiltrado);
            this.tbl_documentos.refresh();
        }
    }

    public String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public void FiltroTipoDocumento(ActionEvent actionEvent) {
        String filtrador = this.cb_tipo_informacion.getValue().split(":")[0].trim();
        if (filtrador.isEmpty()) {
            this.tbl_documentos.setItems(documentos);
        } else {
            this.documentosFiltrado.clear();
            for (Documento d : documentos) {
                if (d.getTipo().getIdTipo() == Integer.parseInt(filtrador)) {
                    this.documentosFiltrado.add(d);
                }
            }
            this.tbl_documentos.setItems(documentosFiltrado);
            this.tbl_documentos.refresh();
        }
    }

    public void FiltroEtiqueta(ActionEvent actionEvent) {
        String filtrador = this.cb_tema_interes.getValue().split(":")[0].trim();
        if (filtrador.isEmpty()) {
            this.tbl_documentos.setItems(documentos);
        } else {
            this.documentosFiltrado.clear();
            for (Documento d : documentos) {
                if (d.getEtiqueta().getIdEtiqueta() == Integer.parseInt(filtrador)) {
                    this.documentosFiltrado.add(d);
                }
            }
            this.tbl_documentos.setItems(documentosFiltrado);
            this.tbl_documentos.refresh();
        }
    }
}

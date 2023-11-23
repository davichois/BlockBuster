package com.alex.blockbuster.controller;

import com.alex.blockbuster.model.Documento;
import com.alex.blockbuster.model.Etiqueta;
import com.alex.blockbuster.model.Tipo;
import com.alex.blockbuster.utils.Conectionsbd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeleccionCatalogoController implements Initializable {

    public TableView<Documento> tbl_documentos;
    public ComboBox cb_tipo_informacion;
    public ComboBox cb_tema_interes;
    public TextField txt_buscar;
    public TableColumn tc_id;
    public TableColumn tc_nombre;

    private ObservableList<Documento> producto;
    private static Conectionsbd db = new Conectionsbd();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        producto = FXCollections.observableArrayList();
        this.tbl_documentos.setItems(producto);

        this.tc_id.setCellValueFactory(new PropertyValueFactory("id_documento"));
        this.tc_nombre.setCellValueFactory(new PropertyValueFactory("no_documento"));

        try {
            traerDatos();
        } catch (SQLException ex) {
            Logger.getLogger(SeleccionCatalogoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void traerDatos() throws SQLException {
        Connection conex = db.openConnection();
        PreparedStatement ps = conex.prepareStatement("select * from Documento");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Documento newDocument = new Documento(rs.getInt("id_documento"), rs.getString("no_documento"), rs.getInt("nu_cantidad"), rs.getString("co_documento"), rs.getString("au_documento"), rs.getString("ed_documento"), rs.getString("ap_documento"), new Etiqueta(rs.getInt("id_etiqueta")), new Tipo(rs.getInt("id_tipo")));
            System.out.println(newDocument.getNoDocumento());
            this.producto.add(newDocument);
            this.tbl_documentos.refresh();
        }
    }

}

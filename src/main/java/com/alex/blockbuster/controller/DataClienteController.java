package com.alex.blockbuster.controller;

import com.alex.blockbuster.model.Cliente;
import com.alex.blockbuster.model.Usuario;
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

public class DataClienteController implements Initializable {
    public Button btn_guardar;
    public Button btn_cd;
    public Button btn_eliminar;
    public TextField tf_dni;
    public TextField tf_nombre;
    public TextField tf_apellido;
    public TextField tf_email;
    public TextField tf_celular;
    public TextField tf_direccion;
    public Button btn_regresar;
    public TableView<Cliente> tbl_cliente;
    public TableColumn<Cliente, Integer> tc_id;
    public TableColumn<Cliente, String> tc_dni;
    public TableColumn<Cliente, String> tc_nombre;
    public TableColumn<Cliente, String> tc_email;
    public TableColumn<Cliente, String> tc_celular;
    public RadioButton rb_masculino;
    public RadioButton rb_feminenino;
    public Button btn_actualizar;
    public Button btn_clear;


    private ObservableList<Cliente> clientes;
    private int sexoU;
    private int idCliente = 0;

    private static Conectionsbd db = new Conectionsbd();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clientes = FXCollections.observableArrayList();

        this.tbl_cliente.setItems(clientes);
        this.tc_id.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("idCliente"));
        this.tc_dni.setCellValueFactory(new PropertyValueFactory<Cliente, String>("dniCliente"));
        this.tc_nombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("noCliente"));
        this.tc_email.setCellValueFactory(new PropertyValueFactory<Cliente, String>("emCliente"));
        this.tc_celular.setCellValueFactory(new PropertyValueFactory<Cliente, String>("ncCliente"));

        try {
            traerDatosCliente();
        } catch (SQLException ex) {
            Logger.getLogger(SeleccionCatalogoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void GuardarDatosCliente(ActionEvent actionEvent) throws SQLException {
        Connection conex = db.openConnection();
        PreparedStatement ps = conex.prepareStatement("insert into cliente(no_cliente, ap_cliente, dni_cliente, nc_cliente, ge_cliente, di_cliente, em_cliente) values (?,?,?,?,?,?,?)");
        ps.setString(1, this.tf_nombre.getText());
        ps.setString(2, this.tf_apellido.getText());
        ps.setString(3, this.tf_dni.getText());
        ps.setString(4, this.tf_celular.getText());
        ps.setInt(5, this.sexoU);
        ps.setString(6, this.tf_direccion.getText());
        ps.setString(7, this.tf_email.getText());

        int resultado = ps.executeUpdate();

        Alert alert;
        if (resultado > 0) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("Cliente creado correctamente.");
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error al insertar cliente");
        }
        alert.showAndWait();

        this.clientes.clear();
        traerDatosCliente();
        db.closeConnection();
        limpiarCampos();
    }

    public void CargarDatosCliente(ActionEvent actionEvent) {
        Cliente c = this.tbl_cliente.getSelectionModel().getSelectedItem();

        if (c == null) {
            System.out.println("Error");
            System.out.println("Debes seleccionar a un cliente.");
        } else {
            this.idCliente = c.getIdCliente();

            this.tf_dni.setText(c.getDniCliente());
            this.tf_nombre.setText(c.getNoCliente());
            this.tf_apellido.setText(c.getApCliente());
            this.tf_email.setText(c.getEmCliente());
            this.tf_celular.setText(c.getNcCliente());
            this.tf_direccion.setText(c.getDiCliente());
            if (c.getGeCliente() == 0){
                this.rb_feminenino.setSelected(true);
                this.rb_masculino.setSelected(true);
            } else {
                this.rb_masculino.setSelected(false);
                this.rb_feminenino.setSelected(true);
            }
        }
    }

    public void EliminarDatosCliente(ActionEvent actionEvent) throws SQLException {
        Cliente c = this.tbl_cliente.getSelectionModel().getSelectedItem();

        if (c == null) {
            System.out.println("Error");
            System.out.println("Debes seleccionar a un cliente.");
        } else {
            Connection conex = db.openConnection();
            PreparedStatement ps = conex.prepareStatement("delete from cliente where id_cliente=?");
            ps.setInt(1, c.getIdCliente());

            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                System.out.println("Cliente Eliminado correctamente");
            } else {
                System.out.println("Error al eliminar cliente");
            }
            this.clientes.clear();
            traerDatosCliente();
            db.closeConnection();
        }
    }

    public void RegresarMenu(ActionEvent actionEvent) {
        Stage stage = (Stage) this.btn_regresar.getScene().getWindow();
        stage.close();
    }

    public void getSex(ActionEvent actionEvent) {
        if (rb_masculino.isSelected()){
            sexoU = 0;
        } else if (rb_feminenino.isSelected()){
            sexoU = 1;
        }
    }

    public void ActualizarDatosCliente(ActionEvent actionEvent) throws SQLException {
        Alert alert;
        if (this.idCliente != 0){
            Connection conex = db.openConnection();
            PreparedStatement ps = conex.prepareStatement("update cliente set no_cliente=?, ap_cliente=?, dni_cliente=?, nc_cliente=?, ge_cliente=?, di_cliente=?, em_cliente=? where id_cliente=?");
            ps.setString(1, this.tf_nombre.getText());
            ps.setString(2, this.tf_apellido.getText());
            ps.setString(3, this.tf_dni.getText());
            ps.setString(4, this.tf_celular.getText());
            ps.setInt(5, this.sexoU);
            ps.setString(6, this.tf_direccion.getText());
            ps.setString(7, this.tf_email.getText());
            ps.setInt(8, idCliente);

            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                System.out.println(alert);
                alert.setHeaderText(null);
                alert.setTitle("Info");
                alert.setContentText("Cliente actualizado correctamente.");
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                System.out.println(alert);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Error al actualizar cliente");
            }
            alert.showAndWait();

            this.clientes.clear();
            traerDatosCliente();
            db.closeConnection();
            limpiarCampos();
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            System.out.println(alert);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No hay nada que actualizar");
        }
    }

    private void traerDatosCliente() throws SQLException {
        Connection conex = db.openConnection();
        PreparedStatement ps = conex.prepareStatement("select * from Cliente");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Cliente newCliente = new Cliente(rs.getInt("id_cliente"), rs.getString("no_cliente"), rs.getString("ap_cliente"), rs.getString("dni_cliente"), rs.getString("em_cliente"), rs.getString("di_cliente"), rs.getString("nc_cliente"), rs.getInt("ge_cliente"));
            this.clientes.add(newCliente);
            this.tbl_cliente.refresh();
        }
    }

    private void limpiarCampos() {
        this.tf_nombre.clear();
        this.tf_dni.clear();
        this.tf_celular.clear();
        this.tf_apellido.clear();
        this.tf_email.clear();
        this.tf_direccion.clear();
        this.rb_feminenino.setSelected(false);
        this.rb_masculino.setSelected(false);
    }

    public void LimpiarDatosCliente(ActionEvent actionEvent) {
        limpiarCampos();
    }
}

package com.alex.blockbuster.controller;

import com.alex.blockbuster.utils.Conectionsbd;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NuevoUsuarioController {
    public Button btn_cancelar_nuevo_usuario;
    public Button btn_guardar_nuevo_usuario;
    public TextField tf_usuario;
    public TextField tf_contrasena;
    public TextField tf_confirm_contrasena;
    public RadioButton rb_admin;
    public RadioButton rb_no_admin;

    public int isAdmin = 0;

    private static Conectionsbd db = new Conectionsbd();

    public void CancelarNuevoUsuario(ActionEvent actionEvent) {
        Stage stage = (Stage) this.btn_cancelar_nuevo_usuario.getScene().getWindow();
        stage.close();
    }

    public void GuardarNuevoUsuario(ActionEvent actionEvent) throws SQLException {

        Connection conex = db.openConnection();
        PreparedStatement ps = conex.prepareStatement("insert into usuario(co_usuario, pas_usuario, ti_usuario) values (?,?,?)");
        ps.setString(1, this.tf_usuario.getText());
        ps.setString(2, this.tf_contrasena.getText());
        ps.setInt(3, this.isAdmin);

        int resultado = ps.executeUpdate();

        Alert alert;
        if (resultado > 0) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("Usuario creado correctamente.");

            Stage stage = (Stage) this.btn_guardar_nuevo_usuario.getScene().getWindow();
            stage.close();

        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error al insertar cliente");
        }
        alert.showAndWait();

    }

    public void getAdmin(ActionEvent actionEvent) {
        if (rb_admin.isSelected()){
            isAdmin = 1;
        } else if (rb_no_admin.isSelected()){
            isAdmin = 0;
        }
    }
}

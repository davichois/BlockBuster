package com.alex.blockbuster.controller;

import com.alex.blockbuster.Application;
import com.alex.blockbuster.utils.Conectionsbd;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InicioSesionController {


    public Button btn_inicio_sesion;
    public TextField txtCodeUser;
    public TextField txtContrasena;
    public Label mensajeInicioSesion;

    private static Conectionsbd db = new Conectionsbd();

    public void InicioSesion(ActionEvent actionEvent) throws SQLException, IOException {
        String codigoUsuario = this.txtCodeUser.getText();
        String passContrasena = this.txtContrasena.getText();

        String codigoUsuarioQ = "";
        String passContrasenaQ = "";

        Connection connection = db.openConnection();
        PreparedStatement ps = connection.prepareStatement("select * from usuario where co_usuario=? and pas_usuario=?");
        ps.setString(1,codigoUsuario);
        ps.setString(2,passContrasena);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            codigoUsuarioQ = rs.getString("co_usuario");
            passContrasenaQ = rs.getString("pas_usuario");
        }

        if (codigoUsuarioQ.equals(codigoUsuario)) {
            if (passContrasenaQ.equals(passContrasena)){
                // Poner mensaje
                mensajeInicioSesion.setText("Bienvenido");
                // Abrir nueva ventana
                Parent root = FXMLLoader.load(Application.class.getResource("MenuSesionView.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setResizable(true);
                stage.setTitle("Menu inicial");
                stage.setScene(scene);
                stage.show();

                // Cerrar la ventana anterior
                Stage old = (Stage) btn_inicio_sesion.getScene().getWindow();
                old.close();
            } else {
                mensajeInicioSesion.setText("No tienes una cuenta");
            }
        } else {
            mensajeInicioSesion.setText("No tienes una cuenta");
        }

        System.out.println(codigoUsuarioQ + " " + passContrasenaQ);

    }
}

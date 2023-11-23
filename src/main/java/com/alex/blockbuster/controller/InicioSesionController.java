package com.alex.blockbuster.controller;

import com.alex.blockbuster.utils.Conectionsbd;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    public void InicioSesion(ActionEvent actionEvent) throws SQLException {
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
                mensajeInicioSesion.setText("Bienvenido");
            } else {
                mensajeInicioSesion.setText("No tienes una cuenta");
            }
        } else {
            mensajeInicioSesion.setText("No tienes una cuenta");
        }

        System.out.println(codigoUsuarioQ + " " + passContrasenaQ);

    }
}

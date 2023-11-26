package com.alex.blockbuster.controller;

import com.alex.blockbuster.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuSesionController implements Initializable {
    public Button btn_catalogo;
    public Button btn_registrar;
    public Button btn_prestamo;
    public Button btn_regresar;
    public Button btn_administrar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boolean isAdmin = InicioSesionController.isAdmin;
        btn_administrar.setVisible(isAdmin);
    }

    public void navCatalogo(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Application.class.getResource("SeleccionCatalogoView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Catalogo");
        stage.setScene(scene);
        stage.show();
    }

    public void navRegistrar(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Application.class.getResource("DataClienteView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Registrar");
        stage.setScene(scene);
        stage.show();
    }

    public void navPrestamo(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Application.class.getResource("RegistroDataView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Prestamo");
        stage.setScene(scene);
        stage.show();
    }

    public void navAdministrar(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Application.class.getResource("AdministradorUsuarioView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Administracion");
        stage.setScene(scene);
        stage.show();
    }

    public void navCerrarSesion(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Application.class.getResource("InicioSesionView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Inicio de Sesion");
        stage.setScene(scene);
        stage.show();

        // Cerrar la ventana anterior
        Stage old = (Stage) btn_regresar.getScene().getWindow();
        old.close();
    }

}

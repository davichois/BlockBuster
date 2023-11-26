package com.alex.blockbuster.controller;

import com.alex.blockbuster.Application;
import com.alex.blockbuster.model.Usuario;
import com.alex.blockbuster.utils.Conectionsbd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdministradorUsuarioController implements Initializable {

    public TableView<Usuario> tbl_usuarios;
    public TableColumn<Usuario, Integer> tc_id;
    public TableColumn<Usuario, String> tc_usuario;
    public TableColumn<Usuario, String> tc_contrasena;
    public Button btn_eliminar;
    public Button btn_regresar;
    public Button btn_nuevo_usuario;

    private ObservableList<Usuario> usuarios;

    private static Conectionsbd db = new Conectionsbd();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usuarios = FXCollections.observableArrayList();

        this.tbl_usuarios.setItems(usuarios);
        this.tc_id.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("idUsuario"));
        this.tc_usuario.setCellValueFactory(new PropertyValueFactory<Usuario, String>("coUsuario"));
        this.tc_contrasena.setCellValueFactory(new PropertyValueFactory<Usuario, String>("pasUsuario"));

        try {
            traerDatosUsuario();
        } catch (SQLException ex) {
            Logger.getLogger(SeleccionCatalogoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void EliminarUsuario(ActionEvent actionEvent) throws SQLException {
        Usuario u = this.tbl_usuarios.getSelectionModel().getSelectedItem();

        if (u == null) {
            System.out.println("Error");
            System.out.println("Debes seleccionar a un usuario.");
        } else {
            Connection conex = db.openConnection();
            PreparedStatement ps = conex.prepareStatement("delete from usuario where id_usuario=?");
            ps.setInt(1, u.getIdUsuario());

            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                System.out.println("Usuario Eliminado correctamente");
            } else {
                System.out.println("Error al eliminar usuario");
            }
            this.usuarios.clear();
            traerDatosUsuario();
            db.closeConnection();
        }
    }

    public void RegresarMenu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Application.class.getResource("MenuSesionView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Menu inicial");
        stage.setScene(scene);
        stage.show();

        // Cerrar la ventana anterior
        Stage old = (Stage) btn_regresar.getScene().getWindow();
        old.close();
    }

    private void traerDatosUsuario() throws SQLException {
        Connection conex = db.openConnection();
        PreparedStatement ps = conex.prepareStatement("select * from Usuario");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Usuario newUser = new Usuario(rs.getInt("id_usuario"), rs.getString("co_usuario"), rs.getString("pas_usuario"), rs.getInt("pas_usuario"));
            this.usuarios.add(newUser);
            this.tbl_usuarios.refresh();
        }
    }

    public void CrearNuevoUsuario(ActionEvent actionEvent) {
    }
}

package com.alex.blockbuster.controller;

import com.alex.blockbuster.model.ClienteMulta;
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
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteMultaController implements Initializable{
    //atributos
    public TableView<ClienteMulta> tbl_multa_cliente;
    public TableColumn<ClienteMulta, String> tc_nombre;
    public TableColumn<ClienteMulta, String> tc_dni;
    public TableColumn<ClienteMulta, String> tc_documento;
    public TableColumn<ClienteMulta, Date> tc_fe;
    public TableColumn<ClienteMulta, Date> tc_fr;
    public TableColumn<ClienteMulta, String> tc_multa;
    public TableColumn<ClienteMulta, String> tc_deuda;
    public TextField tc_busca_multa;
    public Button btn_regresar;
    public Button btn_recalcular;
    public Button btn_cancelacion_prestamo;
    public Button btn_cancelacion_multa;

    private ObservableList<ClienteMulta> clienteMultas;
    private ObservableList<ClienteMulta> clienteMultasFilter;
    private static Conectionsbd db = new Conectionsbd();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clienteMultas = FXCollections.observableArrayList();
        clienteMultasFilter = FXCollections.observableArrayList();

        this.tbl_multa_cliente.setItems(clienteMultas);
        this.tc_nombre.setCellValueFactory(new PropertyValueFactory<ClienteMulta, String>("noCliente"));
        this.tc_dni.setCellValueFactory(new PropertyValueFactory<ClienteMulta, String>("dniCliente"));
        this.tc_documento.setCellValueFactory(new PropertyValueFactory<ClienteMulta, String>("noDocumento"));
        this.tc_fe.setCellValueFactory(new PropertyValueFactory<ClienteMulta, Date>("fePrestamo"));
        this.tc_fr.setCellValueFactory(new PropertyValueFactory<ClienteMulta, Date>("ffPrestamo"));
        this.tc_multa.setCellValueFactory(new PropertyValueFactory<ClienteMulta, String>("sdPrestamo"));
        this.tc_deuda.setCellValueFactory(new PropertyValueFactory<ClienteMulta, String>("prMulta"));


        try {
            traerDatosClienteMultas();
        } catch (SQLException ex) {
            Logger.getLogger(RegistroDataController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //metodos
    public void RegresarPrestamo(ActionEvent actionEvent) {
        Stage stage = (Stage) this.btn_regresar.getScene().getWindow();
        stage.close();
    }

    public void RecalcularDeuda(ActionEvent actionEvent) throws ParseException, SQLException {
        ClienteMulta cm = this.tbl_multa_cliente.getSelectionModel().getSelectedItem();
        if (cm == null) {
            System.out.println("Error");
            System.out.println("Debes seleccionar a un cliente.");
        } else {
            if (cm.getIdMulta() == 900){
                LocalDate ultimaFechaPrestamo = cm.getFfPrestamo();
                LocalDate fechaHoy = LocalDate.now();

                long days = fechaHoy.toEpochDay() - ultimaFechaPrestamo.toEpochDay();
                if (days > 1) {
                    // Creamos multa
                    Connection conex = db.openConnection();
                    PreparedStatement ps = conex.prepareStatement("insert into multa(pr_multa, sd_multa, id_cliente, id_prestamo) values (?,?,?,?)");
                    ps.setString(1, "S/. " + days * 10);
                    ps.setInt(2, 0);
                    ps.setInt(3, cm.getIdCliente());
                    ps.setInt(4, cm.getIdPrestamo());
                    int resultado = ps.executeUpdate();

                    Alert alert;
                    if (resultado > 0) {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Info");
                        alert.setContentText("multa se ha creado correctamente.");
                    } else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("Error");
                        alert.setContentText("Error al crear multa");
                    }
                    alert.showAndWait();
                    this.clienteMultas.clear();
                    traerDatosClienteMultas();
                    db.closeConnection();
                } else {
                    Alert alert;
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Info");
                    alert.setContentText("Aun no vence la fecha de prestamo");
                    alert.showAndWait();
                }
            } else {
                LocalDate ultimaFechaPrestamo = cm.getFfPrestamo();
                LocalDate fechaHoy = LocalDate.now();

                long days = fechaHoy.toEpochDay() - ultimaFechaPrestamo.toEpochDay();
                String pagar = String.valueOf(days*10);

                Alert alert;
                Connection conex = db.openConnection();
                PreparedStatement ps = conex.prepareStatement("update multa set pr_multa=? where id_multa=?");
                ps.setString(1, "S/. " + pagar);
                ps.setInt(2, cm.getIdMulta());

                int resultado = ps.executeUpdate();

                if (resultado > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    System.out.println(alert);
                    alert.setHeaderText(null);
                    alert.setTitle("Info");
                    alert.setContentText("Multa actualizado correctamente.");
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    System.out.println(alert);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("Error al actualizar multa");
                }
                alert.showAndWait();

                db.closeConnection();

                this.clienteMultas.clear();
                traerDatosClienteMultas();

            }
        }
    }

    private void traerDatosClienteMultas() throws SQLException {
        Connection conex = db.openConnection();
        PreparedStatement ps = conex.prepareStatement("select ifNull(m.id_multa, 900) as id_multa, p.id_prestamo, c.id_cliente, c.no_cliente, c.dni_cliente, d.no_documento, p.fe_prestamo, p.ff_prestamo, p.is_activo, m.pr_multa, m.sd_multa\n" +
                "from prestamo as p left join cliente as c on c.id_cliente = p.id_cliente \n" +
                "left join multa as m on m.id_prestamo= p.id_prestamo \n" +
                "join documento as d on p.id_documento = d.id_documento where p.is_activo = 0 or m.sd_multa = 0");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ClienteMulta newClienteMulta = new ClienteMulta(rs.getInt("id_multa"), rs.getInt("id_prestamo"), rs.getInt("id_cliente"), rs.getString("no_cliente"), rs.getString("dni_cliente"), rs.getString("no_documento"), rs.getDate("fe_prestamo").toLocalDate(), rs.getDate("ff_prestamo").toLocalDate(), rs.getInt("is_activo"), rs.getString("pr_multa"), rs.getInt("sd_multa"));
            this.clienteMultas.add(newClienteMulta);
            this.tbl_multa_cliente.refresh();
        }
    }

    public void BuscarMultaCliente(KeyEvent keyEvent) {
        String filtrador = this.tc_busca_multa.getText();
        if (filtrador.isEmpty()) {
            this.tbl_multa_cliente.setItems(clienteMultas);
        } else {
            this.clienteMultasFilter.clear();
            for (ClienteMulta cm : clienteMultas) {
                if (cm.getNoCliente().toLowerCase().contains(filtrador.toLowerCase())) {
                    this.clienteMultasFilter.add(cm);
                }
            }
            this.tbl_multa_cliente.setItems(clienteMultasFilter);
            this.tbl_multa_cliente.refresh();
        }
    }

    public void CancelacionPrestamo(ActionEvent actionEvent) throws SQLException {
        ClienteMulta cm = this.tbl_multa_cliente.getSelectionModel().getSelectedItem();
        if (cm == null) {
            System.out.println("Error");
            System.out.println("Debes seleccionar a un cliente.");
        } else {
            LocalDate ultimaFechaPrestamo = cm.getFfPrestamo();
            LocalDate fechaHoy = LocalDate.now();

            Long days = fechaHoy.toEpochDay() - ultimaFechaPrestamo.toEpochDay();
            String pagar = String.valueOf(days*10);

            Alert alert;
            Connection conex = db.openConnection();
            PreparedStatement ps = conex.prepareStatement("update prestamo set is_activo=? where id_prestamo=?");
            ps.setString(1, "1");
            ps.setInt(2, cm.getIdPrestamo());

            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                System.out.println(alert);
                alert.setHeaderText(null);
                alert.setTitle("Info");
                alert.setContentText("Prestamo devuelto correctamente.");
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                System.out.println(alert);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Error al devolver prestamo");
            }
            alert.showAndWait();

            db.closeConnection();

            this.clienteMultas.clear();
            traerDatosClienteMultas();
        }
    }

    public void CancelacionMulta(ActionEvent actionEvent) throws SQLException {
        ClienteMulta cm = this.tbl_multa_cliente.getSelectionModel().getSelectedItem();
        if (cm == null) {
            System.out.println("Error");
            System.out.println("Debes seleccionar a un cliente.");
        } else {
            if (cm.getIdMulta() == 900){
                System.out.println("No tienes multa");
            } else {
                LocalDate ultimaFechaPrestamo = cm.getFfPrestamo();
                LocalDate fechaHoy = LocalDate.now();

                Long days = fechaHoy.toEpochDay() - ultimaFechaPrestamo.toEpochDay();
                String pagar = String.valueOf(days*10);

                Alert alert;
                Connection conex = db.openConnection();
                PreparedStatement ps = conex.prepareStatement("update multa set sd_multa=? where id_multa=?");
                ps.setString(1, "1");
                ps.setInt(2, cm.getIdMulta());

                int resultado = ps.executeUpdate();

                if (resultado > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    System.out.println(alert);
                    alert.setHeaderText(null);
                    alert.setTitle("Info");
                    alert.setContentText("Multa pagada correctamente.");
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    System.out.println(alert);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("Error al pagar multa");
                }
                alert.showAndWait();

                db.closeConnection();

                this.clienteMultas.clear();
                traerDatosClienteMultas();

            }
        }
    }

}

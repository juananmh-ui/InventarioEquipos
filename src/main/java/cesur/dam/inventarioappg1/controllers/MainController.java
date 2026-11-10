package cesur.dam.inventarioappg1.controllers;

import cesur.dam.inventarioappg1.SceneNavigator;
import cesur.dam.inventarioappg1.data.DataSourceFactory;
import cesur.dam.inventarioappg1.data.EquipoJdbcDAO;
import cesur.dam.inventarioappg1.models.Equipo;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TableColumn<Equipo,String> cAvailability;
    @FXML
    private TableView<Equipo> table;
    @FXML
    private TableColumn<Equipo,String> cCategory;
    @FXML
    private TableColumn<Equipo,String> cName;
    @FXML
    private TableColumn<Equipo,String> cId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /* Inicialización de la tabla */
        cId.setCellValueFactory( (row)->{
            Equipo equipment = row.getValue();
            Long  id = equipment.getId();
            return new SimpleStringProperty(String.valueOf(id));
        });
        cName.setCellValueFactory( (row)->{
            if(row.getValue().getNombre().isBlank()){
                return new SimpleStringProperty("--");
            } else  {
                return new SimpleStringProperty(String.valueOf(row.getValue().getNombre()));
            }
        });
        cCategory.setCellValueFactory( (row)->{
            return new SimpleStringProperty(String.valueOf(row.getValue().getCategoria()).toUpperCase());
        });
        cAvailability.setCellValueFactory( (row)->{
            var availability = row.getValue().getDisponible();
            String output = availability? "disponible":"no disponible";
            return new SimpleStringProperty(output);
        });

        try {
            var dao = new EquipoJdbcDAO(DataSourceFactory.getDataSource());
            table.getItems().setAll(dao.findAll());
        } catch (RuntimeException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText("No se pudo cargar el inventario");
            alerta.setContentText("Comprueba MySQL y las variables USER y PASSWORD.");
            System.err.println(e.getMessage());
            alerta.showAndWait();
        }
    }

    @FXML
    public void goBack(ActionEvent actionEvent) {
        SceneNavigator.navigateTo("login-view.fxml", "Login");
    }

    @FXML
    public void addNew(ActionEvent actionEvent) {
        SceneNavigator.navigateTo("create-view.fxml", "Ficha de equipo");
    }
}

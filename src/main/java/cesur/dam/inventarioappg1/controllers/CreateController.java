package cesur.dam.inventarioappg1.controllers;

import cesur.dam.inventarioappg1.SceneNavigator;
import cesur.dam.inventarioappg1.data.DataSourceFactory;
import cesur.dam.inventarioappg1.data.EquipoJdbcDAO;
import cesur.dam.inventarioappg1.models.Equipo;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

import java.util.List;

public class CreateController {
    static final List<String> CATEGORIAS = List.of(
            "Impresora", "NAS", "PC Sobremesa", "Portátil", "Proyector",
            "Router", "Switch", "Tablet", "Videojuego", "Webcam"
    );

    private static final int MAX_NOMBRE = 60;

    @FXML private TextField txtName;
    @FXML private ChoiceBox<String> choiceCategory;
    @FXML private CheckBox chkAvailable;
    @FXML private Spinner<Integer> spinnerId;

    @FXML
    private void initialize() {
        spinnerId.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000));
        spinnerId.setEditable(false);
        spinnerId.setDisable(true);
        spinnerId.setTooltip(new Tooltip("MySQL asigna el ID al guardar."));
        choiceCategory.getItems().setAll(CATEGORIAS);
    }

    @FXML
    public void save() {
        String nombre = txtName.getText().strip();
        if (nombre.isEmpty() || nombre.codePointCount(0, nombre.length()) > MAX_NOMBRE) {
            avisar(Alert.AlertType.WARNING, "Introduce un nombre de entre 1 y 60 caracteres.");
            txtName.requestFocus();
            return;
        }

        String categoria = choiceCategory.getValue();
        if (categoria == null) {
            avisar(Alert.AlertType.WARNING, "Selecciona la categoría del equipo.");
            choiceCategory.requestFocus();
            return;
        }

        Equipo equipo = new Equipo();
        equipo.setNombre(nombre);
        equipo.setCategoria(categoria);
        equipo.setDisponible(chkAvailable.isSelected());

        try {
            var dao = new EquipoJdbcDAO(DataSourceFactory.getDataSource());
            dao.save(equipo);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            avisar(Alert.AlertType.ERROR, "No se pudo guardar el equipo. Comprueba la conexión y los datos.");
            return;
        }
        goBack();
    }

    @FXML
    public void goBack() {
        SceneNavigator.navigateTo("main-view.fxml", "Main");
    }

    private void avisar(Alert.AlertType tipo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.initOwner(txtName.getScene().getWindow());
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}

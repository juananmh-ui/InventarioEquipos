package cesur.dam.inventarioappg1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Utility class for navigating between FXML views in the application.
 * <p>
 * It holds a single shared {@link Stage} reference and swaps the scene shown on it
 * whenever a new view is requested. All members are static, so the navigator can be
 * used from anywhere without needing an instance.
 */
public class SceneNavigator {

    /**
     * The main application stage on which scenes are displayed.
     */
    private static Stage stage;

    /**
     * Registers the stage that will be used for subsequent scene navigation.
     *
     * @param stage the main application {@link Stage}
     */
    public static void setStage(Stage stage) {
        SceneNavigator.stage = stage;
    }

    /**
     * Loads the given FXML view and displays it on the registered stage.
     * <p>
     * The FXML file is resolved relative to the {@code views/} package of {@link AppG1}.
     *
     * @param fxml  file name of the FXML view to load (resolved under {@code views/})
     * @param title window title to set on the stage
     * @throws RuntimeException if the FXML resource fails to load (wraps the {@link IOException})
     */
    public static void navigateTo(String fxml,String title){
        FXMLLoader fxmlLoader = new FXMLLoader(AppG1.class.getResource("views/"+fxml));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
            stage.setTitle(title);
            stage.setScene(scene);
            stage.sizeToScene();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

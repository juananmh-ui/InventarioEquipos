package cesur.dam.inventarioappg1;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class AppG1 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneNavigator.setStage(stage);
        SceneNavigator.navigateTo("login-view.fxml","Login");
        stage.show();
    }
}

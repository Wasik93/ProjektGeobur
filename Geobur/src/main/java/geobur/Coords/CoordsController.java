package geobur.Coords;

import geobur.Menu.MainMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CoordsController {
    private Stage stage;
    private Scene scene;
    @FXML
    protected void goBackButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource("MainMenu.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,800,600);
        boolean sFS = false;
        if(stage.isFullScreen()){
            sFS = true;
        };
        stage.setTitle("Geobur - Main Menu");
        stage.setScene(scene);
        stage.setFullScreen(sFS);
        stage.show();
    }
}

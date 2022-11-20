package geobur.Menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane anchorPaneLeft;

    @FXML
    private AnchorPane anchorPaneRight;


    @FXML
    void initialize(){
        Image image = new Image("function.png");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
        anchorPaneLeft.setBackground(new Background(backgroundImage));
    }
    @FXML
    protected void FunctionButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource("functions.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,800,600);
        boolean sFS = stage.isFullScreen();
        stage.setScene(scene);
        stage.setTitle("Geobur - Functions");
        stage.setFullScreen(sFS);
        stage.show();
    }

    @FXML
    protected void CoordsButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource("coords.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,800,600);
        boolean sFS = stage.isFullScreen();
        stage.setScene(scene);
        stage.setTitle("Geobur - Coords");
        stage.setFullScreen(sFS);
        stage.show();
    }

    @FXML
    protected void FullScreenButtonClick(ActionEvent event){
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setFullScreen(true);
    }
}
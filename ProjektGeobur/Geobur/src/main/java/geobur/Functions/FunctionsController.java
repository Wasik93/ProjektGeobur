package geobur.Functions;

import geobur.Handlers.ZoomHandler;
import geobur.Menu.MainMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class FunctionsController {
    private Stage stage;
    private Scene scene;

    String functionName = "x";

    @FXML
    private TextField textField;

    @FXML
    private StackPane stackPane;

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
        stage.setScene(scene);
        stage.setTitle("Geobur - Main Menu");
        stage.setFullScreen(sFS);
        stage.show();
    }

    @FXML
    protected void generateNewFunction(ActionEvent event){

        try {
            Functions f = new Functions(textField.getText());
            stackPane.getChildren().clear();
            stackPane.getChildren().add(f.plotChart(1));
            stackPane.toFront();
            ZoomHandler zoomHandler = f.newZoomHandler(textField.getText());
            stackPane.setOnScroll(zoomHandler);
            functionName = textField.getText();
        }
        catch (Exception e){

            Image image = new Image("sadge.png");
            ImageView imageView = new ImageView(image);
            stackPane.getChildren().add(imageView);
            stackPane.toFront();
            Alert alert = new Alert(Alert.AlertType.WARNING, "Bad Function: " + textField.getText(), ButtonType.OK);
            alert.showAndWait();

            Functions f = new Functions(functionName);
            stackPane.getChildren().clear();
            stackPane.getChildren().add(f.plotChart(1));
            stackPane.toFront();
            ZoomHandler zoomHandler = f.newZoomHandler(functionName);
            stackPane.setOnScroll(zoomHandler);
        }
    }

    @FXML
    void initialize(){
        Functions f = new Functions(functionName);
        stackPane.getChildren().add(f.plotChart(1));
        stackPane.toFront();
        ZoomHandler zoomHandler = f.newZoomHandler(functionName);
        stackPane.setOnScroll(zoomHandler);
    }
}

package geobur.Coords;

import geobur.Menu.MainMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class CoordsController {
    private Stage stage;
    private Scene scene;
    private Coords c;

    public Boolean[] handlers = new Boolean[10];

    @FXML
    private Canvas canvas;
     @FXML
    void initialize(){
        c = new Coords();
        c.setCc(this);
        c.createCanvasGrid(800,482);
        handlers[0] = false;
        handlers[1] = false;
        handlers[2] = false;
        handlers[3] = false;
        handlers[4] = false;
        handlers[5] = false;
        handlers[6] = false;
        handlers[7] = false;
        handlers[8] = false;
    }

    @FXML
    protected void clearButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource("coords.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,800,600);
        boolean sFS = false;
        if(stage.isFullScreen()){
            sFS = true;
        };
        stage.setScene(scene);
        stage.setTitle("Geobur - Coords");
        stage.setFullScreen(sFS);
        stage.show();
    }
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

    @FXML
    protected void clickButton0(MouseEvent event){
        c.removeEventHandlers(handlers);
        handlers[0] = true;
        c.weCanDrawNow();
    }
    @FXML
    protected void clickButton1(MouseEvent event){
        c.removeEventHandlers(handlers);
        handlers[1] = true;
        c.addPoint();

    }
    @FXML
    protected void clickButton2(MouseEvent event){
        c.removeEventHandlers(handlers);
        handlers[2] = true;
        c.addLine();
    }
    @FXML
    protected void clickButton3(MouseEvent event){
        c.removeEventHandlers(handlers);
        handlers[3] = true;
        c.calculateAngle();
    }
    @FXML
    protected void clickButton4(MouseEvent event){
        c.removeEventHandlers(handlers);
        handlers[4] = true;
        c.calculateDistance();
    }
    @FXML
    protected void clickButton5(MouseEvent event){
        c.removeEventHandlers(handlers);
        handlers[5] = true;
        c.addCircle();
    }
    @FXML
    protected void clickButton6(MouseEvent event){
        c.removeEventHandlers(handlers);
        handlers[6] = true;
    }
    @FXML
    protected void clickButton7(MouseEvent event){
        c.removeEventHandlers(handlers);
        handlers[7] = true;
    }
    @FXML
    protected void clickButton8(MouseEvent event){
        c.removeEventHandlers(handlers);
        handlers[8] = true;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}

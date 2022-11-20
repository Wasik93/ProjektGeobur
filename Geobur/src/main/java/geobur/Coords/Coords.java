package geobur.Coords;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


import java.text.DecimalFormat;

public class Coords {

    private static final DecimalFormat df = new DecimalFormat("0.00");
    boolean clicked_once = false;

    Integer status = 0;
    Double[][] P = new Double[3][2]; //for angle calculation

    private Canvas canvas;

    Double x;

    Double y;
    private GraphicsContext graphicsContext;

    public void setCc(CoordsController cc) {
        this.canvas = cc.getCanvas();
        graphicsContext = cc.getCanvas().getGraphicsContext2D();
    }

    public void removeEventHandlers(Boolean[] array){
        clicked_once = false;
        status = 0;
        if(array[0]) {
            canvas.removeEventHandler(MouseEvent.MOUSE_PRESSED, DrawHandler1);
            canvas.removeEventHandler(MouseEvent.MOUSE_DRAGGED, DrawHandler2);
            canvas.removeEventHandler(MouseEvent.MOUSE_RELEASED,DrawHandler3);
            array[0] = false;
        }
        if(array[1]){
            canvas.removeEventHandler(MouseEvent.MOUSE_CLICKED, PointHandler);
            array[1] = false;
        }
        if(array[2]){
            canvas.removeEventHandler(MouseEvent.MOUSE_CLICKED,LineHandler);
            array[2] = false;
        }
        if(array[3]){
            canvas.removeEventHandler(MouseEvent.MOUSE_CLICKED,AngleHandler);
            array[3] = false;
        }
        if(array[4]){
            canvas.removeEventHandler(MouseEvent.MOUSE_CLICKED,DistanceHandler);
            array[4] = false;
        }
        if(array[5]){
            canvas.removeEventHandler(MouseEvent.MOUSE_CLICKED,CircleHandler);
            array[5] = false;
        }
        if(array[6]){
            array[6] = false;
        }
        if(array[7]){
            array[7] = false;
        }
        if(array[8]){
            array[8] = false;
        }
    }
    public void createCanvasGrid(int width, int height){
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setLineWidth(1);
        graphicsContext.setStroke(Color.rgb(255,177,177));
        for (double x = 0.0; x < width; x+=10) {
            graphicsContext.moveTo(x, 0);
            graphicsContext.lineTo(x, height);
            graphicsContext.stroke();
        }
        for (double y = 0.0; y < height; y+=10) {
            graphicsContext.moveTo(0, y);
            graphicsContext.lineTo(width, y);
            graphicsContext.stroke();
        }
    }

    public void weCanDrawNow(){
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, DrawHandler1);
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, DrawHandler2);
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, DrawHandler3);
    }
    EventHandler<MouseEvent> DrawHandler1 = new EventHandler<>() {
        @Override
        public void handle(MouseEvent event) {
            graphicsContext.setStroke(Color.BLACK);
            graphicsContext.beginPath();
            graphicsContext.moveTo(event.getX(), event.getY());
            graphicsContext.stroke();
        }
    };
    EventHandler<MouseEvent> DrawHandler2 = new EventHandler<>() {
        @Override
        public void handle(MouseEvent event) {
            graphicsContext.setLineWidth(2.0);
            graphicsContext.lineTo(event.getX(), event.getY());
            graphicsContext.stroke();
        }
    };
    EventHandler<MouseEvent> DrawHandler3 = event -> {
    };

    public void addPoint(){
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED,PointHandler);
    }
    EventHandler<MouseEvent> PointHandler = new EventHandler<>() {
        @Override
        public void handle(MouseEvent event) {
            graphicsContext.setFill(Color.BLACK);
            graphicsContext.beginPath();
            graphicsContext.setStroke(Color.BLACK);
            graphicsContext.fillOval(event.getX(), event.getY(), 5, 5);
            graphicsContext.fill();
            graphicsContext.stroke();
        }
    };

    public void addLine(){
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED,LineHandler);
    }

    EventHandler<MouseEvent> LineHandler = new EventHandler<>() {
        @Override
        public void handle(MouseEvent event) {
            if (!clicked_once) {
                clicked_once = true;
                x = event.getX();
                y = event.getY();
            } else {
                clicked_once = false;
                graphicsContext.beginPath();
                graphicsContext.moveTo(event.getX(), event.getY());
                graphicsContext.lineTo(x, y);
                graphicsContext.setStroke(Color.BLACK);
                graphicsContext.setFill(Color.BLACK);
                graphicsContext.stroke();
            }
        }
    };

    public void calculateAngle(){
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED,AngleHandler);
    }
    EventHandler<MouseEvent> AngleHandler = event -> {
        if (status == 0) {
            status = 1;
            P[1][0] = event.getX();
            P[1][1] = event.getY();
        } else if (status == 1) {
            status = 2;
            P[0][0] = event.getX();
            P[0][1] = event.getY();
        } else {
            status = 0;
            P[2][0] = event.getX();
            P[2][1] = event.getY();
            Double numerator = P[1][1] * (P[0][0] - P[2][0]) + P[0][1] * (P[2][0] - P[1][0]) + P[2][1] * (P[1][0] - P[0][0]);
            Double denominator = (P[1][0] - P[0][0]) * (P[0][0] - P[2][0]) + (P[1][1] - P[0][1]) * (P[0][1] - P[2][1]);
            Double ratio = numerator / denominator;
            Double angleRad = Math.atan(ratio);
            Double angleDeg = (angleRad * 180) / Math.PI;
            if (angleDeg < 0) {
                angleDeg += 180;
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Angle between: A = (" +
                    df.format(P[0][0]) + "," + df.format(P[0][1]) + ") ;\n B = (" +
                    df.format(P[1][0]) + "," + df.format(P[1][1]) + ") ;\n C = (" +
                    df.format(P[2][0]) + "," + df.format(P[2][1]) + ") is\n" +
                    df.format(angleDeg) + "\u00B0"
                    , ButtonType.FINISH);
            alert.showAndWait();
        }
    };
    
    public void calculateDistance(){
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, DistanceHandler);
    }
    
    EventHandler<MouseEvent> DistanceHandler = new EventHandler<>() {
        @Override
        public void handle(MouseEvent event) {
            if (status == 0) {
                status = 1;
                x = event.getX();
                y = event.getY();
            } else {
                status = 0;
                Double distance = Math.sqrt((event.getX()-x)*(event.getX()-x)+(event.getY()-y)*(event.getY()-y));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Distance between: A = (" +
                        df.format(x) + "," + df.format(y) + ") ;\n B = (" +
                        df.format(event.getX()) + "," + df.format(event.getY()) + ") is\n" +
                        df.format(distance),
                ButtonType.FINISH);
                alert.showAndWait();

            }
        }
    };

    public void addCircle(){
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, CircleHandler);
    }

    EventHandler<MouseEvent> CircleHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            graphicsContext.setFill(Color.TRANSPARENT);
            graphicsContext.setLineWidth(2);
            graphicsContext.beginPath();
            graphicsContext.setStroke(Color.BLACK);
            graphicsContext.strokeOval(event.getX(), event.getY(), 50, 50);
            graphicsContext.stroke();
        }
    };
    
    
}

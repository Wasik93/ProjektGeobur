package geobur.Handlers;

import geobur.Utils.Plot;
import geobur.Functions.*;
import javafx.event.EventHandler;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;

public class ZoomHandler implements EventHandler<ScrollEvent> {
    private static final double MAX_ZOOM = 2;
    private static final double MIN_ZOOM = 0.5;

    private String functionName = "x";

    private double zoomFactor = 1;

    public ZoomHandler(){
        functionName = "x";
    }

    public ZoomHandler(String F){
        functionName = F;
    }


    @Override
    public void handle(ScrollEvent event) {
        if (event.getDeltaY() == 0) {
            return;
        } else if (event.getDeltaY() < 0) {
            zoomFactor = Math.max(MIN_ZOOM, zoomFactor * 0.9);
        } else if (event.getDeltaY() > 0) {
            zoomFactor = Math.min(MAX_ZOOM, zoomFactor * 1.1);
        }
        Functions f = new Functions(functionName);
        Plot plot = f.plotChart(zoomFactor);

        Pane parent = (Pane) event.getSource();
        parent.getChildren().setAll(plot);
    }
}
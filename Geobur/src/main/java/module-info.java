module geobur {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.scripting;

    exports geobur.Parser;
    opens geobur.Parser to javafx.fxml;
    exports geobur.Menu;
    opens geobur.Menu to javafx.fxml;
    exports geobur.Coords;
    opens geobur.Coords to javafx.fxml;
    exports geobur.Functions;
    opens geobur.Functions to javafx.fxml;
    exports geobur.Handlers;
    opens geobur.Handlers to javafx.fxml;
    exports geobur.Utils;
    opens geobur.Utils to javafx.fxml;
}
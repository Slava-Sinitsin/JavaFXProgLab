package Lab1Package;

import javafx.scene.control.Alert;

public class ErrorDialogWindow {
    public static void showErrorWindow() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Incorrect input. Enter an integer");
        alert.setHeaderText("");
        alert.showAndWait();
    }
}

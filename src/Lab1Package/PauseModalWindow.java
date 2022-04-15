package Lab1Package;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PauseModalWindow {
    Stage window = new Stage();
    Group group;
    Scene scene;
    Button resumeButton;
    Button stopButton;
    TextArea textForAuto;
    TextArea textForMoto;
    TextArea simTime;

    PauseModalWindow() {
        resumeButton = new Button("Resume");
        resumeButton.setLayoutX(10);
        resumeButton.setLayoutY(220);
        resumeButton.setFocusTraversable(false);
        stopButton = new Button("Stop");
        stopButton.setLayoutX(80);
        stopButton.setLayoutY(220);
        stopButton.setFocusTraversable(false);
        simTime = new TextArea("Time of simulation: 0");
        simTime.setLayoutX(10);
        simTime.setLayoutY(10);
        simTime.setMaxSize(250, 20);
        simTime.setFont(new Font("Arial", 20.0D));
        simTime.setEditable(false);
        simTime.setFocusTraversable(false);
        textForAuto = new TextArea("Value of auto: 0");
        textForAuto.setLayoutX(10);
        textForAuto.setLayoutY(80);
        textForAuto.setMaxSize(250, 20);
        textForAuto.setFont(new Font("Arial", 20.0D));
        textForAuto.setEditable(false);
        textForAuto.setFocusTraversable(false);
        textForMoto = new TextArea("Value of moto: 0");
        textForMoto.setLayoutX(10);
        textForMoto.setLayoutY(150);
        textForMoto.setMaxSize(250, 20);
        textForMoto.setFont(new Font("Arial", 20.0D));
        textForMoto.setEditable(false);
        textForMoto.setFocusTraversable(false);
    }

    public void newWindow() {
        window.initModality(Modality.APPLICATION_MODAL);
        group = new Group(resumeButton, stopButton, simTime, textForAuto, textForMoto);
        scene = new Scene(group, 300, 250);
        window.setScene(scene);
        window.setTitle("Info");
    }
}

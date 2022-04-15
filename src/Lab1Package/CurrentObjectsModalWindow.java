package Lab1Package;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.TreeMap;

public class CurrentObjectsModalWindow {
    Stage window = new Stage();
    Group group;
    Scene scene;
    TreeMap<Integer, Integer> transportTreeMap;
    TextArea textArea;

    public void newWindow() {
        window.initModality(Modality.APPLICATION_MODAL);
        transportTreeMap = new TreeMap<>();
        textArea = new TextArea();
        textArea.setMaxSize(450, 250);
        textArea.setFont(new Font("Arial", 20.0D));
        textArea.setEditable(false);
        textArea.setFocusTraversable(false);
        group = new Group(textArea);
        scene = new Scene(group, 450, 250);
        window.setScene(scene);
        window.setTitle("Current objects");
    }

    public void show(ArrayList<Transport> transportArr, TreeMap<Transport, Integer> transportTreeMap) {
        StringBuilder str = new StringBuilder();
        for (Transport transport : transportArr) {
            str.append("Type: ").append(transport.getClass().getSimpleName()).append("\tID: ").append(transport.hashCode()).append("      \tTime: ").append(transportTreeMap.get(transport)).append("s\n");
        }
        textArea.setText(str.toString());
    }
}
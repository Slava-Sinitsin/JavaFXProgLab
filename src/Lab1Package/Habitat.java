package Lab1Package;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeMap;


public class Habitat {
    int wd = 1280;
    int hg = 720;
    final ArrayList<Transport> transportArr;
    int n1 = 1, p1 = 100, n2 = 1, p2 = 100;
    int valueOfAuto = 0, valueOfMoto = 0;
    Scene scene;
    Group root;
    Label textForAuto, textForMoto;
    Label simTimeLabel, timerLabel;
    Line line1, line2;
    Button startButton, pauseButton;
    CheckBox infoCheckBox;
    RadioButton yesButton, noButton;
    Label showTimeLabel;
    ComboBox<Integer> autoProb, motoProb;
    ObservableList<Integer> probList;
    Label probAutoLabel, probMotoLabel;
    TextField periodOfAuto, periodOfMoto;
    Label PeriodOfAutoLabel, PeriodOfMotoLabel;
    TextField autoLifeTimeField, motoLifeTimeField;
    Label autoLifeTimeLabel, motoLifeTimeLabel;
    ToolBar toolBar;
    Button toolBarStartButton, toolBarPauseButton;
    CheckBox toolBarInfoCheckBox;
    ToggleGroup toggleGroup;
    HashSet<Integer> transportHashSet;
    TreeMap<Transport, Integer> transportTreeMap;
    int autoLifeTime, motoLifeTime;
    Button currentObjectsButton;
    ObservableList<Integer> threadPriorityList;
    ComboBox<Integer> autoThreadPriority, motoThreadPriority;
    Label autoThreadPriorityLabel, motoThreadPriorityLabel;
    Button autoThreadButton, motoThreadButton;
    final AutoAI autoAI;
    final MotoAI motoAI;

    Habitat() {
        transportArr = new ArrayList<>();
        textForAuto = new Label("Value of auto: 0");
        textForAuto.setLayoutX(10.0D);
        textForAuto.setLayoutY(70.0D);
        textForAuto.setFont(new Font("Arial", 20.0D));
        textForAuto.setTextFill(Color.web("#000000"));
        textForAuto.setVisible(false);
        textForMoto = new Label("Value of moto: 0");
        textForMoto.setLayoutX(10.0D);
        textForMoto.setLayoutY(100.0D);
        textForMoto.setFont(new Font("Arial", 20.0D));
        textForMoto.setTextFill(Color.web("#000000"));
        textForMoto.setVisible(false);
        simTimeLabel = new Label("Simulation time: 0");
        simTimeLabel.setLayoutX(10.0D);
        simTimeLabel.setLayoutY(40.0D);
        simTimeLabel.setFont(new Font("Arial", 20.0D));
        simTimeLabel.setTextFill(Color.web("#000000"));
        simTimeLabel.setVisible(false);
        timerLabel = new Label("Time: 0");
        timerLabel.setLayoutX(10);
        timerLabel.setLayoutY(690);
        timerLabel.setFont(new Font("Arial", 20.0D));
        timerLabel.setTextFill(Color.web("#000000"));
        timerLabel.setVisible(false);
        line1 = new Line(200, 0, 200, 720);
        line2 = new Line(0, 125, 200, 125);
        startButton = new Button("Start");
        startButton.setLayoutX(10);
        startButton.setLayoutY(135);
        startButton.setFocusTraversable(false);
        pauseButton = new Button("Pause");
        pauseButton.setLayoutX(65);
        pauseButton.setLayoutY(135);
        pauseButton.setDisable(true);
        pauseButton.setFocusTraversable(false);
        infoCheckBox = new CheckBox("Show info");
        infoCheckBox.setLayoutX(10);
        infoCheckBox.setLayoutY(170);
        infoCheckBox.setFocusTraversable(false);
        toggleGroup = new ToggleGroup();
        yesButton = new RadioButton("Yes");
        yesButton.setLayoutX(120);
        yesButton.setLayoutY(150);
        yesButton.setVisible(false);
        yesButton.setFocusTraversable(false);
        yesButton.setToggleGroup(toggleGroup);
        noButton = new RadioButton("No");
        noButton.setLayoutX(120);
        noButton.setLayoutY(170);
        noButton.setVisible(false);
        noButton.setFocusTraversable(false);
        noButton.setSelected(true);
        noButton.setToggleGroup(toggleGroup);
        showTimeLabel = new Label("Show time?");
        showTimeLabel.setLayoutX(120);
        showTimeLabel.setLayoutY(130);
        showTimeLabel.setVisible(false);
        probList = FXCollections.observableArrayList(100, 90, 80, 70, 60, 50, 40, 30, 20, 10);
        autoProb = new ComboBox<>(probList);
        autoProb.setLayoutX(10);
        autoProb.setLayoutY(280);
        autoProb.setValue(100);
        autoProb.setFocusTraversable(false);
        motoProb = new ComboBox<>(probList);
        motoProb.setLayoutX(80);
        motoProb.setLayoutY(280);
        motoProb.setValue(100);
        motoProb.setFocusTraversable(false);
        probAutoLabel = new Label("Auto prob:");
        probAutoLabel.setLayoutX(10);
        probAutoLabel.setLayoutY(260);
        probMotoLabel = new Label("Moto prob:");
        probMotoLabel.setLayoutX(80);
        probMotoLabel.setLayoutY(260);
        periodOfAuto = new TextField();
        periodOfAuto.setLayoutX(10);
        periodOfAuto.setLayoutY(220);
        periodOfAuto.setMaxSize(65, 20);
        periodOfAuto.setText("1");
        periodOfAuto.setFocusTraversable(false);
        periodOfMoto = new TextField();
        periodOfMoto.setLayoutX(80);
        periodOfMoto.setLayoutY(220);
        periodOfMoto.setMaxSize(65, 20);
        periodOfMoto.setText("1");
        periodOfMoto.setFocusTraversable(false);
        PeriodOfAutoLabel = new Label("Period auto:");
        PeriodOfAutoLabel.setLayoutX(10);
        PeriodOfAutoLabel.setLayoutY(200);
        PeriodOfMotoLabel = new Label("Period moto:");
        PeriodOfMotoLabel.setLayoutX(80);
        PeriodOfMotoLabel.setLayoutY(200);
        periodOfMoto.setFocusTraversable(false);
        autoLifeTimeLabel = new Label("Auto life time:");
        autoLifeTimeLabel.setLayoutX(10);
        autoLifeTimeLabel.setLayoutY(320);
        autoLifeTimeField = new TextField("5");
        autoLifeTimeField.setLayoutX(10);
        autoLifeTimeField.setLayoutY(340);
        autoLifeTimeField.setMaxSize(75, 20);
        autoLifeTimeField.setFocusTraversable(false);
        motoLifeTimeLabel = new Label("Moto life time:");
        motoLifeTimeLabel.setLayoutX(100);
        motoLifeTimeLabel.setLayoutY(320);
        motoLifeTimeField = new TextField("5");
        motoLifeTimeField.setLayoutX(100);
        motoLifeTimeField.setLayoutY(340);
        motoLifeTimeField.setMaxSize(75, 20);
        motoLifeTimeField.setFocusTraversable(false);
        toolBarStartButton = new Button("Start");
        toolBarStartButton.setLayoutX(10);
        toolBarStartButton.setLayoutY(135);
        toolBarStartButton.setFocusTraversable(false);
        toolBarPauseButton = new Button("Pause");
        toolBarPauseButton.setLayoutX(65);
        toolBarPauseButton.setLayoutY(135);
        toolBarPauseButton.setDisable(true);
        toolBarPauseButton.setFocusTraversable(false);
        toolBarInfoCheckBox = new CheckBox("Show info");
        toolBarInfoCheckBox.setLayoutX(10);
        toolBarInfoCheckBox.setLayoutY(170);
        toolBarInfoCheckBox.setFocusTraversable(false);
        toolBar = new ToolBar(toolBarStartButton, toolBarPauseButton, toolBarInfoCheckBox);
        toolBar.setMinSize(200, 30);
        transportHashSet = new HashSet<>();
        transportTreeMap = new TreeMap<>(Comparator.comparingInt(Object::hashCode));
        currentObjectsButton = new Button("Current objects");
        currentObjectsButton.setLayoutX(10);
        currentObjectsButton.setLayoutY(380);
        currentObjectsButton.setDisable(true);
        currentObjectsButton.setFocusTraversable(false);
        threadPriorityList = FXCollections.observableArrayList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
        autoThreadPriority = new ComboBox<>(threadPriorityList);
        autoThreadPriority.setLayoutX(10);
        autoThreadPriority.setLayoutY(440);
        autoThreadPriority.setValue(5);
        autoThreadPriority.setMinSize(70, 10);
        autoThreadPriority.setFocusTraversable(false);
        motoThreadPriority = new ComboBox<>(threadPriorityList);
        motoThreadPriority.setLayoutX(100);
        motoThreadPriority.setLayoutY(440);
        motoThreadPriority.setValue(5);
        motoThreadPriority.setMinSize(70, 10);
        motoThreadPriority.setFocusTraversable(false);
        autoThreadPriorityLabel = new Label("Auto priority:");
        autoThreadPriorityLabel.setLayoutX(10);
        autoThreadPriorityLabel.setLayoutY(420);
        motoThreadPriorityLabel = new Label("Moto priority:");
        motoThreadPriorityLabel.setLayoutX(100);
        motoThreadPriorityLabel.setLayoutY(420);
        autoThreadButton = new Button("Pause");
        autoThreadButton.setLayoutX(10);
        autoThreadButton.setLayoutY(470);
        autoThreadButton.setMinSize(70, 10);
        autoThreadButton.setFocusTraversable(false);
        autoThreadButton.setDisable(true);
        motoThreadButton = new Button("Pause");
        motoThreadButton.setLayoutX(100);
        motoThreadButton.setLayoutY(470);
        motoThreadButton.setMinSize(70, 10);
        motoThreadButton.setFocusTraversable(false);
        motoThreadButton.setDisable(true);
        autoAI = new AutoAI(transportArr);
        motoAI = new MotoAI(transportArr);
        autoAI.setPriority(autoThreadPriority.getValue());
        motoAI.setPriority(motoThreadPriority.getValue());
        autoAI.setName("AutoAI");
        motoAI.setName("MotoAI");
        autoAI.start();
        motoAI.start();
    }

    public void update(int timeLine) {
        n1 = Integer.parseInt(periodOfAuto.getText());
        p1 = autoProb.getValue();
        n2 = Integer.parseInt(periodOfMoto.getText());
        p2 = motoProb.getValue();
        autoLifeTime = Integer.parseInt(autoLifeTimeField.getText());
        motoLifeTime = Integer.parseInt(motoLifeTimeField.getText());
        autoAI.setPriority(autoThreadPriority.getValue());
        motoAI.setPriority(motoThreadPriority.getValue());
        synchronized (transportArr) {
            if (timeLine % n1 == 0 && Math.random() * 100.0D <= (double) p1) {
                transportArr.add(new Auto());
                transportArr.get(transportArr.size() - 1).imageView = new ImageView(Auto.image);
                transportArr.get(transportArr.size() - 1).imageView.setLayoutX(220);
                transportArr.get(transportArr.size() - 1).imageView.setLayoutY(Math.random() * 640 + 10);
                root.getChildren().add(transportArr.get(transportArr.size() - 1).imageView);
                transportHashSet.add(transportArr.get(transportArr.size() - 1).hashCode());
                transportTreeMap.put(transportArr.get(transportArr.size() - 1), timeLine);
                valueOfAuto++;
            }
            if (timeLine % n2 == 0 && Math.random() * 100.0D <= (double) p2) {
                transportArr.add(new Moto());
                transportArr.get(transportArr.size() - 1).imageView = new ImageView(Moto.image);
                transportArr.get(transportArr.size() - 1).imageView.setLayoutX(Math.random() * 1000 + 240);
                transportArr.get(transportArr.size() - 1).imageView.setLayoutY(680);
                root.getChildren().add(transportArr.get(transportArr.size() - 1).imageView);
                transportHashSet.add(transportArr.get(transportArr.size() - 1).hashCode());
                transportTreeMap.put(transportArr.get(transportArr.size() - 1), timeLine);
                valueOfMoto++;
            }
            for (int i = 0; i < transportArr.size(); ++i) {
                if (transportArr.get(i) instanceof Auto && transportTreeMap.get(transportArr.get(i)) + autoLifeTime == timeLine) {
                    root.getChildren().remove(transportArr.get(i).imageView);
                    transportHashSet.remove(transportArr.get(i).hashCode());
                    transportTreeMap.remove(transportArr.get(i));
                    transportArr.remove(i);
                    valueOfAuto--;
                    i--;
                } else if (transportArr.get(i) instanceof Moto && transportTreeMap.get(transportArr.get(i)) + motoLifeTime == timeLine) {
                    root.getChildren().remove(transportArr.get(i).imageView);
                    transportHashSet.remove(transportArr.get(i).hashCode());
                    transportTreeMap.remove(transportArr.get(i));
                    transportArr.remove(i);
                    valueOfMoto--;
                    i--;
                }
            }
        }
    }
}
package Lab1Package;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


public class Test extends Application {
    Habitat habitat = new Habitat();
    PauseModalWindow pauseModalWindow = new PauseModalWindow();
    CurrentObjectsModalWindow currentObjectsModalWindow = new CurrentObjectsModalWindow();
    Timer timer;
    int timeLine = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group group1 = new Group(
                habitat.autoThreadButton,
                habitat.motoThreadButton,
                habitat.autoThreadPriorityLabel,
                habitat.motoThreadPriorityLabel,
                habitat.autoThreadPriority,
                habitat.motoThreadPriority,
                habitat.currentObjectsButton,
                habitat.toolBar,
                habitat.autoLifeTimeField,
                habitat.motoLifeTimeField,
                habitat.autoLifeTimeLabel,
                habitat.motoLifeTimeLabel,
                habitat.PeriodOfAutoLabel,
                habitat.PeriodOfMotoLabel,
                habitat.periodOfAuto,
                habitat.periodOfMoto,
                habitat.probAutoLabel,
                habitat.probMotoLabel,
                habitat.autoProb,
                habitat.motoProb,
                habitat.showTimeLabel,
                habitat.yesButton,
                habitat.noButton,
                habitat.infoCheckBox,
                habitat.startButton,
                habitat.pauseButton,
                habitat.line1,
                habitat.line2,
                habitat.textForMoto,
                habitat.textForAuto,
                habitat.simTimeLabel,
                habitat.timerLabel);
        Scene scene1 = new Scene(group1, habitat.wd, habitat.hg);
        habitat.scene = scene1;
        habitat.root = group1;
        primaryStage.setScene(scene1);
        pauseModalWindow.newWindow();
        currentObjectsModalWindow.newWindow();
        scene1.setOnKeyTyped(keyEvent -> {
            switch (keyEvent.getCharacter().toLowerCase(Locale.ROOT)) {
                case "b", "и" -> startGenerate();
                case "e", "у" -> pauseGenerate();
                case "t", "е" -> {
                    habitat.timerLabel.setVisible(!habitat.timerLabel.isVisible());
                    habitat.yesButton.setSelected(!habitat.yesButton.isSelected());
                    if (!habitat.yesButton.isSelected()) {
                        habitat.noButton.setSelected(true);
                    }
                }
                case "\u001B" -> System.exit(0);
                default -> System.out.println("Error " + keyEvent.getCharacter());
            }
        });
        primaryStage.show();
        primaryStage.setOnCloseRequest(windowEvent -> System.exit(0));
        habitat.startButton.setOnAction(actionEvent -> startGenerate());
        habitat.toolBarStartButton.setOnAction(actionEvent -> startGenerate());
        habitat.pauseButton.setOnAction(actionEvent -> pauseGenerate());
        habitat.toolBarPauseButton.setOnAction(actionEvent -> pauseGenerate());
        habitat.infoCheckBox.setOnAction(actionEvent -> {
            if (habitat.infoCheckBox.isSelected()) {
                habitat.toolBarInfoCheckBox.setSelected(true);
                habitat.simTimeLabel.setVisible(true);
                habitat.textForAuto.setVisible(true);
                habitat.textForMoto.setVisible(true);
            } else {
                habitat.toolBarInfoCheckBox.setSelected(false);
                habitat.simTimeLabel.setVisible(false);
                habitat.textForAuto.setVisible(false);
                habitat.textForMoto.setVisible(false);
            }
        });
        habitat.toolBarInfoCheckBox.setOnAction(actionEvent -> {
            if (habitat.toolBarInfoCheckBox.isSelected()) {
                habitat.infoCheckBox.setSelected(true);
                habitat.simTimeLabel.setVisible(true);
                habitat.textForAuto.setVisible(true);
                habitat.textForMoto.setVisible(true);
            } else {
                habitat.infoCheckBox.setSelected(false);
                habitat.simTimeLabel.setVisible(false);
                habitat.textForAuto.setVisible(false);
                habitat.textForMoto.setVisible(false);
            }
        });
        habitat.yesButton.setOnAction(actionEvent -> habitat.timerLabel.setVisible(true));
        habitat.noButton.setOnAction(actionEvent -> habitat.timerLabel.setVisible(false));
        pauseModalWindow.window.setOnCloseRequest(windowEvent -> stopGenerate());
        pauseModalWindow.stopButton.setOnAction(actionEvent -> {
            stopGenerate();
            pauseModalWindow.window.close();
        });
        pauseModalWindow.resumeButton.setOnAction(actionEvent -> {
            resumeGenerate();
            pauseModalWindow.window.close();
        });
        habitat.currentObjectsButton.setOnAction(actionEvent -> showCurrentObjects());
        currentObjectsModalWindow.window.setOnCloseRequest(windowEvent -> {
            habitat.currentObjectsButton.setDisable(false);
            if (!habitat.pauseButton.isDisable()) {
                resumeGenerate();
            }
        });
        habitat.autoThreadButton.setOnAction(actionEvent -> {
            synchronized (habitat.autoAI) {
                if (habitat.autoThreadButton.getText().equals("Pause")) {
                    habitat.autoThreadButton.setText("Resume");
                    habitat.autoAI.myWait();
                } else if (habitat.autoThreadButton.getText().equals("Resume")) {
                    habitat.autoThreadButton.setText("Pause");
                    habitat.autoAI.myNotify();
                }
            }
        });
        habitat.motoThreadButton.setOnAction(actionEvent -> {
            synchronized (habitat.motoAI) {
                if (habitat.motoThreadButton.getText().equals("Pause")) {
                    habitat.motoThreadButton.setText("Resume");
                    habitat.motoAI.myWait();
                } else if (habitat.motoThreadButton.getText().equals("Resume")) {
                    habitat.motoThreadButton.setText("Pause");
                    habitat.motoAI.myNotify();
                }
            }
        });
    }

    public void startGenerate() {
        try {
            timer = new Timer();
            Integer.parseInt(habitat.periodOfAuto.getText());
            Integer.parseInt(habitat.periodOfMoto.getText());
            Integer.parseInt(habitat.autoLifeTimeField.getText());
            Integer.parseInt(habitat.motoLifeTimeField.getText());
            habitat.autoAI.myNotify();
            habitat.motoAI.myNotify();
            habitat.valueOfAuto = 0;
            habitat.valueOfMoto = 0;
            habitat.autoThreadPriority.setDisable(true);
            habitat.motoThreadPriority.setDisable(true);
            habitat.motoThreadButton.setDisable(false);
            habitat.autoThreadButton.setDisable(false);
            habitat.periodOfAuto.setDisable(true);
            habitat.periodOfMoto.setDisable(true);
            habitat.autoProb.setDisable(true);
            habitat.motoProb.setDisable(true);
            habitat.infoCheckBox.setSelected(false);
            habitat.toolBarInfoCheckBox.setSelected(false);
            habitat.simTimeLabel.setText("Simulation time: 0");
            habitat.simTimeLabel.setVisible(false);
            habitat.textForAuto.setText("Value of auto: 0");
            habitat.textForAuto.setVisible(false);
            habitat.textForMoto.setText("Value of moto: 0");
            habitat.textForMoto.setVisible(false);
            for (int i = 0; i < habitat.transportArr.size(); ++i) {
                habitat.root.getChildren().remove(habitat.transportArr.get(i).imageView);
            }
            habitat.transportArr.clear();
            habitat.transportHashSet.clear();
            habitat.transportTreeMap.clear();
            habitat.timerLabel.setText("Time: 0");
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() ->
                    {
                        habitat.update(timeLine);
                        timeLine++;
                        habitat.timerLabel.setText("Time: " + timeLine);
                        habitat.simTimeLabel.setText("Simulation time: " + timeLine);
                        habitat.textForAuto.setText("Value of auto: " + habitat.valueOfAuto);
                        habitat.textForMoto.setText("Value of moto: " + habitat.valueOfMoto);
                    });
                }
            }, 1000, 1000);
            habitat.autoLifeTimeField.setDisable(true);
            habitat.motoLifeTimeField.setDisable(true);
            habitat.yesButton.setDisable(false);
            habitat.startButton.setDisable(true);
            habitat.toolBarStartButton.setDisable(true);
            habitat.pauseButton.setDisable(false);
            habitat.toolBarPauseButton.setDisable(false);
            habitat.yesButton.setVisible(true);
            habitat.noButton.setVisible(true);
            habitat.showTimeLabel.setVisible(true);
            habitat.currentObjectsButton.setDisable(false);
        } catch (NumberFormatException e) {
            habitat.periodOfAuto.setText("1");
            habitat.periodOfMoto.setText("1");
            habitat.autoLifeTimeField.setText("5");
            habitat.motoLifeTimeField.setText("5");
            ErrorDialogWindow.showErrorWindow();
        }
    }

    public void stopGenerate() {
        timer.cancel();
        timer = new Timer();
        habitat.autoAI.myWait();
        habitat.motoAI.myWait();
        habitat.autoThreadPriority.setDisable(false);
        habitat.motoThreadPriority.setDisable(false);
        habitat.autoThreadButton.setText("Pause");
        habitat.motoThreadButton.setText("Pause");
        habitat.motoThreadButton.setDisable(true);
        habitat.autoThreadButton.setDisable(true);
        habitat.periodOfAuto.setDisable(false);
        habitat.periodOfMoto.setDisable(false);
        habitat.autoProb.setDisable(false);
        habitat.motoProb.setDisable(false);
        habitat.pauseButton.setDisable(true);
        habitat.toolBarPauseButton.setDisable(true);
        habitat.showTimeLabel.setVisible(false);
        habitat.yesButton.setVisible(false);
        habitat.yesButton.setSelected(false);
        habitat.noButton.setVisible(false);
        habitat.noButton.setSelected(true);
        habitat.timerLabel.setVisible(false);
        habitat.autoLifeTimeField.setDisable(false);
        habitat.motoLifeTimeField.setDisable(false);
        habitat.periodOfAuto.setDisable(false);
        habitat.periodOfMoto.setDisable(false);
        habitat.autoProb.setDisable(false);
        habitat.motoProb.setDisable(false);
        habitat.startButton.setDisable(false);
        habitat.toolBarStartButton.setDisable(false);
        habitat.timerLabel.setText("Time: " + timeLine);
        habitat.simTimeLabel.setText("Simulation time: " + timeLine);
        habitat.textForAuto.setText("Value of auto: " + habitat.valueOfAuto);
        habitat.textForMoto.setText("Value of moto: " + habitat.valueOfMoto);
        habitat.simTimeLabel.setVisible(true);
        habitat.textForAuto.setVisible(true);
        habitat.textForMoto.setVisible(true);
        habitat.infoCheckBox.setSelected(true);
        habitat.toolBarInfoCheckBox.setSelected(true);
        timeLine = 0;
    }

    public void resumeGenerate() {
        habitat.autoAI.myNotify();
        habitat.motoAI.myNotify();
        habitat.startButton.setDisable(true);
        habitat.toolBarStartButton.setDisable(true);
        habitat.pauseButton.setDisable(false);
        habitat.toolBarPauseButton.setDisable(false);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() ->
                {
                    habitat.update(timeLine);
                    timeLine++;
                    habitat.timerLabel.setText("Time: " + timeLine);
                    habitat.simTimeLabel.setText("Simulation time: " + timeLine);
                    habitat.textForAuto.setText("Value of auto: " + habitat.valueOfAuto);
                    habitat.textForMoto.setText("Value of moto: " + habitat.valueOfMoto);
                });
            }
        }, 1000, 1000);
    }

    public void pauseGenerate() {
        timer.cancel();
        habitat.autoAI.myWait();
        habitat.motoAI.myWait();
        habitat.pauseButton.setDisable(true);
        habitat.toolBarPauseButton.setDisable(true);
        pauseModalWindow.simTime.setText("Time of simulation: " + timeLine);
        pauseModalWindow.textForAuto.setText("Value of auto: " + habitat.valueOfAuto);
        pauseModalWindow.textForMoto.setText("Value of moto: " + habitat.valueOfMoto);
        pauseModalWindow.window.showAndWait();
    }

    public void showCurrentObjects() {
        habitat.autoAI.myWait();
        habitat.motoAI.myWait();
        currentObjectsModalWindow.textArea.setFocusTraversable(false);
        habitat.currentObjectsButton.setDisable(true);
        timer.cancel();
        currentObjectsModalWindow.show(habitat.transportArr, habitat.transportTreeMap);
        currentObjectsModalWindow.window.showAndWait();
    }
}
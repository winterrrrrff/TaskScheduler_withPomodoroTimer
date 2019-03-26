package controller;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import model.Task;
//import pomodoro.PomodoroApp;
import ui.EditTask;
import ui.ListView;
import utility.JsonFileIO;
import utility.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static ui.PomoTodoApp.setScene;

// Controller class for Todobar UI
public class TodobarController implements Initializable {
    private static final String todoOptionsPopUpFXML = "resources/fxml/TodoOptionsPopUp.fxml";
    private static final String todoActionsPopUpFXML = "resources/fxml/TodoActionsPopUp.fxml";
    private File todoOptionPopUpFile = new File(todoOptionsPopUpFXML);
    private File todoActionPopUpFile = new File(todoActionsPopUpFXML);
    @FXML
    private Label descriptionLabel;
    @FXML
    private JFXHamburger todoActionsPopUpBurger;
    @FXML
    private StackPane todoActionsPopUpContainer;
    @FXML
    private JFXRippler todoOptionsPopUpRippler;
    @FXML
    private StackPane todoOptionsPopUpBurger;

    private Task task;
    private JFXPopup actionPopUp;
    private JFXPopup optionPopUp;


    // REQUIRES: task != null
    // MODIFIES: this
    // EFFECTS: sets the task in this Todobar
    //          updates the Todobar UI label to task's description
    public void setTask(Task task) {
        this.task = task;
        descriptionLabel.setText(task.getDescription());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadActionPopUp();
        loadTodoBarPopUpActionListener();
        loadOptionPopUp();
        loadViewOptionsPopUpActionListener();
    }

    private void loadActionPopUp() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(todoActionPopUpFile.toURI().toURL());
            fxmlLoader.setController(new ActionPopUpController());
            actionPopUp = new JFXPopup(fxmlLoader.load());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void loadOptionPopUp() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(todoOptionPopUpFile.toURI().toURL());
            fxmlLoader.setController(new OptionPopUpController());
            optionPopUp = new JFXPopup(fxmlLoader.load());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    // EFFECTS: show view selector pop up when its icon is clicked
    private void loadViewOptionsPopUpActionListener() {
        todoOptionsPopUpBurger.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                optionPopUp.show(todoOptionsPopUpBurger,
                        JFXPopup.PopupVPosition.TOP,
                        JFXPopup.PopupHPosition.LEFT,
                        12,
                        15);
            }
        });
    }

    // EFFECTS: show options pop up when its icon is clicked
    private void loadTodoBarPopUpActionListener() {
        todoActionsPopUpBurger.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                actionPopUp.show(todoActionsPopUpBurger,
                        JFXPopup.PopupVPosition.TOP,
                        JFXPopup.PopupHPosition.RIGHT,
                        -12,
                        15);
            }
        });
    }

    private class ActionPopUpController {
        @FXML
        private JFXListView<?> actionPopUpList;

        @FXML
        private void submit() {
            int selectedIndex = actionPopUpList.getSelectionModel().getSelectedIndex();
            switch (selectedIndex) {
                case 0:
                    Logger.log("ActionPopUpController", "The TODO action is not implemented");
                    break;
                case 1:
                    Logger.log("ActionPopUpController", "The UP-NEXT action is not implemented");
                    break;
                case 2:
                    Logger.log("ActionPopUpController","The IN-PROGRESS action is not implemented");
                    break;
                case 3:
                    Logger.log("ActionPopUpController","The DONE action is not implemented");
                    break;
                default:
//                    Logger.log("ActionPopUpController", "No action is implemented for the selected option");
//                    PomodoroApp.main(s);
                    openPomodoro();
            }
            actionPopUp.hide();
        }
    }

    private void openPomodoro() {
        Logger.log("ActionPopUpController", "No action is implemented for the selected option");
        String[] s = "sampleStringArray".split("");
        //PomodoroApp.main(s);
    }

    private class OptionPopUpController {
        @FXML
        private JFXListView<?> optionPopUpList;

        @FXML
        private void submit() {
            int selectedIndex = optionPopUpList.getSelectionModel().getSelectedIndex();
            switch (selectedIndex) {
                case 0:
                    Logger.log("OptionPopUpController", "The task has been edited");
                    setScene(new EditTask(task));
                    break;
                case 1:
                    Logger.log("OptionPopUpController", "The task has been deleted");
                    List<Task> listOfTask = JsonFileIO.read();
                    listOfTask.remove(task);
                    JsonFileIO.write(listOfTask);
                    setScene(new ListView(listOfTask));
                    break;
                default:
                    Logger.log("OptionPopUpController", "No implementations for the selected option");
            }
            optionPopUp.hide();
        }
    }

}




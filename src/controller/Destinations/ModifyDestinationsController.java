package controller.Destinations;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Destination;
import model.Destinations;

public class ModifyDestinationsController extends Controller<Destinations> {
    @FXML private TextField name;
    @FXML private TextField country;
    @FXML private Button addBtn;
    @FXML private Button removeBtn;

    public BooleanProperty addBtnOn = new SimpleBooleanProperty(false);
    public BooleanProperty removeBtnOn = new SimpleBooleanProperty(false);
    public final String getName() {
        return name.getText();
    }

    public final String getCountry() {
        return country.getText();
    }
    public void setName() {
        if (name != null) {
            name.setText("");
        }
    }

    public void setCountry() {
        if (country != null) {
            country.setText("");
        }
    }
    @FXML public void initialize() {
        if(addBtn != null) {
            name.textProperty().addListener((observable, oldValue, newValue) -> CheckAll());
            country.textProperty().addListener((observable, oldValue, newValue) -> CheckAll());
            addBtn.disableProperty().bind(addBtnOn.not());
        } else {
            name.textProperty().addListener((observable, oldValue, newValue) -> CheckAll());
            country.textProperty().addListener((observable, oldValue, newValue) -> CheckAll());
            removeBtn.disableProperty().bind(removeBtnOn.not());
        }
    }
    
    private void CheckAll() {
        boolean check;
        if (addBtn != null) {
            check = 
                    !getName().isEmpty() 
                    && !getCountry().isEmpty();
            addBtnOn.set(check);
        } else {
            check = !getName().isEmpty() 
                    && !getCountry().isEmpty();
            removeBtnOn.set(check);
        }
    }

    @FXML public void handleAddDestination(ActionEvent event) throws Exception {
        try {
        Destination Destination = new Destination(getName(), getCountry());
        model.addDestination(Destination);
        stage.close();
        } catch (Exception e) {
            Stage errorStage = new Stage();
            errorStage.getIcons().add(new Image("/image/error_icon.png")); 
            ViewLoader.showStage(e, "/view/Error/ErrorView.fxml", "Error", errorStage);
        }
        
    }
    @FXML public void handleRemoveDestination(ActionEvent event) throws Exception {
        try {
        model.removeDestination(model.destination(getName(), getCountry()));
        stage.close();
        }
        catch (Exception e) {
            Stage errorStage = new Stage();
            errorStage.getIcons().add(new Image("/image/error_icon.png")); 
            ViewLoader.showStage(e, "/view/Error/ErrorView.fxml", "Error", errorStage);
        }
    }
    @FXML
    public void handleExit(ActionEvent event) {
        // Close the current window
        stage.close();
    }
}

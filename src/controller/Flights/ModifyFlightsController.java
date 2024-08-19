package controller.Flights;

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
import model.Flight;
import model.Flights;

public class ModifyFlightsController extends Controller<Flights> {
    @FXML private TextField airline;
    @FXML private TextField flightNo;
    @FXML private TextField takeoff;
    @FXML private TextField landing;
    @FXML private TextField cost;
    @FXML private Button addBtn;
    @FXML private Button removeBtn;
    public BooleanProperty addBtnOn = new SimpleBooleanProperty(false);
    public BooleanProperty removeBtnOn = new SimpleBooleanProperty(false);
    public final String getAirline() {
        return (airline != null) ? airline.getText() : null;
    }

    public final String getFlightNo() {
        return (flightNo != null) ? flightNo.getText() : null;
    }

    public final String getTakeoff() {
        return (takeoff != null) ? takeoff.getText() : null;
    }

    public final String getLanding() {
        return (landing != null) ? landing.getText() : null;
    }

    public final String getCost() {
        return (cost != null) ? cost.getText() : null;
    }

    public void setAirline() {
        if (airline != null) {
            airline.setText("");
        }
    }

    public void setFlightNo() {
        if (flightNo != null) {
            flightNo.setText("");
        }
    }

    public void setTakeoff() {
        if (takeoff != null) {
            takeoff.setText("");
        }
    }

    public void setLanding() {
        if (landing != null) {
            landing.setText("");
        }
    }

    public void setCost() {
        if (cost != null) {
            cost.setText("");
        }
    }
    @FXML public void initialize() {
        if(addBtn != null) {
            airline.textProperty().addListener((observable, oldValue, newValue) -> CheckAll());
            flightNo.textProperty().addListener((observable, oldValue, newValue) -> CheckAll());
            takeoff.textProperty().addListener((observable, oldValue, newValue) -> CheckAll());
            landing.textProperty().addListener((observable, oldValue, newValue) -> CheckAll());
            cost.textProperty().addListener((observable, oldValue, newValue) -> CheckAll());
            addBtn.disableProperty().bind(addBtnOn.not());
        } else {
            takeoff.textProperty().addListener((observable, oldValue, newValue) -> CheckAll());
            landing.textProperty().addListener((observable, oldValue, newValue) -> CheckAll());
            removeBtn.disableProperty().bind(removeBtnOn.not());
        }
    }
    
    private void CheckAll() {
        boolean check;
        if (addBtn != null) {
            check = !getAirline().isEmpty() 
                    && !getFlightNo().isEmpty() 
                    && !getTakeoff().isEmpty() 
                    && !getLanding().isEmpty() 
                    && !getCost().isEmpty(); 
            addBtnOn.set(check);
        } else {
            check = !getTakeoff().isEmpty() 
                    && !getLanding().isEmpty();
            removeBtnOn.set(check);
        }
    }
    @FXML public void handleAddFlight(ActionEvent event) throws Exception {
        try {
        Flight flight = new Flight(getAirline(), Integer.parseInt(getFlightNo()), getTakeoff(), getLanding(), Double.parseDouble(getCost().trim()));
        model.addFlight(flight);
        stage.close();
        }
        catch (Exception e){
            Stage errorStage = new Stage();
            errorStage.getIcons().add(new Image("/image/error_icon.png")); 
            ViewLoader.showStage(e, "/view/Error/ErrorView.fxml", "Error", errorStage);
        }
    }
    @FXML public void handleRemoveFlight(ActionEvent event) throws Exception {
        try {
            model.removeFlight(model.getFlight(getTakeoff(), getLanding()));
            stage.close();
        } catch (Exception e) {
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

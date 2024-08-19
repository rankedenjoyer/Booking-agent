package controller.Flights;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import model.*;

public class ExploreFlightsController extends Controller<Agency> {
    public StringProperty greetingMessageProperty() {
        StringBinding binding = Bindings.createStringBinding(() -> {
            String userName = model.getLoggedInUser().getNameProperty().get();
            return "Hello " + userName + ", welcome to the Flights section";
        }, model.getLoggedInUser().getNameProperty());

        StringProperty greetingProperty = new SimpleStringProperty(binding.get());
        binding.addListener((observable, oldValue, newValue) -> greetingProperty.set(newValue));
        
        return greetingProperty;
    }
    @FXML
    public void handleViewAllFlights(ActionEvent event) throws Exception {
        Stage ViewAllFLightsStage = new Stage();
        ViewAllFLightsStage.getIcons().add(new Image("/image/flights_icon.png")); 
        ViewLoader.showStage(model.getFlights(), "/view/Flights/DisplayFlightsView.fxml", "Display Flights", ViewAllFLightsStage);
    }
    @FXML
    public void handleViewFlightsbyCountry(ActionEvent event) throws Exception {
        Stage ViewAllFilteredFlightsStage = new Stage();
        ViewAllFilteredFlightsStage.getIcons().add(new Image("/image/flights_icon.png")); 
        ViewLoader.showStage(model.getFlights(), "/view/Flights/DisplayFilteredFlightsView.fxml", "Display Flights Filtered", ViewAllFilteredFlightsStage);
    }
    @FXML
    public void handleAddFlight(ActionEvent event) throws Exception {
        Stage addStage = new Stage();
        addStage.getIcons().add(new Image("/image/flights_icon.png"));
        ViewLoader.showStage(model.getFlights(), "/view/Flights/AddFlightView.fxml", "Add Flight", addStage);
    }
    @FXML
    public void handleRemoveFlight(ActionEvent event) throws Exception {

        Stage removeStage = new Stage();
        removeStage.getIcons().add(new Image("/image/flights_icon.png"));
        ViewLoader.showStage(model.getFlights(), "/view/Flights/RemoveFlightView.fxml", "Remove Flight", removeStage);
    }
    @FXML
    public void handleExit(ActionEvent event) {
        // Close the current window
        stage.close();
    }
}

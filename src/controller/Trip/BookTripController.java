package controller.Trip;

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

public class BookTripController extends Controller<Trip> {
    public StringProperty greetingMessageProperty() {
        StringBinding binding = Bindings.createStringBinding(() -> {
            String userName = model.getAgency().getLoggedInUser().getNameProperty().get();
            return "Hello " + userName + ", welcome to the Trip section";
        }, model.getAgency().getLoggedInUser().getNameProperty());

        StringProperty greetingProperty = new SimpleStringProperty(binding.get());
        binding.addListener((observable, oldValue, newValue) -> greetingProperty.set(newValue));
        
        return greetingProperty;
    }
    @FXML
    public void handleAddDestination(ActionEvent event) throws Exception {
        Stage addStage = new Stage();
        addStage.getIcons().add(new Image("/image/Destinations_icon.png"));
        ViewLoader.showStage(model.getDestinations(), "/view/Destinations/AddDestinationView.fxml", "Add Destination", addStage);
    }
    @FXML
    public void handleRemoveDestination(ActionEvent event) throws Exception {
        Stage removeStage = new Stage();
        removeStage.getIcons().add(new Image("/image/Destinations_icon.png"));
        ViewLoader.showStage(model.getDestinations(), "/view/Destinations/RemoveDestinationView.fxml", "Remove Destination", removeStage);
    }
    @FXML
    public void handleAddConnectingFlight(ActionEvent event) throws Exception {
        try {
        model.addConnectingFlights();
        }
        catch (Exception e) {
            Stage errorStage = new Stage();
            errorStage.getIcons().add(new Image("/image/error_icon.png")); 
            ViewLoader.showStage(e, "/view/Error/ErrorView.fxml", "Error", errorStage);
        }
    }
    @FXML
    public void handleViewTrip(ActionEvent event) throws Exception {
        Stage viewTripStage = new Stage();
        viewTripStage.getIcons().add(new Image("/image/flights_icon.png"));
        ViewLoader.showStage(model, "/view/Trip/DisplayTripView.fxml", "Display Trip", viewTripStage);
    }
    @FXML
    public void handleExit(ActionEvent event) {
        // Close the current window
        stage.close();
    }
}

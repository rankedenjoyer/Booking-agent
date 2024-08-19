package controller;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import model.*;

public class AgencyController extends Controller<Agency> {
    @FXML private Label HelloTxt;
    public StringProperty greetingMessageProperty() {
        StringBinding binding = Bindings.createStringBinding(() -> {
            String userName = model.getLoggedInUser().getNameProperty().get();
            return "Hello " + userName + ", welcome to the Prog2 Travel Agency";
        }, model.getLoggedInUser().getNameProperty());

        StringProperty greetingProperty = new SimpleStringProperty(binding.get());
        binding.addListener((observable, oldValue, newValue) -> greetingProperty.set(newValue));
        
        return greetingProperty;
    }
    @FXML
    public void handleExploreFlights(ActionEvent event) throws Exception {
        Stage ExploreFLightsStage = new Stage();
        ExploreFLightsStage.getIcons().add(new Image("/image/flights_icon.png")); 
        ViewLoader.showStage(model, "/view/Flights/ExploreFlightsView.fxml", "Explore Flights", ExploreFLightsStage);
    }
    @FXML
    public void handleExploreDestinations(ActionEvent event) throws Exception {
        Stage ExploreDestinationsStage = new Stage();
        ExploreDestinationsStage.getIcons().add(new Image("/image/destinations_icon.png")); 
        ViewLoader.showStage(model, "/view/Destinations/ExploreDestinationsView.fxml", "Explore Destinations", ExploreDestinationsStage);
    }
    @FXML
    public void handleBookTrip(ActionEvent event) throws Exception {
        Stage BookTripStage = new Stage();
        BookTripStage.getIcons().add(new Image("/image/trip_icon.png")); 
        ViewLoader.showStage(new Trip(model), "/view/Trip/BookTripView.fxml", "Book a Trip", BookTripStage);
    }

    @FXML
    public void handleExit(ActionEvent event) {
        // Close the current window
        stage.close();
    }
}

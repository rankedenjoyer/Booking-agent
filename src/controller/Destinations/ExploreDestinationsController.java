package controller.Destinations;

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

public class ExploreDestinationsController extends Controller<Agency> {
    public StringProperty greetingMessageProperty() {
        StringBinding binding = Bindings.createStringBinding(() -> {
            String userName = model.getLoggedInUser().getNameProperty().get();
            return "Hello " + userName + ", welcome to the Destinations section";
        }, model.getLoggedInUser().getNameProperty());

        StringProperty greetingProperty = new SimpleStringProperty(binding.get());
        binding.addListener((observable, oldValue, newValue) -> greetingProperty.set(newValue));
        
        return greetingProperty;
    }
    @FXML
    public void handleViewAllDestinations(ActionEvent event) throws Exception {
        Stage ViewAllDestinationsStage = new Stage();
        ViewAllDestinationsStage.getIcons().add(new Image("/image/Destinations_icon.png")); 
        ViewLoader.showStage(model.getDestinations(), "/view/Destinations/DisplayDestinationsView.fxml", "Display Destinations", ViewAllDestinationsStage);
    }
    @FXML
    public void handleViewDestinationsbyCountry(ActionEvent event) throws Exception {
        Stage ViewAllFilteredDestinationsStage = new Stage();
        ViewAllFilteredDestinationsStage.getIcons().add(new Image("/image/Destinations_icon.png")); 
        ViewLoader.showStage(model.getDestinations(), "/view/Destinations/DisplayFilteredDestinationsView.fxml", "Display Destinations Filtered", ViewAllFilteredDestinationsStage);
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
    public void handleExit(ActionEvent event) {
        // Close the current window
        stage.close();
    }
}

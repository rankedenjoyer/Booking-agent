package controller.Trip;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.util.*;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.*;

public class DisplayTripController extends Controller<Trip> {
    public ObservableList<Itinery> itinery =  FXCollections.<Itinery>observableArrayList();
    @FXML private ListView<Itinery> listView;
    public void setItinery() {
        LinkedList<Itinery> objects = new LinkedList<Itinery>();
        for (int i = 0; i < model.getDestinations().getDestinations().size(); i++) {
            objects.add(model.getDestinations().getDestinations().get(i)); 
            if (i < model.getFlights().getFlights().size()) { objects.add(model.getFlights().getFlights().get(i)); }
        }
        itinery.setAll(objects);
    }
    @FXML public void initialize() {
        setItinery();
        listView.setItems(itinery);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        model.getFlights().getFlights().addListener((ListChangeListener<Flight>) change -> {
            setItinery(); 
        });

        model.getDestinations().getDestinations().addListener((ListChangeListener<Destination>) change -> {
            setItinery(); 
        });
    }
    @FXML public void handleViewIndividuals(ActionEvent event) throws Exception{
        ObservableList<Itinery> chosen = listView.getSelectionModel().getSelectedItems();
        if (!chosen.isEmpty()) {
            ObservableList<Itinery> chosenFlights = FXCollections.<Itinery>observableArrayList();
            ObservableList<Itinery> chosenDestinations = FXCollections.<Itinery>observableArrayList();
            for (Itinery item: chosen) {
                if (item instanceof Flight) {
                    chosenFlights.add(item);
                }
                else {
                    chosenDestinations.add(item);
                }
            }
            if ((chosenDestinations.isEmpty()) && (!chosenFlights.isEmpty())) {
                Stage ViewAllFLightsStage = new Stage();
                ViewAllFLightsStage.getIcons().add(new Image("/image/flights_icon.png")); 
                ViewLoader.showStage(new Flights(chosenFlights), "/view/Flights/DisplayFlightsView.fxml", "Display Flights", ViewAllFLightsStage);
            }
            else if ((!chosenDestinations.isEmpty()) && (chosenFlights.isEmpty())) {
                Stage ViewAllDestinationsStage = new Stage();
                ViewAllDestinationsStage.getIcons().add(new Image("/image/Destinations_icon.png")); 
                ViewLoader.showStage(new Destinations(chosenDestinations), "/view/Destinations/DisplayDestinationsView.fxml", "Display Destinations", ViewAllDestinationsStage);
            } else {
                Stage errorStage = new Stage();
                errorStage.getIcons().add(new Image("/image/error_icon.png")); 
                ViewLoader.showStage(new InputMismatchException(), "/view/Error/ErrorView.fxml", "Error", errorStage);
            }
        }
    }
    @FXML
    public void handleExit(ActionEvent event) {
        // Close the current window
        stage.close();
    }
    
}

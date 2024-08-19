package controller.Flights;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import au.edu.uts.ap.javafx.Controller;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Flight;
import model.Flights;

public class DisplayFlightsController extends Controller<Flights> {
    @FXML private TextField FilterTf;
    private ObservableList<Flight> allFlights = FXCollections.<Flight>observableArrayList();

    public final String getFilterTf() {
        if (FilterTf != null) {
            return FilterTf.getText();
        }
        return "France";
    }

    public void setFilterTf() {
        if (FilterTf != null) {
            FilterTf.setText("");
        }
    }

    public void setAllFlights(ObservableList<Flight> newList) {
        allFlights.setAll(newList);
    }

    public final ObservableList<Flight> getAllFlights() {
        if (allFlights == null) {
            allFlights = model.getFlights();
        }
        return allFlights;
    }

    @FXML
    public void handleExit(ActionEvent event) {
        // Close the current window
        stage.close();
    }

    @FXML
    public void initialize() {
        // Initialize allFlights with the full list of flights
        setAllFlights(model.getFlights());
        model.getFlights().addListener((ListChangeListener<Flight>) change -> {
            setAllFlights(model.getFlights());; 
        });

        if (FilterTf != null) {
            // Listen for Enter key press in the filter TextField
            if (FilterTf != null) {
                FilterTf.textProperty().addListener((observable, oldValue, newValue) -> {
                String country = newValue;
                updateFilteredFlights(country);
        });
    }

        }
    }

    public void updateFilteredFlights(String country) {
        if (country != null && !country.trim().isEmpty()) {
            ObservableList<Flight> filteredFlights = model.getFilteredFlights(country);
            setAllFlights(filteredFlights);
        } else {
            // Show all flights if the filter text is empty
            setAllFlights(model.getFlights());
        }
    }
}

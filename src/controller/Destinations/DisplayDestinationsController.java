package controller.Destinations;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import au.edu.uts.ap.javafx.Controller;
import model.Destination;
import model.Destinations;

public class DisplayDestinationsController extends Controller<Destinations> {
    @FXML private TextField FilterTf;
    @FXML private TableView<Destination> tv;
    private ObservableList<Destination> allDestinations = FXCollections.<Destination>observableArrayList();

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

    public void setAllDestinations(ObservableList<Destination> newList) {
        allDestinations.setAll(newList);
        model.getDestinations().addListener((ListChangeListener<Destination>) change -> {
            setAllDestinations(model.getDestinations());; 
        });
    }

    public final ObservableList<Destination> getAllDestinations() {
        if (allDestinations == null) {
            allDestinations = model.getDestinations();
        }
        return allDestinations;
    }

    @FXML
    public void handleExit(ActionEvent event) {
        // Close the current window
        stage.close();
    }

    @FXML
    public void initialize() {
        setAllDestinations(model.getDestinations());

        if (FilterTf != null) {
            if (FilterTf != null) {
                FilterTf.textProperty().addListener((observable, oldValue, newValue) -> {
                String country = newValue;
                updateFilteredDestinations(country);
        });
    }
        }
    }

    public void updateFilteredDestinations(String country) {
        if (country != null && !country.trim().isEmpty()) {
            ObservableList<Destination> filteredDestinations = model.getFilteredDestinations(country);
            setAllDestinations(filteredDestinations);
        } else {
            // Show all Destinations if the filter text is empty
            setAllDestinations(model.getDestinations());
        }
    }
}

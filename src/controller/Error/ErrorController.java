package controller.Error;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.stage.*;
import model.*;
import model.Exceptions.*;
import javafx.scene.*;
import javafx.scene.text.Text;

public class ErrorController extends Controller<Exception>{
    @FXML
    private Label errorNameTxt;
    @FXML
    private Label suggestionTxt;
    public final String getErrorNameTxt() {
        return errorNameTxt.getText();
    }
    public final String getSuggestionTxt() {
        return suggestionTxt.getText();
    }
    public void setErrorNameTxt(String value) {
        errorNameTxt.setText(value);
    }
    public void setSuggestionTxt(String value) {
        suggestionTxt.setText(value);
    }
    public void setValues(Exception exception) {
        String exceptionType = exception.getClass().getSimpleName(); 
        System.out.println(exceptionType.isEmpty());
        errorNameTxt.setText(exceptionType);
        if (exceptionType.equals("InvalidCredentialsException")) {
            setSuggestionTxt("Invalid username or password!");
        } else if (exceptionType.equals("DuplicateItemException")) {
            setSuggestionTxt("The item already exists");
        } else if (exceptionType.equals("ItemNotFoundException")) {
            setSuggestionTxt("No item found");
        }  else if (exceptionType.equals("NumberFormatException")) {
            setSuggestionTxt("Make sure the numbers are correct!");
        } else if (exceptionType.equals("InsufficientDestinationsException")) {
            setSuggestionTxt("Trip needs more destinations");
        } else {
            setSuggestionTxt("Pick either Flight or Destination");
        }
    }
    @FXML public void initialize() {
        setValues(model);
    }
    @FXML
    public void handleExit(ActionEvent event) {
        // Close the current window
        stage.close();
    }
}

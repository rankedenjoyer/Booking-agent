package controller;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.stage.*;
import model.Exceptions.InvalidCredentialsException;
import model.*;
import javafx.scene.*;


public class LoginController extends Controller<Agency>{
    @FXML
    private TextField UsernameTf;
    @FXML
    private PasswordField PasswordTf;
    public final String getUsername() {
        return UsernameTf.getText();
    }
    public final String getPassword() {
        return PasswordTf.getText();
    }
    public void setUsername() {
        UsernameTf.setText("");
    }
    public void setPassword() {
        PasswordTf.setText("Try again");
    }
    @FXML
    public void handleLogin(ActionEvent event) throws Exception {
        try {
            Administrator a = model.getAdministrators().getAdministrator(getUsername(), getPassword());
            // Close the current window
            Stage AgencyStage = new Stage();
            model.setLoggedInUser(a);
            AgencyStage.getIcons().add(new Image("/image/agency_icon.png")); 
            ViewLoader.showStage(model, "/view/AgencyView.fxml", "Prog2 Travel Agency", AgencyStage);
            stage.close();
        } catch (InvalidCredentialsException e) {
            // Show an error message
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

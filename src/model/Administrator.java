package model;

import javafx.beans.property.*;

public class Administrator {
    StringProperty name;
    StringProperty login;
    StringProperty password;

    public Administrator(String name, String login, String password) {
        this.name = new SimpleStringProperty(name);
        this.login = new SimpleStringProperty(login);
        this.password = new SimpleStringProperty(password);
    }

    public final String getLogin() {
        return this.login.get();
    }

    public final String getPassword() {
        return this.password.get();
    }

    public final String getName() {
        return this.name.get();
    }
    public final StringProperty getNameProperty() {
        return this.name;
    }

}

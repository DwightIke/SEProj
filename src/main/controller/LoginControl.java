package main.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.Repository.CMLoginRepository;
import main.main.Main;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Dragos on 4/4/2017.
 */
public class LoginControl implements Initializable {

    @FXML
    private TextField userField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private RadioButton cmRadio;
    @FXML private RadioButton attendantRadio;
    @FXML private RadioButton authorRadio;
    private ToggleGroup group;

    // Logins Repositories
    private CMLoginRepository CMLRepository;
    //private AttendantLoginRepository ATLRepository;
    //private AuthorsLoginRepository AULRepository;


    public LoginControl(CMLoginRepository cmloginRep/*, AttendantLoginRepository atloginrep, AuthorsLoginRepository atuloginrep*/)
    {
        this.CMLRepository = cmloginRep;
        //this.ATLRepository = atloginrep;
        //this.AULRepository = atuloginrep;
    }

    public void initialize(URL location, ResourceBundle resources)
    {
        passwordField.setPromptText("Your password");
        userField.setPromptText("Your username");

        this.group = new ToggleGroup();

        cmRadio.setToggleGroup(group);
        cmRadio.setSelected(true);

        attendantRadio.setToggleGroup(group);

        authorRadio.setToggleGroup(group);
    }

    public void initManager(final Main loginManager)
    {
        loginButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                    int response = 0;
                    String userName = userField.getText();
                    String password = passwordField.getText();
                    String selected = group.getSelectedToggle().getUserData().toString();
                    if (userName == null) {
                        showErrorMessage("Dati un username");
                    } else if (password == null) {
                        showErrorMessage("Dati o parola");
                    } else
                        {
                            if (selected.compareTo("CM") == 0)
                            {
                                //aici va fi legagura cu repository-ul CM
                                // (ar fi o chestie sa returneze in respone valid,
                                // daca e corecta parola, sau invalid in caz contrar
                                // (e o verificare pt response mai jos)
                                try {
                                    if (CMLRepository.login(userName, password)) {
                                        showMessage(Alert.AlertType.CONFIRMATION);
                                        response = 1;
                                    }

                                }catch(SQLException e) {
                                    e.printStackTrace();
                                    //
                                }
                            }
                            else if (selected.compareTo("Author") == 0)
                            {
                                //aici va fi legagura cu repository-ul author
                                // (ar fi o chestie sa returneze in respone valid,
                                // daca e corecta parola, sau invalid in caz contrar
                                // (e o verificare pt response mai jos)
                            }
                            else if (selected.compareTo("Attendant") == 0)
                            {
                                //aici va fi legagura cu repository-ul attendant
                                // (ar fi o chestie sa returneze in respone valid,
                                // daca e corecta parola, sau invalid in caz contrar
                                // (e o verificare pt response mai jos)
                            }
                            if (response == 0) {
                                showErrorMessage("Username sau parola invalida");
                                initManager(loginManager);
                            }
                            else
                            {
                                loginManager.authenticated(response);
                            }
                    }
            }
        });
    }

    private static void showMessage(Alert.AlertType type)
    {
        Alert message=new Alert(type);
        message.setHeaderText("Succes");
        message.setContentText("Succes");
        message.showAndWait();
    }

    private static void showErrorMessage(String text)
    {
        Alert message=new Alert(Alert.AlertType.ERROR);
        message.setTitle("Mesaj eroare");
        message.setContentText(text);
        message.showAndWait();
    }
}

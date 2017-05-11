package main.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.main.Main;
import main.repository.AttendantRepository;
import main.repository.AuthorsRepository;
import main.repository.ComiteeRepository;
import main.repository.ReviewerRepository;

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
    @FXML private RadioButton cmRadio, reviewRadio;
    @FXML private RadioButton attendantRadio;
    @FXML private RadioButton authorRadio;
    private ToggleGroup group;

    // Logins Repositories
    private ComiteeRepository CMLRepository;
    private AttendantRepository ATLRepository;
    private AuthorsRepository AULRepository;
    private ReviewerRepository RVWRepo;


    public LoginControl(ComiteeRepository cmloginRep, AttendantRepository atloginrep, AuthorsRepository atuloginrep, ReviewerRepository RVWRepo)
    {
        this.CMLRepository = cmloginRep;
        this.ATLRepository = atloginrep;
        this.AULRepository = atuloginrep;
        this.RVWRepo = RVWRepo;
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
                    //String selected = group.getSelectedToggle().getUserData().toString();
                    if (userName == null) {
                        showErrorMessage("Dati un username");
                    } else if (password == null) {
                        showErrorMessage("Dati o parola");
                    } else
                        {
                            if (cmRadio.isSelected())
                            {
                                //aici va fi legagura cu repository-ul CM
                                // (ar fi o chestie sa returneze in respone valid,
                                // daca e corecta parola, sau invalid in caz contrar
                                // (e o verificare pt response mai jos)
                                if(reviewRadio.isSelected()){
                                    try{
                                        if(RVWRepo.login(userName, password)){
                                            response = 4;
                                        }
                                    }catch(SQLException ex){
                                        ex.printStackTrace();
                                    }
                                }
                                else {
                                    try {
                                        if (CMLRepository.login(userName, password)) {
                                            showMessage(Alert.AlertType.CONFIRMATION);
                                            response = 1;
                                        }

                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                        //
                                    }
                                }
                            }
                            else if (authorRadio.isSelected())
                            {
                                //aici va fi legagura cu repository-ul author
                                // (ar fi o chestie sa returneze in respone valid,
                                // daca e corecta parola, sau invalid in caz contrar
                                // (e o verificare pt response mai jos)
                                try {
                                    if (AULRepository.login(userName, password)) {
                                        showMessage(Alert.AlertType.CONFIRMATION);
                                        response = 2;
                                    }

                                }catch(SQLException e) {
                                    e.printStackTrace();
                                    //
                                }
                            }
                            else if (attendantRadio.isSelected())
                            {
                                //aici va fi legagura cu repository-ul attendant
                                // (ar fi o chestie sa returneze in respone valid,
                                // daca e corecta parola, sau invalid in caz contrar
                                // (e o verificare pt response mai jos)
                                try {
                                    if (ATLRepository.login(userName, password)) {
                                        showMessage(Alert.AlertType.CONFIRMATION);
                                        response = 3;
                                    }

                                }catch(SQLException e) {
                                    e.printStackTrace();
                                    //
                                }
                            }
                            if (response == 0) {
                                showErrorMessage("Username sau parola invalida");
                                initManager(loginManager);
                            }
                            else
                            {
                                try {
                                    loginManager.authenticated(response);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
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

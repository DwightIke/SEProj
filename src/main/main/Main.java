package main.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.controller.LoginControl;
import main.controller.ReviewerControl;
import main.database.Database;
import main.repository.AttendantRepository;
import main.repository.AuthorsRepository;
import main.repository.ComiteeRepository;
import main.repository.ReviewerRepository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

/**
 * Created by Dragos on 4/4/2017.
 */

public class Main extends Application {
    private FXMLLoader loader;
    private FXMLLoader loader2;
    private Stage primaryStage;
    private AnchorPane rootLayout1;
    private TabPane rootLayout2;
    private static void execute(String sql) {

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException {
        this.primaryStage = primaryStage;
        loader = new FXMLLoader();
        loader2 = new FXMLLoader();
        LoginView();
    }

    public void authenticated(int response) throws SQLException, ClassNotFoundException {
        if (response == 1)
        {
            MainViewCM();
        }
        else if (response == 2)
        {
            MainViewAuthor();
        }
        else if (response ==3)
        {
            MainViewAttendant();
        }
        else if (response == 4){
            MainViewReviewer();
        }
    }

    private void LoginView() throws ClassNotFoundException, SQLException {
        // xampp db connect
        // asta o folositi toti
        Database db = new Database("//localhost:3306/cms");
        if (!db.startConnection("root", "")) // daca baza de date are user si pass
        return;

        ComiteeRepository cmloginrep = new ComiteeRepository(db.getConnection());
        AttendantRepository atloginrep = new AttendantRepository(db.getConnection());
        AuthorsRepository atuloginrep = new AuthorsRepository(db.getConnection());
        ReviewerRepository RVWRepo = new ReviewerRepository(db.getConnection());


        try {
            String pathToFxml = "src/main/resources/LoginWindow.fxml";
            URL fxmlUrl = new File(pathToFxml).toURI().toURL();
            loader.setLocation(fxmlUrl);
            LoginControl controlLogin = new LoginControl(cmloginrep, atloginrep, atuloginrep, RVWRepo);
            loader.setController(controlLogin);
            rootLayout1 = loader.load();
            Scene scene = new Scene(rootLayout1);
            primaryStage.setScene(scene);
            primaryStage.show();

            controlLogin.initManager(this);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void MainViewAuthor()
    {

    }

    private void MainViewAttendant()
    {

    }

    private void MainViewCM()
    {

    }

    private void MainViewReviewer() throws SQLException, ClassNotFoundException {
        Database db = new Database("//localhost:3306/cms");
        if (!db.startConnection("root", "")) // daca baza de date are user si pass
            return;
        ReviewerRepository reviewRepo = new ReviewerRepository(db.getConnection());

        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/ReviewerWindow.fxml"));
            Pane myPane = (Pane) loader.load();
            ReviewerControl ctrl=loader.getController();
            ctrl.initData();
            Scene myScene = new Scene(myPane);
            Stage newStage = new Stage();
            newStage.setScene(myScene);

            newStage.show();
            //controlLogin.initManager(this);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import main.repository.ReviewerRepository;
import main.utils.AcceptLevel;

/**
 * Created by Dragos on 5/8/2017.
 */
public class ReviewerControl {
    private ReviewerRepository reviewRepo;
    @FXML
    ComboBox<AcceptLevel> acceptCB = new ComboBox<>();

    public ReviewerControl(){
    }
    public void initData(){
        acceptCB.getItems().addAll(AcceptLevel.values());
    }
}

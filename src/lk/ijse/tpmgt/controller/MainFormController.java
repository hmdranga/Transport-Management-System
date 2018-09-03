/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.tpmgt.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author A C E R
 */
public class MainFormController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private ImageView imgVehicle;
    @FXML
    private ImageView imgDriver;
    @FXML
    private ImageView imgTrip;
    @FXML
    private ImageView imgExpences;
    @FXML
    private ImageView imgSalary;
    @FXML
    private Label lblMenu;
    @FXML
    private Label lblDescription;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void playMouseExitAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView){
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT =new ScaleTransition(Duration.millis(150), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play(); 
            
            icon.setEffect(null);
            lblMenu.setText("Welcome");
            lblDescription.setText("Please select one of above main operations to proceed");
        }
    }

    @FXML
    private void playMouseEnterAnimation(MouseEvent event) {
             if (event.getSource() instanceof ImageView){
            ImageView icon = (ImageView) event.getSource();
            
            switch(icon.getId()){
                case "imgVehicle":
                    lblMenu.setText("Vehicle");
                    lblDescription.setText("Click to add, delete, update or view Vehicle");
                    break;
                case "imgDriver":
                    lblMenu.setText("Driver");
                    lblDescription.setText("Click to add, delete, update or view Driver");
                    break;
                case "imgTrip":
                    lblMenu.setText("Trip");
                    lblDescription.setText("Click to make Trip");
                    break;
                case "imgExpences":
                    lblMenu.setText("Vehicle Expences");
                    lblDescription.setText("Click to make Vehicle Expences");
                    break;
                case "imgSalary":
                    lblMenu.setText("Salary");
                    lblDescription.setText("Click to pay Salary");
                    break;
                
            }
            
            ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play(); 
            
            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);            
        }
    }

    @FXML
    private void navigate(MouseEvent event) throws IOException {
            if (event.getSource() instanceof ImageView){
            ImageView icon = (ImageView) event.getSource();
            
            Parent root = null;     
            
            switch(icon.getId()){
                case "imgVehicle":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/tpmgt/view/ManageVehicleForm.fxml"));
                    break;
                case "imgDriver":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/tpmgt/view/ManageDriverForm.fxml"));
                    break;
                case "imgTrip":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/tpmgt/view/ManageTripForm.fxml"));
                    break;
                case "imgExpences":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/tpmgt/view/ManageVehicleExpenceForm.fxml"));
                    break;
                case "imgSalary":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/tpmgt/view/ManageSalaryForm.fxml"));
                    break;
                
            }
            
            if (root != null){
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();
                
                TranslateTransition tt = new TranslateTransition(Duration.millis(400), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();
                
            }
        }
        
        
        
    }
    
}

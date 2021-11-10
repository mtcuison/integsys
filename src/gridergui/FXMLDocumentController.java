/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gridergui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.rmj.appdriver.GRider;
import org.rmj.fund.manager.base.Incentive;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLDocumentController implements Initializable, ScreenInterface {
    private GRider oApp;
    
    @FXML
    private Pane btnMin;
    @FXML
    private Pane btnClose;
    @FXML
    private StackPane workingSpace;
    @FXML
    private Menu mnuTransaction;
    @FXML
    private MenuItem mnuEmployeeIncentives;
    @FXML
    private MenuItem mnuIncentiveParameter;
    @FXML
    private Pane view;
    @FXML
    private MenuItem mnuEmployeeIncentivesBank;
    @FXML
    private MenuItem mnuIncentiveReleasing;
    @FXML
    private MenuItem mnuIncentiveConfirmation;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setScene(loadAnimate("MainScreenBG.fxml"));
    }    
    
    @Override
    public void setGRider(GRider foValue) {
        oApp = foValue;
    }

    @FXML
    private void handleButtonCloseClick(MouseEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleButtonMinimizeClick(MouseEvent event) {
        Stage stage = (Stage) btnMin.getScene().getWindow();
        stage.setIconified(true);
    }
    
    private AnchorPane loadAnimate(String fsFormName){
        ScreenInterface fxObj = getController(fsFormName);
        fxObj.setGRider(oApp);
        
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(fxObj.getClass().getResource(fsFormName));
        fxmlLoader.setController(fxObj);      
   
        AnchorPane root;
        try {
            root = (AnchorPane) fxmlLoader.load();
            FadeTransition ft = new FadeTransition(Duration.millis(1500));
            ft.setNode(root);
            ft.setFromValue(1);
            ft.setToValue(1);
            ft.setCycleCount(1);
            ft.setAutoReverse(false);
            ft.play();

            return root;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
    
    private ScreenInterface getController(String fsValue){
        System.out.println(fsValue);
        switch (fsValue){
            case "MainScreenBG.fxml":
                return new MainScreenBGController();
            case "EmployeeIncentives.fxml":
                return new EmployeeIncentivesController();
            case "EmployeeBankInfo.fxml":
                return new EmployeeBankInfoController();
            case "IncentiveParameter.fxml":
                return new IncentiveParameterController();
            case "AddIncentives.fxml":
                return new AddIncentivesController();
            case "IncentiveReleasing.fxml":
                return new IncentiveReleasingController();
            case "IncentiveConfirmation.fxml":
                return new IncentiveConfirmationController();
            default:
                return null;
        }
    }
 
    private void setScene(AnchorPane foPane){
        workingSpace.getChildren().clear();
        workingSpace.getChildren().add(foPane);
    }

    @FXML
    private void mnuEmployeeIncentivesClick(ActionEvent event) {
        setScene(loadAnimate("EmployeeIncentives.fxml"));
    }
    @FXML
    private void mnuEmployeeBankInfoClick(ActionEvent event) {
        setScene(loadAnimate("EmployeeBankInfo.fxml"));
    }
    
    @FXML
    private void mnuIncentiveParameterClick(ActionEvent event) {
        setScene(loadAnimate("IncentiveParameter.fxml"));
    } 
    @FXML
    private void mnuIncentiveReleasingClick(ActionEvent event) {
        setScene(loadAnimate("IncentiveReleasing.fxml"));
    } 
    @FXML
    private void mnuIncentiveConfirmationClick(ActionEvent event) {
        setScene(loadAnimate("IncentiveConfirmation.fxml"));
    }
}

package Views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisteredView extends AView{

    @FXML
    public Button btn_exit;
    public Button btn_read;
    public Button btn_update;
    public Button btn_delete;

    public BorderPane lyt_mainPane;


    /**
     * method to set the read interface
     * @param mouseEvent - click event on the read button
     */
    public void setRead(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader();
        try {
            lyt_mainPane.setCenter(loader.load(getClass().getResourceAsStream("/fxmls/readXML.fxml")));

            AView v = loader.getController();
            v.set_controller(_controller);

        } catch (IOException e) {
            popProblem("Error while trying to load read interface\n" + e.getMessage());
        }
        mouseEvent.consume();
    }


    /**
     * method to set the update interface
     * @param mouseEvent - click event of the update button
     */
    public void setUpdate(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader();
        try {
            lyt_mainPane.setCenter(loader.load(getClass().getResourceAsStream("/fxmls/updateXML.fxml")));

            AView v = loader.getController();
            v.set_controller(_controller);

        } catch (IOException e) {
            popProblem("Error while trying to load update interface\n" + e.getMessage());
        }
        mouseEvent.consume();
    }


    /**
     * method to set the delete interface
     * @param mouseEvent - click even on the delete button
     */
    public void setDelete(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader();
        try {
            lyt_mainPane.setCenter(loader.load(getClass().getResourceAsStream("/fxmls/deleteXML.fxml")));

            AView v = loader.getController();
            v.set_controller(_controller);

        } catch (IOException e) {
            popProblem("Error while trying to load update interface\n" + e.getMessage());
        }
        mouseEvent.consume();
    }


    /**
     * method to to exit the program
     * @param mouseEvent - even of close
     */
    public void setExit(MouseEvent mouseEvent) {
        Stage stage = (Stage) btn_exit.getScene().getWindow();
        mouseEvent.consume();
        stage.close();
    }

}

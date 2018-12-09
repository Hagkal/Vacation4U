package Views;

import Controllers.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class unRegisterView extends AView {

    @FXML
    public Button btn_exit;
    public Button btn_login;

    /**
     * method to exit the program
     * @param mouseEvent - mouse click on exit event
     */
    public void exit(MouseEvent mouseEvent) {
        Stage stage = (Stage) btn_exit.getScene().getWindow();
        mouseEvent.consume();
        stage.close();
    }


    /**
     * method to move to login zone
     * @param mouseEvent - mouse click on system enter
     */
    public void login(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader();
        try {
            Parent root = loader.load(getClass().getResourceAsStream("/fxmls/loginXML.fxml"));
            AView loginView = loader.getController();
            loginView.set_controller(_controller);
            loginView.set_cameFrom(Main.pStage.getScene());

            Main.pStage.setScene(new Scene(root, 800, 400));
            Main.pStage.show();

            mouseEvent.consume();

        } catch (IOException e) {
            System.out.println("Something bad happened while trying to move to login screen :(");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}

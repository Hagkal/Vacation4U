package Views;

import Controllers.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.time.LocalDate;

public class LoginView extends AView {
    public Button btn_login;
    public Button btn_register;
    public Button btn_back;
    public TextField tf_userName;
    public PasswordField pf_password;


    /**
     * method to launch registration procedure
     * @param mouseEvent - mouse click on registration button
     */
    public void setCreateForm(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader();
        try {
            Parent root = loader.load(getClass().getResourceAsStream("/fxmls/createXML.fxml"));
            CreateView registerView = loader.getController();
            registerView.set_cameFrom(Main.pStage.getScene());
            registerView.set_controller(_controller);

            LocalDate today = LocalDate.now();
            registerView.dp_date.setValue(today.minusYears(18));
            registerView.dp_date.getEditor().setText("Must be over 18 years old");

            Main.pStage.setScene(new Scene(root, 800, 400));
            Main.pStage.show();

            mouseEvent.consume();
        } catch (IOException e) {
            System.out.println("Some went wrong loading registration :(");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * method to go back from login screen
     * @param mouseEvent - mouse click on 'back' cutton
     */
    public void goBack(MouseEvent mouseEvent) {
        Main.pStage.setScene(_cameFrom);
        Main.pStage.show();
        mouseEvent.consume();
    }

    /**
     * method to confirm login
     * @param mouseEvent - mouse click on 'login' button
     */
    public void login_baby(MouseEvent mouseEvent) {
        String username = tf_userName.getText();
        String password = pf_password.getText();

        if (username.isEmpty() || password.isEmpty()){
            popProblem("Please insert both Username and Password");
            return;
        }

        String response = _controller.login(username, password);
        if (response.contains("Error") &&
                response.contains("find")){
            popProblem("Login failed!\n" +
                    "Make sure you typed in a proper Username and Password");
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        try {
            Parent root = loader.load(getClass().getResourceAsStream("/fxmls/RegisteredView.fxml"));
            RegisteredView view = loader.getController();
            view.set_controller(_controller);
            view.set_cameFrom(Main.pStage.getScene());

            view._loggedUser = username;
            view._manager = false; /* needs to be changed */

            Main.pStage.setScene(new Scene(root, 800, 400));
            Main.pStage.show();

            mouseEvent.consume();
        } catch (IOException e) {
            System.out.println("Something went wrong trying to load logged in screen :(");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        catch (NullPointerException e){
            System.out.println("Something went wrong with the DB/SQL service :(");
            e.printStackTrace();
        }


    }
}

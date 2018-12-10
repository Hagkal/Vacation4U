package Controllers;

import Controllers.Controller;
import Models.Model;
import Views.AView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage pStage;


    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        pStage = primaryStage;

        // receiving fxml loader and setting primary node
        FXMLLoader loader = new FXMLLoader();
        String fxmlFile = "/fxmls/UnRegistered.fxml";
        Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));


        // setting up
        Model model = new Model();
        Controller controller = new Controller();
        AView view = loader.getController();
        controller.set_model(model);
        view.set_controller(controller);

        // displaying the first presentation
        primaryStage.setTitle("Vacation4U");
        primaryStage.setScene(new Scene(root, 800, 400));
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}

package Views;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class unRegisterView extends Application {

    public Button btn_ams;
    public Label lbl_ams_details;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {

            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxmls/sample.fxml"));
            primaryStage.setTitle("Vacation4U");

            Canvas canvas = new Canvas(1200, 1400);

            GraphicsContext gc = canvas.getGraphicsContext2D();
            String ams = getClass().getResource("/Pics/ams.jpg").toString();
            Image amsIM = new Image(ams);
            gc.drawImage(amsIM, 255, 95, 250, 180);

            String london = getClass().getResource("/Pics/london.jpg").toString();
            Image londonIM = new Image(london);
            gc.drawImage(londonIM,675,95,250, 180);

            String paris = getClass().getResource("/Pics/paris.jpg").toString();
            Image parisIM = new Image(paris);
            gc.drawImage(parisIM,675,405,250, 180);

            String rome = getClass().getResource("/Pics/rome.jpg").toString();
            Image romeIM = new Image(rome);
            gc.drawImage(romeIM,255,405,250, 180);

            Pane root = new Pane();
            root = fxml.load();
            root.getChildren().add(canvas);

            Scene scene = new Scene(root, 1400, 700);
            primaryStage.setScene(scene);
            primaryStage.show();

            btn_ams.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    FXMLLoader loader = new FXMLLoader();
                    Stage details = new Stage();
                    try {
                        Parent root = loader.load(getClass().getResourceAsStream("/fxmls/readXML.fxml"));
                        lbl_ams_details = new Label("Details" + "\n" + "More Details" + "\n" + "fooking more details");
                        details.setTitle("Vacation4U");
                        details.setScene(new Scene(root, 800, 400));
                        details.setResizable(false);
                        details.show();
                    }
                    catch(Exception exc){}

                    e.consume();
                }
            });
        }
        catch (Exception e){

        }

    }

}
package Views;

import com.sun.prism.paint.Color;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class unRegisterView extends Application {

    public Label lbl_ams;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {

            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxmls/sample.fxml"));
            //Parent root = fxml.load();
            primaryStage.setTitle("Vacation4U");
            //Group root = fxml.load();
            // Create the Canvas

            Canvas canvas1 = new Canvas(800, 400);
            Canvas canvas2 = new Canvas(1200, 400);
            Canvas canvas3 = new Canvas(600, 400);
            Canvas canvas4 = new Canvas(1200, 450);
            // Get the graphics context of the canvas

            GraphicsContext gc = canvas1.getGraphicsContext2D();
            gc = canvas2.getGraphicsContext2D();
            gc = canvas3.getGraphicsContext2D();
            gc = canvas4.getGraphicsContext2D();


            // Load the Image
            String ams = getClass().getResource("/Pics/ams.jpg").toString();
            Image amsIM = new Image(ams);
            String london = getClass().getResource("/Pics/london.jpg").toString();
            Image londonIM = new Image(london);
            String paris = getClass().getResource("/Pics/paris.jpg").toString();
            Image parisIM = new Image(paris);
            String rome = getClass().getResource("/Pics/rome.jpg").toString();
            Image romeIM = new Image(rome);

            // Draw the Image
            lbl_ams = new Label();
            lbl_ams.setText("תאריכים: 6-6");
            gc.drawImage(amsIM, 210, 80, 300,170);
            gc.drawImage(londonIM,630,80,320,170);
            gc.drawImage(parisIM,630,320,320,130);
            gc.drawImage(romeIM,210,320,300,150);

            // Create the Pane

            Pane root = new Pane();
            // Add the Canvas to the Pane
            root = fxml.load();
            root.getChildren().add(canvas1);
            root.getChildren().add(canvas2);
            root.getChildren().add(canvas3);
            root.getChildren().add(canvas4);


            Scene scene = new Scene(root, 1280, 720);
            /*Image img_ams = new Image(ams);
            ImageView imv_ams = new ImageView(img_ams);
            gp_vacations.getChildren().add(imv_ams);*/
            primaryStage.setScene(scene);
            primaryStage.show();
            //gc.setFill(javafx.scene.paint.Color.BLACK);
        }
        catch (Exception e){

        }


    }
}
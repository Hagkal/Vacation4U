package Views;

import Users.User;
import Vacations.VacationPayment;
import Vacations.VacationRequest;
import Vacations.VacationSell;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MailView extends ARegisteredView {

    public AnchorPane ap_mail;
    public BorderPane bp_waiting;
    public BorderPane bp_authorized;
    public Button btn_authorize = new Button();
    public Button btn_pay = new Button();
    ListView<String> waitingForAuthorizationList;
    ListView<String> confirmationsList;

    @Override
    public void prepareView(User username, boolean isManager) {
        _loggedUser = username;
        _manager = isManager;

        waitingForAuthorizationList = new ListView<>();
        confirmationsList = new ListView<>();

        ArrayList<VacationRequest> waitingForAuthorization = _controller.getVacationsForApproval(username.get_userName());
        String buyer, date, id, full, buyer2, price, tickets;
        if (waitingForAuthorization != null) {
            for (VacationRequest v : waitingForAuthorization) {
                id = v._vacationId;
                buyer = v._buyer;
                date = v._date;
                full = "VacationID: " + id + "\t" + "Buyer: " + buyer + "\t" + "Date: " + date;
                waitingForAuthorizationList.getItems().add(full);
            }
        }
        waitingForAuthorizationList.setPrefWidth(800);
        waitingForAuthorizationList.setPrefHeight(150);
        bp_waiting.setCenter(waitingForAuthorizationList);

        ArrayList<VacationSell> vacationsToPay = _controller.getVacationForApprovePayment(username.get_userName());
        if (vacationsToPay != null) {
            for (VacationSell v : vacationsToPay) {
                id = v.get_vacationId();
                buyer2 = v.get_buyer();
                date = v.get_date();
                price = v.get_price();
                full = "VacationID: " + id + "\t" + "Buyer: " + buyer2 + "\t" + "Date: " + date + "\t" + "Price: " + price;
                confirmationsList.getItems().add(full);
            }
        }
        confirmationsList.setPrefHeight(150);
        confirmationsList.setPrefWidth(800);
        bp_authorized.setCenter(confirmationsList);
    }

    public void setSelectApprove (MouseEvent event){
        String entry = waitingForAuthorizationList.getSelectionModel().getSelectedItem();
        if (entry != null) {
            int start = entry.indexOf(':');
            int end = entry.indexOf("Buyer");
            String id = entry.substring(start + 2, end - 1);
            entry = entry.substring(end);
            start = 7;
            end = entry.indexOf("Date");
            String buyer = entry.substring(start, end - 1);

            String response = _controller.approveVacation(_loggedUser.get_userName(), id, buyer);
            if (response.contains("approved"))
                popInfo(response);
            else if (response.contains("error"))
                popProblem(response);
        }
        else
            popProblem("No selection was made");

        prepareView(_loggedUser, _manager);
    }

    public void setSelectPay (MouseEvent event){
        String entry = confirmationsList.getSelectionModel().getSelectedItem();
        if (entry != null) {
            String id, username, seller, price;
            int start = 12;
            int end = entry.indexOf("Seller");
            id = entry.substring(start, end - 1);
            entry = entry.substring(end);
            start = 8 + entry.indexOf("Seller");
            end = entry.indexOf("Date");
            seller = entry.substring(start, end - 1);
            entry = entry.substring(end);
            start = 7 + entry.indexOf("Price");
            end = entry.length();
            price = entry.substring(start, end);

            try {
                Stage pStage = new Stage();
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxmls/paymentXML.fxml"));
                pStage.setTitle("Vacation4U");
                Parent root = fxml.load();

                PaymentView v = fxml.getController();
                v.set_controller(_controller);
                v._loggedUser = _loggedUser;
                v._manager = _manager;
                v._price = price;
                v._id = id;
                v._seller = seller;
                v.prepareView(_loggedUser, _manager);

                pStage.setScene(new Scene(root, 900, 600));
                pStage.initModality(Modality.APPLICATION_MODAL);
                pStage.showAndWait();
                pStage.setResizable(false);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        else
            popProblem("No selection was made");


        prepareView(_loggedUser, _manager);
    }


    public void confirmPayment(MouseEvent mouseEvent){
        mouseEvent.consume();
        String entry = confirmationsList.getSelectionModel().getSelectedItem();
        if (entry != null) {
            String id, buyer, seller, price;
            int start = 12;
            int end = entry.indexOf("Buyer");
            id = entry.substring(start, end - 1);
            entry = entry.substring(end);
            start = 7 + entry.indexOf("Buyer");
            end = entry.indexOf("Date");
            buyer = entry.substring(start, end - 1);
            entry = entry.substring(end);
            start = 7 + entry.indexOf("Price");
            end = entry.length();
            price = entry.substring(start, end);
            seller = _loggedUser.get_userName();

            String response = _controller.confirmPayment(id, seller, buyer, price);
            if (response.toLowerCase().contains("error")){
                popProblem(response);
            }
            else{
                popInfo(response);
            }
        }
        else{
            popProblem("No selection was made");
        }

        prepareView(_loggedUser, _manager);
    }

}

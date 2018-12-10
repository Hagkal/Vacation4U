package Views;

import Vacations.VacationApprove;
import Vacations.VacationRequest;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

public class MailView extends ARegisteredView {

    public AnchorPane ap_mail;
    public BorderPane bp_waiting;
    public BorderPane bp_authorized;
    public Button btn_authorize = new Button();
    public Button btn_pay = new Button();
    ListView<String> waitingForAuthorizationList = new ListView<>();
    ListView<String> confirmationsList = new ListView<>();

    @Override
    public void prepareView(String username, boolean isManager) {
        ArrayList<VacationRequest> waitingForAuthorization = _controller.getVacationsForApproval(username);
        String buyer, date, id, full, seller, price, tickets;
        for (VacationRequest v: waitingForAuthorization) {
            id = v._vacationId;
            buyer = v._buyer;
            date = v._date;
            full = "VacationID: " + id + "\t" + "Buyer: " + buyer + "\t" + "Date: " + date;
            waitingForAuthorizationList.getItems().add(full);
        }
        waitingForAuthorizationList.setPrefWidth(800);
        waitingForAuthorizationList.setPrefHeight(150);
        bp_waiting.setCenter(waitingForAuthorizationList);

        //ArrayList<VacationApprove> vacationsToPay = _controller.getVacationsToPay(username);
        ArrayList<VacationApprove> vacationsToPay = new ArrayList<>();
        vacationsToPay.add(new VacationApprove("6", "omri", "12/3/2018", "250$"));
        for (VacationApprove v: vacationsToPay) {
            id = v._vID;
            buyer = v._buyer;
            date = v._date;
            price = v._price;
            full = "VacationID: " + id + "\t" + "Buyer: " + buyer + "\t" + "Date: " + date + "\t" + "Price: " + price;
            confirmationsList.getItems().add(full);
        }
        confirmationsList.setPrefHeight(150);
        confirmationsList.setPrefWidth(800);
        bp_authorized.setCenter(confirmationsList);
    }

    public void setSelectApprove (MouseEvent event){
        String entry = waitingForAuthorizationList.getSelectionModel().getSelectedItem();
        int start = entry.indexOf(':');
        int end = entry.indexOf("Buyer");
        System.out.println(entry.substring(start + 2, end - 1));//TO-DO: pass to controller the ID
    }

    public void setSelectPay (MouseEvent event){
        String vacationToApprove = confirmationsList.getSelectionModel().getSelectedItem();
        int start = vacationToApprove.indexOf(':');
        int end = vacationToApprove.indexOf("Buyer");
        System.out.println(vacationToApprove.substring(start + 2, end - 1));//TO-DO: pass to controller the ID
    }


}

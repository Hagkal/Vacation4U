package Views;

import Vacations.Vacation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;


public class VacationsTableView extends ARegisteredView{

    @FXML
    public AnchorPane ap_vacations;
    public Button ty = new Button();
    ListView<String> list = new ListView<>();


    @Override
    public void prepareView(String username, boolean isManager) {

        ArrayList <Vacation> vacations = _controller.getAllVacations(username);
        if (vacations != null) {
            _loggedUser = username;
            String id, dest, depart, arrive, quant, price, seller, full;
            for (Vacation v : vacations) {
                if (!v._sellingUser.equals(_loggedUser)) {
                    id = v._id;
                    seller = v._sellingUser;
                    dest = v._destination;
                    depart = v._departureDate;
                    arrive = v._returnDate;
                    quant = v._quantity;
                    price = v._price;
                    full = "Vacation ID: " + id + "\t" + "Seller: " + seller + "\t" + "Destination: " + dest + "\t" + " Departure Date: " + depart + "\t" +
                            " Arrival Date: " + arrive + "\t" + " Quantity: " + quant + "\t" + " Price: " + price;
                    list.getItems().add(full);
                }
            }
        }

        list.setPrefWidth(800);
        list.setPrefHeight(150);
        ap_vacations.getChildren().add(list);
    }

    public void setSelectVacation (MouseEvent event){
        String entry = list.getSelectionModel().getSelectedItem();
        if (entry != null) {
            int start = entry.indexOf(':');
            int end = entry.indexOf("Seller");
            String[] selectedVacationDetails = new String[4];
            selectedVacationDetails[0] = entry.substring(start + 2, end - 1);//id
            entry = entry.substring(end);
            start = entry.indexOf(':');
            end = entry.indexOf("Destination");
            selectedVacationDetails[1] = entry.substring(start + 2, end - 1);//seller
            entry = entry.substring(end);
            start = entry.indexOf("Price");
            end = entry.length();
            selectedVacationDetails[2] = entry.substring(start + 7, end);//price
            selectedVacationDetails[3] = _loggedUser;//logged user
          
            String response = _controller.bidVacation(selectedVacationDetails[1], selectedVacationDetails[3], selectedVacationDetails[0], selectedVacationDetails[2]);
            if (response.contains("Bid success"))
                popInfo(response);
            else if (response.contains("error"))

                popProblem(response);

        }
        else
            popProblem("No selection was made");

    }


}

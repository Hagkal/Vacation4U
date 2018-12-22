package Views;

import Vacations.Vacation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class MyVacationView extends ARegisteredView {


    @FXML
    public AnchorPane ap_vacations;
    public ListView<String> myList = new ListView<>();


    @Override
    public void prepareView(String username, boolean isManager) {

        ArrayList<Vacation> vacations = _controller.getMyVacations(username);

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
                    myList.getItems().add(full);
                }
            }
        }
    }
}

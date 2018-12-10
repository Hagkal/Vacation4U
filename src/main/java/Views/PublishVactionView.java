package Views;

import Vacations.Vacation;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.util.ArrayList;

public class PublishVactionView extends ARegisteredView {


    public Button btn_publish;

    public TextField tf_destination;
    public TextField tf_airline;
    public TextField tf_quantity;
    public TextField tf_price;
    public DatePicker dp_return;
    public DatePicker dp_departure;

    @Override
    public void prepareView(String username, boolean isManager) {
        _loggedUser = username;
        _manager = isManager;
    }


    public void publishVacation(MouseEvent mouseEvent) {
        mouseEvent.consume();

        ArrayList<String> errors = new ArrayList<>();

        String destination = tf_destination.getText(),
                airline = tf_airline.getText(),
                quantity = tf_quantity.getText(),
                price = tf_price.getText();


        if (destination.isEmpty()){
            errors.add("Destination must be filled");
        }
        if (airline.isEmpty()){
            errors.add("Airline must be filled");
        }
        if (quantity.isEmpty()){
            errors.add("Quantity must be filled");
        }
        else {
            try{
                int amount = Integer.valueOf(quantity);
                if (amount < 1 ){
                    errors.add("Quantity must be an integer larger than 0");
                }
            }catch (NumberFormatException e){
                errors.add("Quantity must be an integer larger than 0");
            }
        }
        if (price.isEmpty()){
            errors.add("Price must be filled");
        }
        else {
            try {
                double dPrice = Double.valueOf(price);
                if (dPrice <= 0)
                    errors.add("Price must be a real number larger than 0");
            }catch (NumberFormatException e){
                errors.add("Price must be a real number larger than 0");
            }
        }

        if (dp_departure.getValue() == null ||
                dp_return.getValue()== null){
            errors.add("Arrival and Departure dates must be filled");
        }
        else if (dp_return.getValue().compareTo(LocalDate.now()) < 0 ||
                dp_departure.getValue().compareTo(LocalDate.now()) < 0){
            errors.add("Departure and arrival dates cant be in the past!");
        }
        else if (dp_return.getValue().compareTo(dp_departure.getValue()) < 0){
            errors.add("Arrival date must be after departure date");
        }

        if (errors.size() > 0){
            StringBuilder builder = new StringBuilder();
            for (String s :
                    errors){
                builder.append("\t->\t").append(s).append("\n");
            }
            popProblem("Please fix the following errors before we may continue:\n" + builder.toString());
        }
        else {
            Vacation toPublish = new Vacation(_loggedUser, destination, dp_departure.getValue().toString(),
                    dp_return.getValue().toString(), price, quantity, airline);

            String response = _controller.publishVacation(toPublish);

            if (response.contains("error")){
                popProblem(response);
            }
            else {
                popInfo("Vacations published!");
            }
        }
    }
}

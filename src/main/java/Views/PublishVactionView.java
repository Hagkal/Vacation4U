package Views;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PublishVactionView extends ARegisteredView {


    public Button btn_publish;
    public TextField tf_destination;
    public TextField airline;
    public TextField tf_quantity;
    public TextField tf_price;
    public DatePicker dp_arrival;
    public DatePicker dp_departure;

    @Override
    public void prepareView(String username, boolean isManager) {
        _loggedUser = username;
        _manager = isManager;
    }


}

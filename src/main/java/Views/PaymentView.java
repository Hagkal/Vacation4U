package Views;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PaymentView extends ARegisteredView {
    public ChoiceBox cb_type;
    public TextField tf_cardNum;
    public TextField tf_cardDate;
    public TextField tf_cardCVV;
    public TextField tf_username;
    public TextField tf_password;
    public Button btn_visa;
    public Button btn_paypal;

    @Override
    public void prepareView(String username, boolean isManager) {
        cb_type.getItems().add("Visa");
        cb_type.getItems().add("Paypal");

        cb_type.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                cb_type.setValue(newValue);
            }
        });

        if (cb_type.getValue().equals("Visa")){
            tf_cardNum.setDisable(false);
            tf_cardDate.setDisable(false);
            tf_cardCVV.setDisable(false);
            btn_visa.setDisable(false);
        }
        else if (cb_type.getValue().equals("Paypal")){
            tf_username.setDisable(false);
            tf_password.setDisable(false);
            btn_paypal.setDisable(false);
        }
    }

    public void payVisa (MouseEvent mouseEvent){
        if (tf_cardNum.getText().isEmpty() || tf_cardDate.getText().isEmpty() || tf_cardCVV.getText().isEmpty())
            popProblem("Please fill all the details!");
        else {
            String[] details = new String[3];
            details[0] = tf_cardNum.getText();
            details[1] = tf_cardDate.getText();
            details[2] = tf_cardCVV.getText();
            //send the details
        }
    }

    public void payPaypal (MouseEvent mouseEvent){

    }
}

package Views;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PaymentView extends ARegisteredView {
    public CheckBox cb_visa;
    public CheckBox cb_paypal;
    public TextField tf_cardNum;
    public TextField tf_cardDate;
    public TextField tf_cardCVV;
    public TextField tf_username;
    public TextField tf_password;
    public Button btn_visa;
    public Button btn_paypal;

    String _price;
    String _id;
    String _seller;

    @Override
    public void prepareView(String username, boolean isManager) {

    }

    public void setVisa (MouseEvent mouseEvent){
        if (cb_visa.isSelected()) {
            tf_cardNum.setDisable(false);
            tf_cardDate.setDisable(false);
            tf_cardCVV.setDisable(false);
            btn_visa.setDisable(false);
            tf_username.setDisable(true);
            tf_password.setDisable(true);
            btn_paypal.setDisable(true);
            cb_paypal.setSelected(false);
        }
        else{
            tf_cardNum.setDisable(true);
            tf_cardDate.setDisable(true);
            tf_cardCVV.setDisable(true);
            btn_visa.setDisable(true);
            tf_username.setDisable(true);
            tf_password.setDisable(true);
            btn_paypal.setDisable(true);
            cb_paypal.setSelected(false);
        }
    }

    public void setPaypal (MouseEvent mouseEvent){
        if (cb_paypal.isSelected()) {
            tf_cardNum.setDisable(true);
            tf_cardDate.setDisable(true);
            tf_cardCVV.setDisable(true);
            btn_visa.setDisable(true);
            tf_username.setDisable(false);
            tf_password.setDisable(false);
            btn_paypal.setDisable(false);
            cb_visa.setSelected(false);
        }
        else{
            tf_cardNum.setDisable(true);
            tf_cardDate.setDisable(true);
            tf_cardCVV.setDisable(true);
            btn_visa.setDisable(true);
            tf_username.setDisable(true);
            tf_password.setDisable(true);
            btn_paypal.setDisable(true);
            cb_paypal.setSelected(false);
        }
    }

    public void payVisa (MouseEvent mouseEvent){
        if (tf_cardNum.getText().isEmpty() || tf_cardDate.getText().isEmpty() || tf_cardCVV.getText().isEmpty())
            popProblem("Please fill all the details!");
        else {
            String response = _controller.payForVacation(_id, _loggedUser, _seller, _price);
            if (response.contains("Payed")) {
                popInfo(response);
                tf_cardNum.setText("");
                tf_cardDate.setText("");
                tf_cardCVV.setText("");
                tf_username.setText("");
                tf_password.setText("");
            }
            else if (response.equals("error"))
                popProblem(response);
        }

    }

    public void payPaypal (MouseEvent mouseEvent){
        if (tf_username.getText().isEmpty() || tf_password.getText().isEmpty())
            popProblem("Please fill all the details!");
        else {
            String response = _controller.payForVacation(_id, _loggedUser, _seller, _price);
            if (response.contains("Payed")) {
                popInfo(response);
                tf_cardNum.setText("");
                tf_cardDate.setText("");
                tf_cardCVV.setText("");
                tf_username.setText("");
                tf_password.setText("");
            }
            else if (response.equals("error"))
                popProblem(response);
        }
    }
}

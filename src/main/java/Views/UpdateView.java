package Views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.util.ArrayList;

public class UpdateView extends ARegisteredView {

    @FXML
    public Button btn_update_search;
    public Button btn_sendUpdate;
    public Button btn_cancel_update;

    public TextField tf_userNameRead;
    public TextField tf_firstName;
    public TextField tf_lastName;
    public TextField tf_hometown;
    public PasswordField pf_passwordUpdate;
    public DatePicker dp_dateUpdate;

    public Label lbl_passwordUpdateErr;
    public Label lbl_dateUpdateErr;
    public Label lbl_firstNameUpdateErr;
    public Label lbl_hometownUpdateErr;
    public Label lbl_lastNameUpdateErr;
    public Label lbl_userNameUpdateErr;


    /**
     * method to cancel update action
     * @param actionEvent - mouse click on 'abort update'
     */
    public void update_cancel(ActionEvent actionEvent) {
        btn_update_search.setDisable(false);
        tf_userNameRead.setDisable(false);

        btn_cancel_update.setDisable(true);
        btn_cancel_update.setVisible(false);

        btn_sendUpdate.setDisable(true);

        lbl_userNameUpdateErr.setVisible(false);
        lbl_firstNameUpdateErr.setVisible(false);
        lbl_lastNameUpdateErr.setVisible(false);
        lbl_passwordUpdateErr.setVisible(false);
        lbl_hometownUpdateErr.setVisible(false);
        lbl_dateUpdateErr.setVisible(false);

        tf_hometown.setText("");
        tf_hometown.setDisable(true);
        tf_lastName.setText("");
        tf_lastName.setDisable(true);
        tf_firstName.setText("");
        tf_firstName.setDisable(true);
        pf_passwordUpdate.setText("");
        pf_passwordUpdate.setDisable(true);
        dp_dateUpdate.getEditor().setText("");
        dp_dateUpdate.setPromptText("");
        dp_dateUpdate.setDisable(true);

        prepareView(_loggedUser, _manager);

        actionEvent.consume();
    }

    /**
     * method to retrieve data of a user for update
     * @param mouseEvent - mouse click on 'search' button
     */
    public void send_read_update(MouseEvent mouseEvent) {
        String s = tf_userNameRead.getText();
        if (s.isEmpty()){
            popProblem("Please insert a valid Username");
        }

//       else{
//           ArrayList<String> response = _controller.read_update_user(s);

//           if (response != null) {

//               btn_sendUpdate.setDisable(false);

//               btn_cancel_update.setDisable(false);
//               btn_cancel_update.setVisible(true);

//               btn_update_search.setDisable(true);
//               tf_userNameRead.setDisable(true);

//               pf_passwordUpdate.setDisable(false);
//               dp_dateUpdate.setDisable(false);
//               dp_dateUpdate.setEditable(false);
//               tf_firstName.setDisable(false);
//               tf_lastName.setDisable(false);
//               tf_hometown.setDisable(false);
//               pf_passwordUpdate.setText(response.get(1));
//               dp_dateUpdate.setPromptText(response.get(2));
//               tf_firstName.setText(response.get(3));
//               tf_lastName.setText(response.get(4));
//               tf_hometown.setText(response.get(5));

                prepareView(_loggedUser, _manager);

//           }
//           else{
//               popProblem("Username does not exist!");
//           }
//       }

//       mouseEvent.consume();
    }


    public void send_update(MouseEvent mouseEvent) {
        ArrayList<String> toSend = new ArrayList<String>();
        boolean allChecked = true;
        //temp string fields to get the text from the text field, will be checked before inserting to array list
        String username = tf_userNameRead.getText(),
                password = pf_passwordUpdate.getText(),
                firstname = tf_firstName.getText(),
                lastname = tf_lastName.getText(),
                hometown = tf_hometown.getText(),
                birthday = "";

        //set error labels to be not visible
        lbl_userNameUpdateErr.setVisible(false);
        lbl_firstNameUpdateErr.setVisible(false);
        lbl_lastNameUpdateErr.setVisible(false);
        lbl_passwordUpdateErr.setVisible(false);
        lbl_hometownUpdateErr.setVisible(false);
        lbl_dateUpdateErr.setVisible(false);

        if (!dp_dateUpdate.getEditor().getText().isEmpty())
            birthday = dp_dateUpdate.getEditor().getText();
        else if (!dp_dateUpdate.getPromptText().isEmpty())
            birthday = dp_dateUpdate.getPromptText();

        //username check
        if (username.isEmpty()){
            lbl_userNameUpdateErr.setVisible(true);
            allChecked = false;
        }

        //password check
        if (password.length() < 8){
            lbl_passwordUpdateErr.setVisible(true);
            allChecked = false;
        }

        //first name check
        if (firstname.length() < 2){
            lbl_firstNameUpdateErr.setVisible(true);
            allChecked = false;
        }

        //last name check
        if (lastname.length() < 2){
            lbl_lastNameUpdateErr.setVisible(true);
            allChecked = false;
        }

        //date check
 //       if (dp_dateUpdate.getValue() != null && !isBiggerThen18(dp_dateUpdate)){
 //           lbl_dateUpdateErr.setVisible(true);
 //           allChecked = false;
 //       }
//
 //       //hometown check
 //       if (hometown.length() < 2){
 //           lbl_hometownUpdateErr.setVisible(true);
 //           allChecked = false;
 //       }
//
 //       if (allChecked){
 //           toSend.add(username);
 //           toSend.add(password);
 //           toSend.add(birthday);
 //           toSend.add(firstname);
 //           toSend.add(lastname);
 //           toSend.add(hometown);
 //           _controller.update_user(username, toSend);
 //       }
 //       dp_dateUpdate.setPromptText("");
 //       dp_dateUpdate.getEditor().setText("");
    }

    @Override
    public void prepareView(String username, boolean isManager) {
        this._loggedUser = username;
        this._manager = isManager;

        if (!isManager){
            tf_userNameRead.setText(username);
            tf_userNameRead.setDisable(true);
        }
    }
}

package Controllers;

import Models.Model;
import Vacations.Vacation;
import Vacations.VacationPayment;
import Vacations.VacationRequest;

import java.util.ArrayList;

public class Controller {

    Model _model;
  
    public String create_user(ArrayList<String> toSend) {
        return _model.create_user(toSend);
    }


    public ArrayList<String> read_user(String username) {
        ArrayList<String> response = _model.read_user(username);

        if (response!=null){
            response.set(2, convertTimeToText(response.get(2)));
            return response;
        }

        else return null;
    }


    public String update_user(String username, ArrayList<String> toSend) {
        return _model.update_user(username, toSend);
    }

    public String delete_user(String username) {
        return _model.delete_user(username);
    }


    public void set_model(Model m){
        this._model = m;
    }

    /**
     * method to convert date into string
     * @param s - the DB date
     * @return - View date
     */
    private String convertTimeToText(String s) {
        StringBuilder date = new StringBuilder();
        String[] splitted = s.split("-");

        date.append(splitted[1]).append("/");
        date.append(splitted[2]).append("/");
        date.append(splitted[0]);

        return date.toString();
    }

   public String login(String username, String password) {
        return _model.login(username, password);
    }

    public String publishVacation(Vacation toPublish) {
        return _model.publishVacation(toPublish);
    }

    public ArrayList<VacationRequest> getVacationsForApproval(String username) {
        ArrayList<VacationRequest> retrieved = _model.getVacationsForApproval(username);
        return retrieved;
    }

    public String approveVacation(String username, String vacationId, String vacationBuyer){
        return _model.approveVacation(username, vacationId, vacationBuyer);
    }

    public ArrayList<Vacation> getAllVacations(String username) {
        return _model.getAllVacations(username);
    }


    public String bidVacation(String sellerName, String bidderUsername, String vacationId, String price){
        return _model.bidVacation(sellerName, bidderUsername, vacationId, price);
    }

    public ArrayList<VacationPayment> getVacationsForPayment(String username){
        return _model.getVacationsForPayment(username);
    }

    public String payForVacation(String vacationId, String username, String seller, String price){
        return _model.payForVacation(vacationId, username, seller, price);
    }


}

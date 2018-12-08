package Controllers;

import Models.Model;

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
}

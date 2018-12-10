package Models;

import Vacations.Vacation;
import Vacations.VacationApprove;
import Vacations.VacationRequest;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Model {
    //Const
    private final String DB_URL = "jdbc:sqlite:src/main/resources/DB/DataBase.db";

    // helpful attributes
    private ResultSet m_results;

    public Model() {
        // createUsersTable();
        // createVacationstable();
    }

    /**
     * a method to return a connection with the Database
     *
     * @return - a connection if success, null otherwise
     */
    private Connection make_connection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * checks if a given username already exist
     *
     * @param userName - the given username to check
     * @return - true if exist, false otherwise
     */
    private boolean user_exist(String userName) {
        String sql = "SELECT * FROM Users WHERE UserName = ?";

        try (Connection conn = this.make_connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userName);
            m_results = pstmt.executeQuery();

            if (!m_results.next()) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    /**
     * a method to create a user
     *
     * @param attributes - list of needed attributed by a specific order!
     */
    public String create_user(ArrayList<String> attributes) {

        if (user_exist(attributes.get(0))) {
            return "Username: " + attributes.get(0) + "\nalready exist!";
        }

        String sql = "INSERT INTO Users (UserName,Password,Birthday,FirstName,LastName,Hometown)"
                + " VALUES(?,?,?,?,?,?)";

        try (Connection conn = this.make_connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, attributes.get(0)); // username
            pstmt.setString(2, attributes.get(1)); // password
            pstmt.setString(3, attributes.get(2)); // birthday
            pstmt.setString(4, attributes.get(3)); // first name
            pstmt.setString(5, attributes.get(4)); // last name
            pstmt.setString(6, attributes.get(5)); // hometown

            pstmt.executeUpdate();
            return "Created :)";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "Failed :/";
        }
    }

    /**
     * a method to read a user tuple by it's UserName
     *
     * @param userName - the username desired
     */
    public ArrayList<String> read_user(String userName) {
        String sql = "SELECT * FROM Users WHERE UserName = ?";

        try (Connection conn = this.make_connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userName);
            m_results = pstmt.executeQuery();
            ArrayList<String> toReturn = new ArrayList<>();

            toReturn.add(m_results.getString(1));
            toReturn.add(m_results.getString(2));
            toReturn.add(m_results.getString(3));
            toReturn.add(m_results.getString(4));
            toReturn.add(m_results.getString(5));
            toReturn.add(m_results.getString(6));

            return toReturn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * a method to update the database
     *
     * @param toChange - username to be changed
     * @param newatt   - arraylist of attributes to update
     */
    public String update_user(String toChange, ArrayList<String> newatt) {
        // checking if the new username already exist
        if (!user_exist(toChange)) {
            return "Users: " + toChange + "\ndoes not exist!";
        }

        String sql = "UPDATE Users "
                + "SET UserName = ? , "
                + "Password = ? , "
                + "Birthday = ? , "
                + "FirstName = ? , "
                + "LastName = ? , "
                + "Hometown = ? "
                + "WHERE UserName = ?";

        try (Connection conn = this.make_connection();
        ) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, newatt.get(0));
            pstmt.setString(2, newatt.get(1));
            pstmt.setString(3, newatt.get(2));
            pstmt.setString(4, newatt.get(3));
            pstmt.setString(5, newatt.get(4));
            pstmt.setString(6, newatt.get(5));
            pstmt.setString(7, toChange);

            // update
            pstmt.executeUpdate();
            return "Update success";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "Update failed";
        }
    }

    /**
     * a method to delete user
     *
     * @param toDelete - username to delete
     */
    public String delete_user(String toDelete) {
        if (!user_exist(toDelete)) {
            return "Username: " + toDelete + "\ndoes not exist";
        }

        String sql = "DELETE FROM Users WHERE UserName = ?";

        try (Connection conn = this.make_connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, toDelete);

            // execute the delete statement
            pstmt.executeUpdate();
            return "Delete success";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "Delete failed";
        }
    }

    /**
     * method to check login
     *
     * @param username - given username
     * @param password - given password
     * @return - string of success or fail
     */
    public String login(String username, String password) {
        String sql = "SELECT * FROM Users WHERE UserName = ? AND Password = ?";

        try (Connection conn = this.make_connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            m_results = pstmt.executeQuery();
            String toReturn = "Error find";

            if (m_results.next()) {
                toReturn = "OK";
            }

            return toReturn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }


    /**
     * a method to create a vacation
     *
     * @param toPublish - object representing a vacation
     */
    public String publishVacation(Vacation toPublish) {

        String sql = "INSERT INTO Vacations (SellerName,Destination,ArrivalDate,DepartureDate,Airline,TicketAmount,Price,FlightOnly,Status)"
                + " VALUES(?,?,?,?,?,?,?,?,?)";

        try (Connection conn = this.make_connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, toPublish._sellingUser);
            pstmt.setString(2, toPublish._destination);
            pstmt.setString(3, toPublish._returnDate);
            pstmt.setString(4, toPublish._departureDate);
            pstmt.setString(5, toPublish._airline);
            pstmt.setString(6, toPublish._quantity);
            pstmt.setString(7, toPublish._price);
            pstmt.setString(8, "true");
            pstmt.setString(9, "Published");
            pstmt.executeUpdate();

            return "Created :)";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "Failed :/";
        }
    }

    /**
     * method to retrieve all vacations waiting for approval
     *
     * @param username - the username of which vacations are waiting for approval
     * @return - list of vacation requests
     */
    public ArrayList<VacationRequest> getVacationsForApproval(String username) {
        String sql = "SELECT * FROM pendingVacations WHERE SellerName = ?";

        try (Connection conn = this.make_connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            m_results = pstmt.executeQuery();

            ArrayList<VacationRequest> retrieved = new ArrayList<>();

            while (m_results.next()) {
                String vacationId = m_results.getString(1);
                String buyer = m_results.getString(2);
                String date = m_results.getString(4);

                VacationRequest r = new VacationRequest(buyer, date, vacationId);
                retrieved.add(r);
            }


            return retrieved;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * method to approve a vacation of a buyer
     *
     * @param vacationId    - the vacation id
     * @param vacationBuyer - the buyer username
     * @return - success or fail
     */
    public String approveVacation(String username, String vacationId, String vacationBuyer) {
        String sql = "UPDATE pendingVacations " +
                "SET status = ? " +
                "WHERE VacationId = ?";

        try (
                Connection conn = this.make_connection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {

            pstmt.setString(1, "payment");
            pstmt.setInt(2, Integer.valueOf(vacationId));

            pstmt.executeUpdate();


            return "Approved buyer!\n Waiting for payment";
        } catch (SQLException e) {
            System.out.println("something bad happened while trying to update pendingVacations table :(");
            System.out.println(e.getMessage());
            return "error while updating the approval";
        }
    }

    /**
     * method to retrieve all available vacations
     * @return - list of available vacations
     */
    public ArrayList<Vacation> getAllVacations(String username) {
        String sql = "SELECT * FROM Vacations WHERE Status NOT IN ('sold')";

        try (
                Connection conn = make_connection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            m_results = pstmt.executeQuery();
            ArrayList<Vacation> vacations = new ArrayList<>();

            while (m_results.next()) {
                String vacationId = m_results.getString(1);
                String seller = m_results.getString(2);
                String destination = m_results.getString(3);
                String returnDate = m_results.getString(4);
                String departureDate = m_results.getString(5);
                String airline = m_results.getString(6);
                String ticketAmount = m_results.getString(7);
                String price = m_results.getString(8);

                Vacation v = new Vacation(seller, destination, departureDate, returnDate, price, ticketAmount, airline);
                v._id = vacationId;
                vacations.add(v);
            }

            return vacations;

        } catch (SQLException e) {
            System.out.println("Something bad happaned while retrieving data from Vacations");
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * method used to make a bid on a published vacation
     * @param sellerName - the seller name of that vacation
     * @param bidderUsername - the username of the bidder
     * @param vacationId - the vacation id
     * @return - success or fail
     */
    public String bidVacation(String sellerName, String bidderUsername, String vacationId, String price) {
        String sql1 = "UPDATE Vacations "
                + "SET Status = 'bid' "
                + "WHERE VacationId = ?";

        String sql2 = "INSERT INTO pendingVacations (VacationId,SellerName,PotentialBuyerName,bidedAt,Price,Status)"
                + " VALUES(?,?,?,?,?,?)";


        try (
                Connection conn = make_connection();
                PreparedStatement ps1 = conn.prepareStatement(sql1);
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ){

            ps1.setInt(1, Integer.valueOf(vacationId));
            ps2.setInt(1, Integer.valueOf(vacationId));
            ps2.setString(2, sellerName);
            ps2.setString(3, bidderUsername);
            ps2.setString(4, LocalDate.now().toString());
            ps2.setString(5, price);
            ps2.setString(6, "waiting");

            ps1.executeUpdate();
            ps2.executeUpdate();

            return "Bid success";

        }
        catch (SQLException e){
            System.out.println("something bad happened while inserting into pendingVacations :(");
            System.out.println(e.getMessage());
            return "error";
        }
    }

    /**
     *
     * @param username - the username of which to get the status
     * @return - list of vacations for payment
     */
    public ArrayList<VacationApprove> getVacationsForPayment(String username){
        String sql = "SELECT * FROM pendingVacations WHERE potentialBuyerName = ? AND " +
                "status = payment";

        try (
                Connection conn = make_connection();
                PreparedStatement pstm = conn.prepareStatement(sql);
                ){

            pstm.setString(1, username);
            m_results = pstm.executeQuery();

            ArrayList<VacationApprove> forPayment = new ArrayList<>();
            while (m_results.next()){
                String vID = m_results.getString(1);
                String sellerName = m_results.getString(3);
                String date = m_results.getString(4);
                String price = m_results.getString(5);

                forPayment.add(new VacationApprove(vID,sellerName,date, price));

            }

            return forPayment;
        }
        catch (SQLException e){
            System.out.println("something bad happened while retrieving data from pendingVacations");
            System.out.println(e.getMessage());
            return null;
        }
    }


    public String payForVacation(String vacationId, String username){
        String sql = "DELETE FROM pendingVacations WHERE VacationId = ? AND potentialBuyerName = ?";
        String sql2 = "UPDATE Vacations SET Status = 'sold'";

        try (
                Connection conn = make_connection();
                PreparedStatement pst1 = conn.prepareStatement(sql);
                PreparedStatement pst2 = conn.prepareStatement(sql2);
                ){

            pst1.setInt(1, Integer.valueOf(vacationId));
            pst1.setString(2, username);

            pst1.executeUpdate();
            pst2.executeUpdate();

            return "Payed success";

        }
        catch (SQLException e){
            System.out.println("something bad happened while updating payment");
            System.out.println(e.getMessage());
            return "error";
        }
    }




    public static void main(String[] args){
        Model m = new Model();

      //  m.bidVacation("gg", "tt", "2", "26");
        //m.approveVacation("tt", "5", "gg");
        m.payForVacation("5", "gg");
    }
}

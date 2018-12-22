package Vacations;

/**
 * this is the Vacations type class.
 */
public class Vacation {
    public String _id;
    public String _departureDate;
    public String _returnDate;
    public String _price;
    public String _sellingUser;
    public String _destination;
    public String _airline;
    public String _quantity;
    public String _forTrade;

    /** Vacations type constructor
     * @param destination vacation destination
     * @param price the price that the seller demand for the vacation
     * @param user is the seller
     */
    public Vacation(String user, String destination, String departureDate, String returnDate, String price , String quantity, String airline ){
        _sellingUser = user;
        _destination = destination;
        _departureDate = departureDate;
        _returnDate = returnDate;
        _price = price;
        _quantity = quantity;
        _airline = airline;



    }
}

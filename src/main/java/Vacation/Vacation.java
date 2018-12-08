package Vacation;

import User.User;

import java.util.Date;

/**
 * this is the Vacation type class.
 */
public class Vacation {
    private Date date;
    private double price;
    private User sellingUser;
    private String destination;
    private boolean onlyForFlight;

    /** Vacation type constructor
     * @param destination vacation destination
     * @param date the date that the vacation will attend
     * @param price the price that the seller demand for the vacation
     * @param user is the seller
     */
    public Vacation(String destination, Date date, double price , User user,boolean onlyForFlight){
        this.sellingUser = user;
        this.onlyForFlight = onlyForFlight;
        this.date = date;
        this.destination = destination;
        this.price = price;

    }

    public Date getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public User getSellingUser() {
        return sellingUser;
    }

    public String getDestination() {
        return destination;
    }

    public boolean isOnlyForFlight() {
        return onlyForFlight;
    }
}

package fr.efrei.domain;

import java.time.LocalDate;
import java.util.Date;

public class Booking {
    private int bookingId;
    private Room room;
    private Customer customer;
    private Date arrivalDate;
    private Date departureDate;
    private Employee employee;
    private int nbPeople;
    private double totalPrice;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    //future potential feature to implement : nb of people for the booking that should be compared (inferior or equal)
    //to the capacity of the room

    public Booking(int i, Customer customer, Room selectedRoom, LocalDate arrivalDate, LocalDate departureDate, int nbPeople){ }
    public Booking(Room room, Customer customer, Date arrivalDate, Date departureDate, Employee employee, int nbPeople, double totalPrice) {
        this.room = room;
        this.customer = customer;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.employee = employee;
        this.nbPeople = nbPeople;
        this.totalPrice = totalPrice;
    }

    public Room getRoom() {
        return room;
    }
    public Customer getCustomer() {
        return customer;
    }
    public Date getArrivalDate() {
        return arrivalDate;
    }
    public Date getDepartureDate() { return departureDate; }
    public Employee getEmployee() { return employee; }
    public int getNbPeople() { return nbPeople; }
    public double getTotalPrice() { return totalPrice; }
    public int getBookingId() {return bookingId; }

    @Override
    public String toString() {
        return "Booking{" +
                "room=" + room +
                ", customer=" + customer +
                ", arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                ", employee=" + employee +
                ", nbPeople=" + nbPeople +
                ", totalPrice=" + totalPrice +
                '}';
    }

}

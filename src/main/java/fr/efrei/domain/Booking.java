package fr.efrei.domain;

import java.time.LocalDate;

public class Booking {
    private String bookingId;
    private Room room;
    private Customer customer;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private Employee employee;
    private int nbPeople;
    private double totalPrice;

    private Booking(){}

    private Booking(BuilderBooking builder) {
        this.bookingId=builder.bookingId;
        this.room = builder.room;
        this.customer = builder.customer;
        this.arrivalDate = builder.arrivalDate;
        this.departureDate = builder.departureDate;
        this.employee = builder.employee;
        this.nbPeople = builder.nbPeople;
        this.totalPrice = builder.totalPrice;
    }

    public Room getRoom() {
        return room;
    }
    public Customer getCustomer() {
        return customer;
    }
    public LocalDate getArrivalDate() {
        return arrivalDate;
    }
    public LocalDate getDepartureDate() { return departureDate; }
    public Employee getEmployee() { return employee; }
    public int getNbPeople() { return nbPeople; }
    public double getTotalPrice() { return totalPrice; }
    public String getBookingId() {return bookingId; }

    @Override
    public String toString() {
        return "Booking{" +
                "booking id=" + bookingId +
                "room number=" + room.getRoomNumber() +
                ", customer=" + customer.getIdNumber() +
                ", arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                ", employee=" + employee.getEmployeeNumber() +
                ", nbPeople=" + nbPeople +
                ", totalPrice=" + totalPrice +
                '}';
    }
    public static class BuilderBooking {
        private String bookingId;
        private Room room;
        private Customer customer;
        private LocalDate arrivalDate;
        private LocalDate departureDate;
        private Employee employee;
        private int nbPeople;
        private double totalPrice;

        public BuilderBooking setBookingId(String bookingId) {
            this.bookingId = bookingId;
            return this;
        }

        public BuilderBooking setRoom(Room room) {
            this.room = room;
            return this;
        }

        public BuilderBooking setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public BuilderBooking setArrivalDate(LocalDate arrivalDate) {
            this.arrivalDate = arrivalDate;
            return this;
        }

        public BuilderBooking setDepartureDate(LocalDate departureDate) {
            this.departureDate = departureDate;
            return this;
        }

        public BuilderBooking setEmployee(Employee employee) {
            this.employee = employee;
            return this;
        }

        public BuilderBooking setNbPeople(int nbPeople) {
            this.nbPeople = nbPeople;
            return this;
        }

        public BuilderBooking setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }
        public BuilderBooking copy(Booking booking){
            this.bookingId=booking.getBookingId();
            this.room = booking.getRoom();
            this.customer = booking.getCustomer();
            this.arrivalDate = booking.getArrivalDate();
            this.departureDate = booking.getDepartureDate();
            this.employee = booking.getEmployee();
            this.nbPeople = booking.getNbPeople();
            this.totalPrice = booking.getTotalPrice();
            return this;
        }
        public Booking build(){
            return new Booking(this);
        }
    }


}

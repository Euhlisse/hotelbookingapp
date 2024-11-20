package fr.efrei.factory;
import fr.efrei.domain.Booking;
import fr.efrei.domain.Customer;
import fr.efrei.domain.Employee;
import fr.efrei.domain.Room;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BookingFactory {
    public static Booking createBooking(Room room, Customer customer, LocalDate arrivalDate, LocalDate departureDate, Employee employee, int nbPeople, double totalPrice){
        String bookingId = Helper.generateId();
        if (Helper.isNullOrEmpty(bookingId)
                ||Helper.isNullOrEmpty(String.valueOf(room))
                ||Helper.isNullOrEmpty(String.valueOf(customer))
                ||Helper.isNullOrEmpty(String.valueOf(arrivalDate))
                ||Helper.isNullOrEmpty(String.valueOf(departureDate))
                ||Helper.isNullOrEmpty(String.valueOf(employee))
                ||Helper.isNullOrEmpty(String.valueOf(nbPeople))
                ||Helper.isNullOrEmpty(String.valueOf(totalPrice))){
            return null;
        }
        if(arrivalDate.isBefore(LocalDate.now())||departureDate.isBefore(LocalDate.now())){
            return null;
        }
        if (arrivalDate.isAfter(departureDate)||arrivalDate.equals(departureDate)){
            return null;
        }
        if (nbPeople<=0||totalPrice<room.getNightPrice()*(ChronoUnit.DAYS.between(arrivalDate, departureDate))){
            return null;
        }
        return new Booking.BuilderBooking()
                .setBookingId(bookingId)
                .setRoom(room)
                .setCustomer(customer)
                .setArrivalDate(arrivalDate)
                .setDepartureDate(departureDate)
                .setEmployee(employee)
                .setNbPeople(nbPeople)
                .setTotalPrice(totalPrice)
                .build();
    }
}

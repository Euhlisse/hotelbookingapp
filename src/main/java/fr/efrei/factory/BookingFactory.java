package fr.efrei.factory;

import fr.efrei.domain.*;
import fr.efrei.repository.BookingRepository;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BookingFactory {
    public static Booking createBooking(Room room, Customer customer, LocalDate arrivalDate, LocalDate departureDate, Employee employee, int nbPeople){
        String bookingId = Helper.generateBookingId(room,arrivalDate,departureDate,customer);
        if (Helper.isNullOrEmpty(bookingId)
                ||Helper.isNullOrEmpty(String.valueOf(room))
                ||Helper.isNullOrEmpty(String.valueOf(customer))
                ||Helper.isNullOrEmpty(String.valueOf(arrivalDate))
                ||Helper.isNullOrEmpty(String.valueOf(departureDate))
                ||Helper.isNullOrEmpty(String.valueOf(employee))
                ||Helper.isNullOrEmpty(String.valueOf(nbPeople))){
            return null;
        }
        if(arrivalDate.isBefore(LocalDate.now())||departureDate.isBefore(LocalDate.now())){
            System.out.println("cant reserve for the past, have to move forward...");
            return null;
        }
        if (arrivalDate.isAfter(departureDate)||departureDate.isBefore(arrivalDate)){
            System.out.println("cant leave before you arrive...");
            return null;
        }
        if (nbPeople<=0){
            System.out.println("there has to be people");
            return null;
        }
        if(BookingRepository.getRepository().getAll().stream()
                .filter(booking -> booking.getRoom().equals(room))
                .anyMatch(booking ->
                        (booking.getArrivalDate().isAfter(arrivalDate)&&booking.getArrivalDate().isBefore(departureDate)
                                ||(booking.getDepartureDate().isAfter(arrivalDate)&&booking.getDepartureDate().isBefore(departureDate))
                                ||(booking.getArrivalDate().equals(arrivalDate))
                                ||booking.getDepartureDate().equals(departureDate))))
        {
            System.out.println("the room is already reserved");
            return null;
        }

        double totalPrice = room.getNightPrice()*(ChronoUnit.DAYS.between(arrivalDate, departureDate));
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
    public static Booking createBooking(String bookingId,Room room, Customer customer, LocalDate arrivalDate, LocalDate departureDate, Employee employee, int nbPeople){
        if (Helper.isNullOrEmpty(bookingId)
                ||Helper.isNullOrEmpty(String.valueOf(room))
                ||Helper.isNullOrEmpty(String.valueOf(customer))
                ||Helper.isNullOrEmpty(String.valueOf(arrivalDate))
                ||Helper.isNullOrEmpty(String.valueOf(departureDate))
                ||Helper.isNullOrEmpty(String.valueOf(employee))
                ||Helper.isNullOrEmpty(String.valueOf(nbPeople))){
            return null;
        }
        if(arrivalDate.isBefore(LocalDate.now())||departureDate.isBefore(LocalDate.now())){
            System.out.println("cant reserve for the past, have to move forward...");
            return null;
        }
        if (arrivalDate.isAfter(departureDate)||arrivalDate.equals(departureDate)){
            System.out.println("cant leave before you arrive...");
            return null;
        }
        if (nbPeople<=0){
            System.out.println("there has to be people");
            return null;
        }
        if(BookingRepository.getRepository().getAll().stream()
                .filter(booking -> booking.getRoom().equals(room))
                .anyMatch(booking ->
                        (booking.getArrivalDate().isAfter(arrivalDate)&&booking.getArrivalDate().isBefore(departureDate)
                                ||(booking.getDepartureDate().isAfter(arrivalDate)&&booking.getDepartureDate().isBefore(departureDate))
                                ||(booking.getArrivalDate().equals(arrivalDate))
                                ||booking.getDepartureDate().equals(departureDate))))
        {
            System.out.println("the room is already reserved");
            return null;
        }
        double totalPrice = room.getNightPrice()*(ChronoUnit.DAYS.between(arrivalDate, departureDate));
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

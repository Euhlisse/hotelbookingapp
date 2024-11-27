package fr.efrei.repository;

import fr.efrei.domain.Booking;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookingRepository implements IBookingRepository{

    private static IBookingRepository repository = null;
    private List<Booking> bookingList;
    private BookingRepository(){bookingList=new ArrayList<Booking>();}
    public static IBookingRepository getRepository(){
        if (repository==null){
            repository = new BookingRepository();
        }
        return repository;
    }

    @Override
    public Booking create(Booking booking) {
        boolean success =  bookingList.add(booking);
        if (success)
            return booking;
        return null;
    }

    @Override
    public Booking read(String s) {
        Optional<Booking> foundBooking = bookingList.stream().filter(booking -> booking.getBookingId().equals(s))
                .findFirst();
        return foundBooking.orElse(null);
    }

    @Override
    public Booking update(Booking booking) {
        String id = booking.getBookingId();
        Booking oldBooking = read(id);
        if (oldBooking==null)
            return null;
        boolean success = delete(id);
        if (success){
            if(bookingList.add(booking)){
                return booking;
            }
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        return (bookingList.remove(read(id)));
    }

    @Override
    public List<Booking> getAll() {
        return bookingList;
    }
}

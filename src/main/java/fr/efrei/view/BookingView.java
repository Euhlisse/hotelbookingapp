package fr.efrei.view;
import fr.efrei.domain.*;
import fr.efrei.factory.BookingFactory;
import fr.efrei.factory.Helper;
import fr.efrei.repository.IBookingRepository;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class BookingView {
    protected static IBookingRepository bookingRepository;
    public BookingView(){}
    public BookingView(IBookingRepository bookingRepository){
        this.bookingRepository =bookingRepository;
    }
    public static void createBooking(){
        Room room = RoomView.roomRepository.read(RoomView.selectRoomId());
        Customer customer = CustomerView.customerRepository.read(CustomerView.selectCustomerId());
        String arrivalDateString = JOptionPane.showInputDialog("Enter arrival Date (YYYY-MM-DD)");
        String departureDateString = JOptionPane.showInputDialog("Enter departure Date (YYYY-MM-DD)");
        LocalDate arrivalDate=null;
        LocalDate departureDate=null;
        if (Helper.respectsDateFormat(arrivalDateString)&&Helper.respectsDateFormat(departureDateString)) {
            arrivalDate = LocalDate.parse(arrivalDateString);
            departureDate = LocalDate.parse(arrivalDateString);
        }
        Employee employee = EmployeeView.employeeRepository.read(EmployeeView.selectEmployeeId());
        int nbPeople = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of people for the reservation"));
        if (nbPeople>room.getCapacity()||nbPeople<=0){
            JOptionPane.showMessageDialog(null,"invalid value, can't create your booking");
            return;
        }
        Booking booking = BookingFactory.createBooking(room,customer,arrivalDate,departureDate,employee,nbPeople);
        if (booking == null){
            System.out.println("unable to create a booking, wrongs inputs");
            return;
        }
        bookingRepository.create(booking);
        JOptionPane.showMessageDialog(null, "Booking created successfully!");
    }
    public static void searchBooking(){
        JOptionPane.showMessageDialog(null,bookingRepository.read(selectBookingId()));
    }
    public static void updateBooking() {
        Booking oldBooking = bookingRepository.read(selectBookingId());
        String[] updateFields = {"Room", "Customer", "Arrival date", "Departure date", "Employee", "Number of people"};
        int fieldChoice = JOptionPane.showOptionDialog(
                null,
                "Select the field to update",
                "Update Booking",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                updateFields,
                updateFields[0]
        );
        Booking newBooking = switch (fieldChoice) {
            case 0 -> {
                Room newRoom = RoomView.roomRepository.read(RoomView.selectRoomId());
                yield BookingFactory.createBooking(oldBooking.getBookingId(),newRoom, oldBooking.getCustomer(), oldBooking.getArrivalDate(), oldBooking.getDepartureDate(), oldBooking.getEmployee(), oldBooking.getNbPeople());
            }
            case 1 -> {
                Customer newCustomer = CustomerView.customerRepository.read(CustomerView.selectCustomerId());
                yield BookingFactory.createBooking(oldBooking.getBookingId(),oldBooking.getRoom(), newCustomer, oldBooking.getArrivalDate(), oldBooking.getDepartureDate(), oldBooking.getEmployee(), oldBooking.getNbPeople());
            }
            case 2 -> {
                String newArrivalDateString = JOptionPane.showInputDialog("Enter the new arrival date (YYYY-MM-DD)");
                if (Helper.respectsDateFormat(newArrivalDateString)) {
                    LocalDate newArrivalDate = LocalDate.parse(newArrivalDateString);
                    yield BookingFactory.createBooking(oldBooking.getBookingId(), oldBooking.getRoom(), oldBooking.getCustomer(), newArrivalDate, oldBooking.getDepartureDate(), oldBooking.getEmployee(), oldBooking.getNbPeople());
                } else {
                    yield null;
                }
            }
            case 3 -> {
                String newDepartureDateString = JOptionPane.showInputDialog("Enter the new departure date (YYYY-MM-DD)");
                if (Helper.respectsDateFormat(newDepartureDateString)) {
                    LocalDate newDepartureDate = LocalDate.parse(newDepartureDateString);
                    yield BookingFactory.createBooking(oldBooking.getBookingId(), oldBooking.getRoom(), oldBooking.getCustomer(), oldBooking.getArrivalDate(), newDepartureDate, oldBooking.getEmployee(), oldBooking.getNbPeople());
                } else {
                    yield null;
                }
            }
            case 4 -> {
                Employee newEmployee = EmployeeView.employeeRepository.read(EmployeeView.selectEmployeeId());
                yield BookingFactory.createBooking(oldBooking.getBookingId(), oldBooking.getRoom(), oldBooking.getCustomer(), oldBooking.getArrivalDate(), oldBooking.getDepartureDate(), newEmployee, oldBooking.getNbPeople());
            }
            case 5 -> {
                int newNumberPeople = Integer.parseInt(JOptionPane.showInputDialog("enter the new number of people for the booking"));
                if (newNumberPeople > oldBooking.getRoom().getCapacity() || newNumberPeople <= 0) {
                    JOptionPane.showMessageDialog(null,"invalid value, can't update the booking");
                    yield null;
                }
                yield BookingFactory.createBooking(oldBooking.getBookingId(), oldBooking.getRoom(), oldBooking.getCustomer(), oldBooking.getArrivalDate(), oldBooking.getDepartureDate(), oldBooking.getEmployee(), newNumberPeople);
            }
            default -> null;
        };
        if(newBooking==null){
            JOptionPane.showMessageDialog(null,"can't create the booking, please retry");
        }
        bookingRepository.update(newBooking);
    }

    public static void deleteBooking(){bookingRepository.delete(selectBookingId());}

    public static void showBookings(){
        StringBuilder stringBuilder = new StringBuilder();
        bookingRepository.getAll().forEach((booking)->stringBuilder.append(booking.toString()+"\n"));
        JOptionPane.showMessageDialog(null,stringBuilder.toString());
    }

    public static String selectBookingId(){
        List<String> bookingIds = bookingRepository.getAll().stream()
                .map(Booking::getBookingId)
                .toList();
        int bookingChoice = JOptionPane.showOptionDialog(
                null,
                "Select your id",
                "booking login",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                bookingIds.toArray(),
                bookingIds.getFirst()
        );
        return bookingIds.get(bookingChoice);
    }
    public static void bookingMenu(){
        String[] menuChoices = {
                "Create an booking",
                "Search an booking",
                "Update an booking",
                "Delete an booking",
                "Show booking informations"
        };
        int typeChoice = JOptionPane.showOptionDialog(
                null,
                "Select the new booking type",
                "Update booking Type",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                menuChoices,
                menuChoices[0]
        );
        switch (typeChoice){
            case 0 -> createBooking();
            case 1 -> searchBooking();
            case 2 -> updateBooking();
            case 3 -> deleteBooking();
            case 4 -> showBookings();
            default -> JOptionPane.showMessageDialog(null,"Selection error");
        }
    }

}



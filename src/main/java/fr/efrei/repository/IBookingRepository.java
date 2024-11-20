package fr.efrei.repository;

import fr.efrei.domain.Booking;
import java.util.*;


public interface IBookingRepository extends IRepository<Booking,String>{
    public List<Booking> getAll();

}

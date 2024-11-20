package fr.efrei.repository;

import fr.efrei.domain.Booking;

import java.util.Comparator;

public interface IRepository <T,ID> {

    T create(T t);
    T read(ID id);
    T update(T t);
    boolean delete (ID id);
}

package fr.efrei.repository;

import fr.efrei.domain.Customer;
import java.util.*;

public interface ICustomerRepository extends IRepository<Customer, String>{
    public List<Customer> getAll();
}

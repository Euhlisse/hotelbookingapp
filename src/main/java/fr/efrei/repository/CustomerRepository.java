package fr.efrei.repository;

import fr.efrei.domain.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepository implements ICustomerRepository{
    private static ICustomerRepository repository = null;
    private List<Customer> customerList;
    private CustomerRepository(){customerList=new ArrayList<Customer>();}
    public static ICustomerRepository getRepository(){
        if (repository == null){
            repository = new CustomerRepository();
        }
        return repository;
    }

    @Override
    public Customer create(Customer customer){
        boolean success = customerList.add(customer);
        if (success)
            return customer;
        return null;
    }

    @Override
    public Customer read(String s){
        Optional<Customer> foundCustomer = customerList.stream().filter(customer -> customer.getIdNumber().equals(s)).findFirst();
        return foundCustomer.orElse(null);
    }

    @Override
    public Customer update(Customer customer){
        String idNumber = customer.getIdNumber();
        Customer oldCustomer = read(idNumber);
        if (oldCustomer == null)
            return null;
        boolean success = delete(idNumber);
        if (success){
            if(customerList.add(customer))
                return customer;
        }
        return null;
    }

    @Override
    public boolean delete(String idNumber){
        return (customerList.remove(read(idNumber)));
    }

    @Override
    public List<Customer> getAll(){
        return customerList;
    }
}

package fr.efrei.repository;

import fr.efrei.domain.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository implements IEmployeeRepository{
    private static IEmployeeRepository repository = null;
    private List<Employee> employeeList;
    private EmployeeRepository(){employeeList=new ArrayList<Employee>();}
    public static IEmployeeRepository getRepository(){
        if (repository == null){
            repository = new EmployeeRepository();
        }
        return repository;
    }

    @Override
    public Employee create(Employee employee){
        boolean success = employeeList.add(employee);
        if (success)
            return employee;
        return null;
    }

    @Override
    public Employee read(String s){
        Optional<Employee> foundEmployee = employeeList.stream().filter(employee -> employee.getEmployeeNumber().equals(s)).findFirst();
        return foundEmployee.orElse(null);
    }

    @Override
    public Employee update(Employee employee){
        String employeeNumber = employee.getEmployeeNumber();
        Employee oldemployee = read(employeeNumber);
        if (oldemployee == null)
            return null;
        boolean success = delete(employeeNumber);
        if (success){
            if(employeeList.add(employee))
                return employee;
        }
        return null;
    }

    @Override
    public boolean delete(String employeeNumber){
        return (employeeList.remove(read(employeeNumber)));
    }

    @Override
    public List<Employee> getAll(){
        return employeeList;
    }
}
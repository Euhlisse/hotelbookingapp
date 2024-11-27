package fr.efrei.repository;

import fr.efrei.domain.Employee;
import java.util.*;
public interface IEmployeeRepository extends IRepository<Employee, String>{
    public List<Employee> getAll();

}

package fr.efrei.view;

import fr.efrei.domain.Employee;
import fr.efrei.domain.EmployeeType;
import fr.efrei.factory.EmployeeFactory;
import fr.efrei.factory.Helper;
import fr.efrei.repository.IEmployeeRepository;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class EmployeeView {
    protected static IEmployeeRepository employeeRepository;
    public EmployeeView(){}

    public EmployeeView(IEmployeeRepository employeeRepository){
        this.employeeRepository =employeeRepository;
    }

    public static void createEmployee() {
        String employeeId = JOptionPane.showInputDialog("Enter Employee ID");
        String firstName = JOptionPane.showInputDialog("Enter First Name");
        String lastName = JOptionPane.showInputDialog("Enter Last Name");
        String birthDateStr = JOptionPane.showInputDialog("Enter Birth Date (yyyy-mm-dd)");
        LocalDate birthDate = LocalDate.parse(birthDateStr);

        EmployeeType[] employeeTypes = EmployeeType.values();
        String[] employeeTypeNames = Arrays.stream(employeeTypes)
                .map(EmployeeType::name)
                .toArray(String[]::new);
        String selectedType = (String) JOptionPane.showInputDialog(
                null,
                "Select Employee Type",
                "Employee Type",
                JOptionPane.QUESTION_MESSAGE,
                null,
                employeeTypeNames,
                employeeTypeNames[0]
        );
        EmployeeType employeeType = EmployeeType.valueOf(selectedType);

        String password = JOptionPane.showInputDialog("Enter Password");

        Employee newEmployee = EmployeeFactory.createEmployee(
                employeeId,
                firstName,
                lastName,
                birthDate,
                employeeType,
                password
        );

        if (newEmployee == null) {
            JOptionPane.showMessageDialog(null, "Failed to create employee. Please check the inputs.");
            return;
        }
        employeeRepository.create(newEmployee);
        JOptionPane.showMessageDialog(null, "Employee created successfully!");
    }

    public static void searchEmployee(){
        String idSearch = JOptionPane.showInputDialog("Enter the id of the employee you want to search");
        JOptionPane.showMessageDialog(null,employeeRepository.read(idSearch));
    }
    public static void updateEmployee() {
        String oldEmpId = selectEmployeeId();
        Employee oldEmp = employeeRepository.read(oldEmpId);

        String[] fieldUpdate = { "First Name", "Last Name", "Birth Date", "Employee Type", "Password" };

        int fieldChoice = JOptionPane.showOptionDialog(
                null, "Select the field to update", "Update Employee", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, fieldUpdate, fieldUpdate[0]
        );

        Employee newEmp = null;
        switch (fieldChoice) {
            case 0:
                // update first name
                String newFirstName = JOptionPane.showInputDialog("Enter the new first name");
                if (!Helper.isNullOrEmpty(newFirstName))
                    newEmp = EmployeeFactory.createEmployee(oldEmp.getEmployeeNumber(), newFirstName, oldEmp.getLastName(), oldEmp.getBirthDate(), oldEmp.getEmployeeType(), oldEmp.getPassword());
                break;
            case 1:
                String newLastName = JOptionPane.showInputDialog("Enter the new last name");
                if (!Helper.isNullOrEmpty(newLastName))
                    newEmp = EmployeeFactory.createEmployee(oldEmp.getEmployeeNumber(), oldEmp.getFirstName(), newLastName, oldEmp.getBirthDate(), oldEmp.getEmployeeType(), oldEmp.getPassword());
                break;
            case 2:
                // update birthdate
                String newBirthDateString = JOptionPane.showInputDialog("Enter the new birth date (YYYY-MM-DD)");
                if (!Helper.isNullOrEmpty(newBirthDateString)&&Helper.respectsDateFormat(newBirthDateString)) {
                    LocalDate newBirthDate = LocalDate.parse(newBirthDateString);
                    newEmp = EmployeeFactory.createEmployee(oldEmp.getEmployeeNumber(), oldEmp.getFirstName(), oldEmp.getLastName(), newBirthDate, oldEmp.getEmployeeType(), oldEmp.getPassword());
                }
                break;
            case 3:
                // update employee type
                String[] employeeTypes = { "DIRECTOR", "FRONT_DESK_AGENT", "STAFF_MEMBER" };
                int typeChoice = JOptionPane.showOptionDialog(null, "Select the new employee type", "Update Employee Type", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, employeeTypes, employeeTypes[0]);
                EmployeeType newType = EmployeeType.valueOf(employeeTypes[typeChoice]);
                newEmp = EmployeeFactory.createEmployee(oldEmp.getEmployeeNumber(), oldEmp.getFirstName(), oldEmp.getLastName(), oldEmp.getBirthDate(), newType, oldEmp.getPassword());
                break;
            case 4:
                // update password
                String newPassword = JOptionPane.showInputDialog("Enter the new password");
                if (!Helper.isNullOrEmpty(newPassword))
                    newEmp = EmployeeFactory.createEmployee(oldEmp.getEmployeeNumber(), oldEmp.getFirstName(), oldEmp.getLastName(), oldEmp.getBirthDate(), oldEmp.getEmployeeType(), newPassword);
                break;
            default:
                JOptionPane.showMessageDialog(null, "No field selected to update.");
                return;
        }
        if (newEmp == null) {
            JOptionPane.showMessageDialog(null, "Invalid input or no changes made.");
            return;
        }
        employeeRepository.update(newEmp);
        JOptionPane.showMessageDialog(null, "Employee updated successfully!");
    }
    public static void deleteEmployee(){employeeRepository.delete(selectEmployeeId());}

    public static void showEmployees(){
        StringBuilder stringBuilder = new StringBuilder();
        employeeRepository.getAll().forEach((employee -> stringBuilder.append(employee.toString()+"\n")));
        JOptionPane.showMessageDialog(null,stringBuilder.toString());
    }

    public static String selectEmployeeId(){
        List<String> employeeId = employeeRepository.getAll()
                .stream()
                .map(Employee::getEmployeeNumber)
                .toList();

        int employeeChoice=JOptionPane.showOptionDialog(
                null,
                "Select your id",
                "Employee login",
                0,
                3,
                null,
                employeeId.toArray(),
                employeeId.get(0)
        );
        return employeeId.get(employeeChoice);
    }
    public static void employeeMenu(){
        String[] menuChoices = {
                "Create an Employee",
                "Search an Employee",
                "Update an Employee",
                "Delete an Employee",
                "Show Employee informations"
        };
        int typeChoice = JOptionPane.showOptionDialog(
                null,
                "Select the new employee type",
                "Update Employee Type",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                menuChoices,
                menuChoices[0]
        );
        switch (typeChoice){
            case 0 -> createEmployee();
            case 1 -> searchEmployee();
            case 2 -> updateEmployee();
            case 3 -> deleteEmployee();
            case 4 -> showEmployees();
            default -> JOptionPane.showMessageDialog(null,"Selection error");
        }
    }
}

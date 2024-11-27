package fr.efrei.view;
import fr.efrei.domain.Customer;
import fr.efrei.factory.CustomerFactory;
import fr.efrei.factory.Helper;
import fr.efrei.repository.ICustomerRepository;
import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class CustomerView {
    protected static ICustomerRepository customerRepository;
    public CustomerView(){}
    public CustomerView(ICustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    public static void createCustomer() {
        String firstName = JOptionPane.showInputDialog("Enter First Name");
        String lastName = JOptionPane.showInputDialog("Enter Last Name");
        String email = JOptionPane.showInputDialog("Enter Email");
        String phone = JOptionPane.showInputDialog("Enter Phone");
        String birthDateStr = JOptionPane.showInputDialog("Enter Birth Date (yyyy-mm-dd)");
        LocalDate birthDate = LocalDate.parse(birthDateStr);
        Customer newCustomer = CustomerFactory.createCustomer(firstName,lastName,phone,email,birthDate);
        if (newCustomer == null) {
            JOptionPane.showMessageDialog(null, "Failed to create customer. Please check the inputs.");
            return;
        }
        customerRepository.create(newCustomer);
        JOptionPane.showMessageDialog(null, "Customer created successfully!");
    }
    public static void searchCustomer(){
        String idSearch = JOptionPane.showInputDialog("Enter the id of the customer you want to search");
        System.out.println(customerRepository.read(idSearch));
    }
    public static void deleteCustomer(){
        customerRepository.delete(selectCustomerId());
    }
    public static void showCustomers(){
        customerRepository.getAll().forEach(System.out::println);
    }

    public static String selectCustomerId(){
        List<String> customerId = customerRepository.getAll()
                .stream()
                .map(Customer::getIdNumber)
                .toList();

        int CustomerChoice=JOptionPane.showOptionDialog(
                null,
                "Select your id",
                "Customer login",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                customerId.toArray(),
                customerId.getFirst()
        );
        return customerId.get(CustomerChoice);
    }

    public static void updateCustomer(){
        String oldCustId = selectCustomerId();
        Customer oldCust = customerRepository.read(oldCustId);
        String[] fieldUpdate = { "First Name", "Last Name", "Email address", "Phone number", "Birth Date" };

        int fieldChoice = JOptionPane.showOptionDialog(
                null, "Select the field to update", "Update Customer", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, fieldUpdate, fieldUpdate[0]
        );

        Customer newCust = null;
        switch (fieldChoice) {
            case 0:
                // update first name
                String newFirstName = JOptionPane.showInputDialog("Enter the new first name");
                if (!Helper.isNullOrEmpty(newFirstName)) {
                    newCust = CustomerFactory.createCustomer(newFirstName,oldCust.getLastName(),oldCust.getEmail(),oldCust.getPhone(),oldCust.getBirthDate());
                }
                break;
            case 1:
                // update last name
                String newLastName = JOptionPane.showInputDialog("Enter the new last name");
                if (!Helper.isNullOrEmpty(newLastName))
                    newCust = CustomerFactory.createCustomer(oldCust.getFirstName(),newLastName,oldCust.getEmail(),oldCust.getPhone(),oldCust.getBirthDate());
                break;
            case 2:
                // update email
                String newEmail = JOptionPane.showInputDialog("Enter the new email");
                if (!Helper.isNullOrEmpty(newEmail)&&Helper.respectsEmailFormat(newEmail))
                    newCust = CustomerFactory.createCustomer(oldCust.getFirstName(),oldCust.getLastName(),newEmail,oldCust.getPhone(),oldCust.getBirthDate());
                break;
            case 3:
                // update phone number
                String newPhoneNumber = JOptionPane.showInputDialog("Enter the new phone number");
                if(!Helper.isNullOrEmpty(newPhoneNumber)&&Helper.respectsPhoneNumberFormat(newPhoneNumber))
                    newCust = CustomerFactory.createCustomer(oldCust.getFirstName(),oldCust.getLastName(),oldCust.getEmail(),newPhoneNumber,oldCust.getBirthDate());
                break;
            case 4:
                // update birthdate
                String newBirthDateString = JOptionPane.showInputDialog("Enter the new birth date (YYYY-MM-DD)");
                if (!Helper.isNullOrEmpty(newBirthDateString)&&Helper.respectsDateFormat(newBirthDateString)) {
                    LocalDate newBirthDate = LocalDate.parse(newBirthDateString);
                    newCust = CustomerFactory.createCustomer(oldCust.getFirstName(),oldCust.getLastName(),oldCust.getEmail(),oldCust.getPhone(),newBirthDate);
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "No field selected to update.");
                return;
        }
        if (newCust == null) {
            JOptionPane.showMessageDialog(null, "Invalid input or no changes made.");
            return;
        }
        customerRepository.update(newCust);
        JOptionPane.showMessageDialog(null, "Customer updated successfully!");

    }
    public static void customerMenu(){
        String[] menuChoices = {
                "Create an Customer",
                "Search an Customer",
                "Update a Customer",
                "Delete an Customer",
                "Show Customer information"
        };
        int typeChoice = JOptionPane.showOptionDialog(
                null,
                "Select the new Customer type",
                "Update Customer Type",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                menuChoices,
                menuChoices[0]
        );
        switch (typeChoice){
            case 0 -> createCustomer();
            case 1 -> searchCustomer();
            case 2 -> updateCustomer();
            case 3 -> deleteCustomer();
            case 4 -> showCustomers();
            default -> System.out.println("Selection error");

        }
    }
}

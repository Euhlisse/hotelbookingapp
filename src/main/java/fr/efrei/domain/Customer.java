package fr.efrei.domain;

import java.time.LocalDate;

public class Customer {
    private String firstName;
    private String lastName;
    private String idNumber;
    private String email;
    private String phone;
    private LocalDate birthDate;

    private Customer() { }
    private Customer(BuilderCustomer builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.idNumber = builder.idNumber;
        this.email = builder.email;
        this.phone = builder.phone;
        this.birthDate = builder.birthDate;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getIdNumber() { return idNumber; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public LocalDate getBirthDate() { return birthDate; }

    public static class BuilderCustomer{
        private String firstName;
        private String lastName;
        private String idNumber;
        private String email;
        private String phone;
        private LocalDate birthDate;

        public BuilderCustomer setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public BuilderCustomer setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public BuilderCustomer setIdNumber(String idNumber) {
            this.idNumber = idNumber;
            return this;
        }
        public BuilderCustomer setEmail(String email) {
            this.email = email;
            return this;
        }
        public BuilderCustomer setPhone(String phone) {
            this.phone = phone;
            return this;
        }
        public BuilderCustomer setBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public BuilderCustomer copy(Customer customer){
            this.firstName=customer.getFirstName();
            this.lastName=customer.getLastName();
            this.idNumber=customer.getIdNumber();
            this.email=customer.getEmail();
            this.phone=customer.getPhone();
            this.birthDate=customer.getBirthDate();
            return this;
        }

        public Customer build(){
            return new Customer(this);
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id:"+idNumber+
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", idNumber=" + idNumber +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}

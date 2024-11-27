package fr.efrei.domain;

import java.time.LocalDate;

public class Employee {
    private String employeeNumber;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private EmployeeType jobType;

    private Employee() { }
    private Employee(BuilderEmployee builder) {
        this.employeeNumber = builder.employeeNumber;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.birthDate = builder.birthDate;
        this.jobType = builder.jobType;
    }

    public String getEmployeeNumber(){ return employeeNumber; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public LocalDate getBirthDate() { return birthDate; }
    public EmployeeType getJobType() { return jobType; }

    public static class BuilderEmployee{
        private String employeeNumber;
        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private EmployeeType jobType;

        public BuilderEmployee setEmployeeNumber(String employeeNumber) {
            this.employeeNumber = employeeNumber;
            return this;
        }
        public BuilderEmployee setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public BuilderEmployee setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public BuilderEmployee setBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }
        public BuilderEmployee setJobType(EmployeeType jobType) {
            this.jobType = jobType;
            return this;
        }

        public BuilderEmployee copy(Employee employee){
            this.employeeNumber=employee.getEmployeeNumber();
            this.firstName=employee.getFirstName();
            this.lastName= employee.getLastName();
            this.birthDate = employee.getBirthDate();
            this.jobType= employee.getJobType();
            return this;
        }
        public Employee build(){
            return new Employee(this);
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeNumber='" + employeeNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate + '\'' +
                ", jobType='" + jobType + '}';
    }
}

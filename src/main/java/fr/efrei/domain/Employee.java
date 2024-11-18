package fr.efrei.domain;

import java.time.LocalDate;

public class Employee {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String jobType;

    private Employee() { }
    private Employee(BuilderEmployee builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.birthDate = builder.birthDate;
        this.jobType = builder.jobType;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public LocalDate getBirthDate() { return birthDate; }
    public String getJobType() { return jobType; }

    public static class BuilderEmployee{
        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private String jobType;

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
        public BuilderEmployee setJobType(String jobType) {
            this.jobType = jobType;
            return this;
        }

        public BuilderEmployee copy(Employee employee){
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
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate + '\'' +
                ", jobType='" + jobType + '}';
    }
}

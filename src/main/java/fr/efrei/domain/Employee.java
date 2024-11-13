package fr.efrei.domain;

public class Employee {
    private String firstName;
    private String lastName;
    private String jobType;

    public Employee() { }
    public Employee(String firstName, String lastName, String jobType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobType = jobType;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getJobType() { return jobType; }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", jobType='" + jobType + '\'' +
                '}';
    }
}

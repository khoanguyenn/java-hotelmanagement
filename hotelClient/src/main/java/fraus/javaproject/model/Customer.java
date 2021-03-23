package fraus.javaproject.model;

import java.time.LocalDate;

/**
 * Encapsulating all information related to Customer's information, utilizes as model to map all booking's information retrieve from database
 * Contains the name, gender, telephone number, address, email and birthday of a specific customer.
 * @author Huy Ha Xuan, Khoa Nguyen Dang
 * @version 1.0
 * */
public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private String telephoneNumber;
    private String address;
    private String email;
    private LocalDate dateOfBirth;

    public Customer() {
        this(0, null, null, null, null, null, null, null);
    }

    /**
     * Customer constructor
     * */
    public Customer(int id,
                    String firstName,
                    String lastName,
                    String gender,
                    String telephoneNumber,
                    String address,
                    String email,
                    LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}

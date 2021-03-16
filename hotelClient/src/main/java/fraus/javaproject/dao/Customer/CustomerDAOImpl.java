package fraus.javaproject.dao.Customer;

import fraus.javaproject.model.Customer;
import fraus.javaproject.utils.DBUtil;
import fraus.javaproject.utils.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
/**
 * @author Dang Khoa Nguyen
 * @see fraus.javaproject.dao.Customer.CustomerDAO
 * @deprecated
 * */
public class CustomerDAOImpl implements CustomerDAO {
    private static Connection connection = DBUtil.getConnection();
    private static String getAllQuery = "SELECT * FROM customer";
    private static String addQuery = "INSERT INTO customer (first_name, last_name, gender, telephone_number, address, email, dob) VALUES  (?, ?, ?, ?, ?, ?, ?)";
    private ObservableList<Customer> customerList = FXCollections.observableArrayList();

    public CustomerDAOImpl() {

    }
    @Override
    public ObservableList<Customer> getAllCustomer() {
        try (
                //Prepare connection to database
                Statement stmt = connection.createStatement();
                ResultSet res = stmt.executeQuery(getAllQuery);
                ) {
            while (res.next()) {

                //Get all customers infos
                int id = res.getInt("customer_id");
                String firstName = res.getString("first_name");
                String lastName = res.getString("last_name");
                String gender = res.getString("gender");
                String telephoneNumber = res.getString("telephone_number");
                String address = res.getString("address");
                String email = res.getString("email");

                //Parse String -> LocalDate
                LocalDate DOB = DateUtil.parse(res.getString("dob"));

                //Create a new customer
                Customer newCustomer = new Customer(id, firstName, lastName, gender, telephoneNumber, address, email, DOB);

                //Add to the list
                customerList.add(newCustomer);
            }
            return customerList;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public int insertCustomer(Customer customer) {
        ResultSet res = null;
        int customerID = 0;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(addQuery, Statement.RETURN_GENERATED_KEYS);
                ) {
            //Set all parameters to add query
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getGender());
            preparedStatement.setString(4, customer.getTelephoneNumber());
            preparedStatement.setString(5, customer.getAddress());
            preparedStatement.setString(6, customer.getEmail());
            preparedStatement.setDate(7, Date.valueOf(customer.getDateOfBirth()));


            //Execute insert query and return affected row
            int rowAffected = preparedStatement.executeUpdate();

            //If record updated, give generated key back
            if (rowAffected == 1) {
                res = preparedStatement.getGeneratedKeys();

                //Move the ResultSet pointer to the first record (which is id)
                if (res.next()) {
                    customerID = res.getInt(1);
                }
            }
            
            return customerID;
        } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            return -1;
        }
    };

}
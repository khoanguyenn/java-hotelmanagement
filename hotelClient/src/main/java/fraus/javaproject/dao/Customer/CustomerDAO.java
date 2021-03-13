package fraus.javaproject.dao.Customer;

import fraus.javaproject.model.Customer;
import javafx.collections.ObservableList;
/**
 * Connect to directly to the database and fetch all data according to the functionality.
 * @author Dang Khoa Nguyen
 * @version 1.0.5
 * @deprecated it used to directly connect to the database without connecting to web-server to retrieve data.
 * */
public interface CustomerDAO {
    ObservableList<Customer> getAllCustomer();
    int insertCustomer(Customer customer);

    //void deleteCustomer();

}

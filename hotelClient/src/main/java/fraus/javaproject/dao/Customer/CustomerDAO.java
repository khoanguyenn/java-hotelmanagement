package fraus.javaproject.dao.Customer;

import fraus.javaproject.model.Customer;
import javafx.collections.ObservableList;

public interface CustomerDAO {
    ObservableList<Customer> getAllCustomer();
    int insertCustomer(Customer customer);

    //void deleteCustomer();

}

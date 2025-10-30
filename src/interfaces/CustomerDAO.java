package src.interfaces;

import src.models.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    void addCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Optional<Customer> findCustomerByEmail(String email);
    void updateCustomerCity(String email, String newCity);
    void deleteCustomer(String email);
}

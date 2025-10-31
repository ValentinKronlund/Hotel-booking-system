package hotel.interfaces;

import java.util.List;
import java.util.Optional;

import hotel.models.Customer;

public interface CustomerDAO {
    void addCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Optional<Customer> findCustomerByEmail(String email);
    void updateCustomerCity(String email, String newCity);
    void deleteCustomer(String email);
}

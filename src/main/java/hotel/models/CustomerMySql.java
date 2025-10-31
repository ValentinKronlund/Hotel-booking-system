package hotel.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import hotel.config.Connector;
import hotel.interfaces.CustomerDAO;

public class CustomerMySql implements CustomerDAO {

    @Override
    public void addCustomer(Customer customer){
        String sql = "INSERT INTO cusotmers (name, email, city) VALUES (?, ?, ?)";

        try (
            Connection connection = Connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getCity());
            statement.executeUpdate();
        }
        catch (SQLException err){
            throw new RuntimeException("Could not add customer! :C", err);
        }
    }


    @Override
    public List<Customer> getAllCustomers(){
        String sql = "SELECT id, name, email, city FROM customers";
        List<Customer> list = new ArrayList<>();

        try (
            Connection connection = Connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet dataSet = statement.executeQuery();
        ){
            while(dataSet.next()){
                list.add(new Customer(
                    dataSet.getInt("id"),
                    dataSet.getString("name"),
                    dataSet.getString("email"),
                    dataSet.getString("city")
                ));
            }
        }
        catch(SQLException err){
            throw new RuntimeException("Could not retrieve all customers! :C", err);
        }

        return list;
    };


    @Override
    public Optional<Customer> findCustomerByEmail(String email){
        String sql = "SELECT id, name, email, city FROM customer WHERE email = ?";
        
        try(
            Connection connection = Connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setString(1, email);

            try(ResultSet dataSet = statement.executeQuery()){
                if(dataSet.next()){
                    return Optional.of(new Customer(
                        dataSet.getInt("id"),
                        dataSet.getString("name"),
                        dataSet.getString("email"),
                        dataSet.getString("city")
                    ));
                }
            }
            catch(SQLException err){
                throw new RuntimeException("Something went wrong when attempting to find customer! :o", err);
            }

        }
        catch(SQLException err){
            throw new RuntimeException("Could not find customer by email! :C");
        }

        return Optional.empty();
    };


    @Override
    public void updateCustomerCity(String email, String newCity){
        String sql = "UPDATE customers SET city = ? WHERE email = ?";

        try(
            Connection connection = Connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, newCity);
            statement.setString(2, email);
            statement.executeQuery();
            
        } catch (SQLException err) {
            throw new RuntimeException("Could not update customer's city! :C", err);
        }
    };

    @Override
    public void deleteCustomer(String email){
        String sql = "DELETE FROM customers WHERE email = ?";
        try(
            Connection connection = Connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, email);
        } catch (SQLException err) {
            throw new RuntimeException("Could not delete customer! x|", err);
        }
    };
    
}

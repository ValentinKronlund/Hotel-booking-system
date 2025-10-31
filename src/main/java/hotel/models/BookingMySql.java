package hotel.models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import hotel.config.Connector;
import hotel.interfaces.BookingDAO;

public class BookingMySql implements BookingDAO {

    @Override 
    public void bookRoom(Booking booking) throws Exception{
        String checkRoomSql = "SELECT available FROM rooms WHERE id = ? FOR UPDATE";
        String insertBookingSql = "INSERT INTO bookings (customer_id, room_id, start_date, end_date) VALUES (?, ?, ?, ?)";
        String updateRoomSql =  "UPDATE rooms SET available = FALSE WHERE id = ?";

        try (
            Connection connection = Connector.getConnection()
        ){
            try {
                connection.setAutoCommit(false);
    
                try (PreparedStatement checkStatement = connection.prepareStatement(checkRoomSql)){
                    checkStatement.setInt(1, booking.getRoomId());
                    try (ResultSet dataSet = checkStatement.executeQuery()){
                        if(!dataSet.next() | !dataSet.getBoolean("available")){
                            throw new Exception("Room is not available");
                        }
                    }
                }
    
                try (PreparedStatement insertStatement = connection.prepareStatement(insertBookingSql)){
                    insertStatement.setInt(1, booking.getCustomerId());
                    insertStatement.setInt(2, booking.getRoomId());
                    insertStatement.setDate(3, Date.valueOf(booking.getStartDate()));
                    insertStatement.setDate(4, Date.valueOf(booking.getEndDate()));
                    insertStatement.executeUpdate();
                }
    
                try (PreparedStatement updateStatement = connection.prepareStatement(updateRoomSql)){
                    updateStatement.setInt(1, booking.getRoomId());
                    updateStatement.executeUpdate();
                }
            }
            catch(Exception err){
                connection.rollback();
                throw new Exception("Something went wrong when booking ... Rolling back!", err);
            }
            finally {
                connection.setAutoCommit(true);
            }
        }
    };

    
    @Override 
    public List<Booking> getAllBookings(){
        String sql = "SELECT * FROM bookings";
        List<Booking> bookings = new ArrayList<>();

        try (
            Connection connection = Connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet dataSet = statement.executeQuery();
        ){
            while(dataSet.next()){
                bookings.add(new Booking(
                    dataSet.getInt("id"),
                    dataSet.getInt("customer_id"),
                    dataSet.getInt("room_id"),
                    dataSet.getDate("start_date").toLocalDate(),
                    dataSet.getDate("end_date").toLocalDate()
                ));
            }
        } catch (SQLException err) {
            throw new RuntimeException("Could not fetch all bookings! :C", err);
        }

        return bookings;
    };

    
    @Override 
    public List<Booking> getBookingsByCustomerEmail(String email){
        String sql = "SELECT b.* FROM bookings b JOIN customers c ON b.customer_id = c.id WHERE c.email = ?";
        List<Booking> bookings = new ArrayList<>();

        try (
            Connection connection = Connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setString(1, email);

            try (ResultSet dataSet = statement.executeQuery()){
                while(dataSet.next()){
                        bookings.add(new Booking(
                        dataSet.getInt("id"),
                        dataSet.getInt("customer_id"),
                        dataSet.getInt("room_id"),
                        dataSet.getDate("start_date").toLocalDate(),
                        dataSet.getDate("end_date").toLocalDate()
                    ));
                }
            }

        } catch (SQLException err) {
            throw new RuntimeException("Could not get all bookings for " + email + " :C", err);
        }

        return bookings;
    };

    
    @Override 
    public void cancelBooking(int bookingId) throws Exception{
        String roomSql = "SELECT room_id FROM bookings WHERE id = ?";
        String deleteSql = "DELETE FROM bookings WHERE id = ?";
        String setAvailableSql = "UPDATE rooms SET available = TRUE WHERE id = ?";

        try (Connection connection = Connector.getConnection()){
            try {
                connection.setAutoCommit(false);
                int roomId;

                try(PreparedStatement statement = connection.prepareStatement(roomSql)){
                    statement.setInt(1, bookingId);
                    try(ResultSet dataSet = statement.executeQuery()){
                        if(!dataSet.next()){
                            throw new Exception("Could not find any bookings!");
                        }
                        roomId = dataSet.getInt("room_id");
                    }

                }

                try(PreparedStatement statement = connection.prepareStatement(deleteSql)){
                    statement.setInt(1, roomId);
                    statement.executeUpdate();
                }

                try(PreparedStatement statement = connection.prepareStatement(setAvailableSql)){
                    statement.setInt(1, roomId);
                    statement.executeUpdate();
                }

                connection.commit();
            } catch (Exception err){
                connection.rollback();
                throw new Exception("Something went wrong when trying to cancel booking!", err);
            } finally{
                connection.setAutoCommit(true);
            }
            
        } catch (Exception err) {
            throw new RuntimeException("Could not establish connection :C", err);
        }
    };


    
    @Override 
    public List<Object[]> countBookingsPerCustomer(){
        // TODO - Complete implementation
        List<Object[]> list = new ArrayList<>();
        return list;

    };

    
    @Override 
    public int getAverageBookedRoomPrice(){
        // TODO - Complete implementation
        int avgPrice = 0;
        return avgPrice;
    };

    
    @Override 
    public List<Integer> getAvailableRoomsBetween(LocalDate start, LocalDate end){
        // TODO - Complete implementation
        List<Integer> availableRooms = new ArrayList<>();
        return availableRooms;
    };

    
    @Override 
    public List<String> getCustomersWhoNeverBooked(){
        // TODO - Complete implementation
        List<String> cNeverBooked = new ArrayList<>();
        return cNeverBooked;
    };

    
}

package hotel.models;

import java.sql.Connection;
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
    public void bookRoom(Booking booking) throws SQLException{};

    @Override 
    public List<Booking> getAllBookings(){};

    @Override 
    public List<Booking> getBookingsByCustomerEmail(String email){};

    @Override 
    public void cancelBooking(int bookingId) throws SQLException{};


    @Override 
    public List<Object[]> countBookingsPerCustomer(){};

    @Override 
    public double getAverageBookedRoomPrice(){};

    @Override 
    public List<Integer> getAvailableRoomsBetween(LocalDate start, LocalDate end){};

    @Override 
    public List<String> getCustomersWhoNeverBooked(){};

    
}

package hotel.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import hotel.config.Connector;
import hotel.interfaces.RoomDAO;

public class RoomMySql implements RoomDAO {
    
    @Override
    public void addRoom(Room room){
        String sql = "INSERT INTO rooms (room_number, room_type, price, available) VALUES (?, ?, ?, ?)";
    };

    @Override
    public List<Room> getAllRooms(){};

    @Override
    public List<Room> getAvailableRooms(){};

    @Override
    public void updateRoomPrice(String roomNumber, int newPrice){};

    @Override
    public void updateRoomType(String roomNumber, String newType){};

}

package hotel.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hotel.config.Connector;
import hotel.interfaces.RoomDAO;

public class RoomMySql implements RoomDAO {
    
    @Override
    public void addRoom(Room room){
        String sql = "INSERT INTO rooms (room_number, room_type, price, available) VALUES (?, ?, ?, ?)";

        try(
            Connection connection = Connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setString(1, room.getRoomNumber());
            statement.setString(2, room.getRoomType());
            statement.setInt(3, room.getRoomPrice());
            statement.setBoolean(4, room.getRoomAvailability());
            statement.executeUpdate();
        } catch (SQLException err) {
            throw new RuntimeException("Could not add room! :C", err);
        }
    };

    @Override
    public List<Room> getAllRooms(){
        String sql = "SELECT * FROM rooms";
        List<Room> list = new ArrayList<>();

        try (
            Connection connection = Connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet dataSet = statement.executeQuery();
        ){
            while(dataSet.next()){
                list.add(new Room(
                    dataSet.getInt("id"),
                    dataSet.getString("room_number"),
                    dataSet.getString("room_type"),
                    dataSet.getInt("price"),
                    dataSet.getBoolean("available")
                ));
            }
        } catch (SQLException err) {
            throw new RuntimeException("Could not fetch all rooms! :C", err);
        }

        return list;
    };

    @Override
    public List<Room> getAvailableRooms(){
        String sql = "SELECT * FROM rooms WHERE available = TRUE";
        List<Room> rooms = new ArrayList<>();

        try (
            Connection connection = Connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet dataSet = statement.executeQuery();
        ){
            while(dataSet.next()){
                rooms.add(new Room(
                    dataSet.getInt("id"),
                    dataSet.getString("room_number"),
                    dataSet.getString("room_type"),
                    dataSet.getInt("price"),
                    dataSet.getBoolean("available")
                ));
            }
            
        } catch (SQLException err) {
            throw new RuntimeException("Could not get available rooms! :C", err);
        }

        return rooms;
    };

    @Override
    public void updateRoomPrice(String roomNumber, int newPrice){
        String sql = "UPDATE rooms SET price = ? WHERE room_number = ?";

        try (
            Connection connection = Connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setInt(1, newPrice);
            statement.setString(1, roomNumber);
            statement.executeUpdate();
            
        } catch (SQLException err) {
            throw new RuntimeException("Could not update room price! :C", err);
        }
    };

    @Override
    public void updateRoomType(String roomNumber, String newType){
        String sql = "UPDATE rooms SET room_type = ? WHERE room_number = ?";

        try (
            Connection connection = Connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setString(1, newType);
            statement.setString(1, roomNumber);
            statement.executeUpdate();
            
        } catch (SQLException err) {
            throw new RuntimeException("Could not update room price! :C", err);
        }
    };

}

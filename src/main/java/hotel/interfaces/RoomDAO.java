package hotel.interfaces;

import java.util.List;

import hotel.models.Room;

public interface RoomDAO {
    void addRoom(Room room);
    List<Room> getAllRooms();
    List<Room> getAvailableRooms();
    void updateRoomPrice(String roomNumber, int newPrice);
    void updateRoomType(String roomNumber, String newType);
}

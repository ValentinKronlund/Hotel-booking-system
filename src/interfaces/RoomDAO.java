package src.interfaces;

import src.models.Room;
import java.util.List;

public interface RoomDAO {
    void addRoom(Room room);
    List<Room> getAllRooms();
    List<Room> getAvailableRooms();
    void updateRoomPrice(String roomNumber, int newPrice);
    void updateRoomType(String roomNumber, String newType);
}

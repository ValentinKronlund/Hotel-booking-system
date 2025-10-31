package hotel.models;

public class Room {
    private int id;
    private String roomNumber;
    private String roomType;
    private int price;
    private boolean available;

    public Room(){}
    public Room(int id,
    String roomNumber,
    String roomType,
    int price,
    boolean available){
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomType= roomType;
        this.price = price;
        this.available = available;
    }

    public Room(
    String roomNumber,
    String roomType,
    int price,
    boolean available){
        this(0, roomNumber, roomType, price, available);
    }

    public String getRoomNumber(){return this.roomNumber;}
    public String getRoomType(){return this.roomType;}
    public int getRoomPrice(){return this.price;}
    public boolean getRoomAvailability(){return this.available;}
}

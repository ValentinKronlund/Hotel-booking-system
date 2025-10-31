package hotel.models;

import java.time.LocalDate;

public class Booking {
    private int id;
    private int customerId;
    private int roomId;
    private LocalDate startDate;
    private LocalDate endDate;

    public Booking(){}
    public Booking(
        int id,
        int customerId,
        int roomId,
        LocalDate startDate,
        LocalDate endDate
    ){
        this.id = id;
        this.customerId = customerId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public Booking(
        int customerId,
        int roomId,
        LocalDate startDate,
        LocalDate endDate
    ){
        this(0,customerId,roomId,startDate,endDate);
    }
    
}

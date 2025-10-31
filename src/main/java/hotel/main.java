package hotel;

import java.time.LocalDate;
import java.util.Scanner;

import hotel.interfaces.BookingDAO;
import hotel.interfaces.CustomerDAO;
import hotel.interfaces.RoomDAO;
import hotel.models.Booking;
import hotel.models.BookingMySql;
import hotel.models.Customer;
import hotel.models.CustomerMySql;
import hotel.models.Room;
import hotel.models.RoomMySql;

public class main {
    private static final CustomerDAO customerDAO = new CustomerMySql();
    private static final RoomDAO roomDAO = new RoomMySql();
    private static final BookingDAO bookingDAO = new BookingMySql();
    

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("""
                1. Add customer
                2. List customers
                3. Add room
                4. List rooms
                5. Book room
                6. List bookings
                7. Cancel booking
                0. Exit
                """);
            
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
               case 1 -> addCustomer(scanner);
               case 2 -> listCustomers();
               case 3 -> addRoom(scanner);
               case 4 -> listRooms();
               case 5 -> bookRoom(scanner);
               case 6 -> listBookings();
               case 7 -> cancelBooking(scanner);
               case 0 -> {return;}
            }
        }
    }

    private static void addCustomer(Scanner sc) {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("City: ");
        String city = sc.nextLine();
        customerDAO.addCustomer(new Customer(name,
        email, city));
    }

    private static void listCustomers() {
        customerDAO.getAllCustomers()
                .forEach(customer -> System.out.println("Name: %s, Email: %s, City: %s".formatted(customer.getName(), customer.getEmail(), customer.getCity())));
    }

    private static void addRoom(Scanner sc) {
        System.out.print("Room number: ");
        String roomNum = sc.nextLine();
        System.out.print("Type: ");
        String type = sc.nextLine();
        System.out.print("Price: ");
        int price = Integer.parseInt(sc.nextLine());

        roomDAO.addRoom(new Room(roomNum, type, price, true));
    }

    private static void listRooms() {
        roomDAO.getAllRooms().forEach(room ->
                System.out.println("ID: %d, Number: %s, Type: %s, Price: %d, Available: %b".formatted(
                    room.getId(),
                    room.getRoomNumber(),
                    room.getRoomType(),
                    room.getRoomPrice(),
                    room.getRoomAvailability()
                )));
    }

    private static void bookRoom(Scanner sc) throws Exception {
        System.out.print("Customer email: ");
        String email = sc.nextLine();
        var customer = customerDAO.findCustomerByEmail(email);

        if (customer.isEmpty()) {
            System.out.println("No such customer.");
            return;
        }
        
        int customerId = customer.get().getId();

        System.out.print("Room id: ");
        int roomId = Integer.parseInt(sc.nextLine());

        System.out.print("Start date (YYYY-MM-DD): ");
        LocalDate start = LocalDate.parse(sc.nextLine());
        System.out.print("End date (YYYY-MM-DD): ");
        LocalDate end = LocalDate.parse(sc.nextLine());

        bookingDAO.bookRoom(new Booking(customerId, roomId, start, end));
        System.out.println("Booked ✅");
    }

    private static void listBookings() {
        bookingDAO.getAllBookings().forEach(booking ->
                System.out.println(
                "ID:%d, CustomerId:%d, RoomId:%d, Start:%tF, End:%tF".formatted(
                    booking.getId(),
                    booking.getCustomerId(),
                    booking.getRoomId(),
                    booking.getStartDate(),
                    booking.getEndDate()
                )));
    }

    private static void cancelBooking(Scanner sc) throws Exception {
        System.out.print("Booking id: ");
        int id = Integer.parseInt(sc.nextLine());
        bookingDAO.cancelBooking(id);
        System.out.println("Cancelled ✅");
    }

}

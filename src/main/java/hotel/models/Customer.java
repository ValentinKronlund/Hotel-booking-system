package hotel.models;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String city;

    public Customer(){}
    public Customer(int id, String name, String email, String city){
        this.id = id;
        this.name = name;
        this.email = email;
        this.city = city;
    }
    public Customer(String name, String email, String city){
        this(0, name, email, city);
    }

    public String getName(){return this.name;}
    public String getEmail(){return this.email;}
    public String getCity(){return this.city;}
}

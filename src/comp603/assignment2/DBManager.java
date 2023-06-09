package comp603.assignment2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jiuxin Ni
 */
public class DBManager {

    private final String USER_NAME = "pdc";
    private final String PASSWORD = "pdc";
    private String URL;
    Connection conn;
    private ArrayList<Booking> bookingList;
    private Room room;

    public DBManager() {
        bookingList = new ArrayList<>();
        establishConnection();
        readBooking();

    }

    public Connection getConnection() {
        return this.conn;
    }

    //Establish connection
    public void establishConnection() {
        String path = System.getProperty("user.dir");
        this.URL = "jdbc:derby:" + path + "/Comp603 HotelBookingSystem;create=true";
        //Establish a connection to Database
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println(URL + " Connection established successfully.");
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
        }
    }

    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Disconnected sucessfully.");
            } catch (SQLException ex) {
                System.out.println("Error on Disconnection: " + ex.getMessage());
            }
        }
    }

    //adding Booking to the DataBase
    public void addBooking(Booking booking) {
        String query = "INSERT INTO BOOKINGLIST(CUSTOMERNAME, ROOMTYPE, CHECKINDATE, CHECKOUTDATE, PHONE) VALUES (?,?,?,?,?)";
        try {
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setString(1, booking.name);
                statement.setString(2, booking.room.getRoomType());
                statement.setString(3, booking.CheckIndate);
                statement.setString(4, booking.CheckOutdate);
                statement.setString(5, booking.phone);
                statement.executeUpdate();
                statement.close();
                bookingList.add(booking);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //reading Booking from the DataBase
    public void readBooking() {
        try {
            Statement statement = conn.createStatement();
            ResultSet booking = statement.executeQuery("SELECT * FROM BOOKINGLIST");
            while (booking.next()) {
                String name = booking.getString("CUSTOMERNAME");
                String roomType = booking.getString("ROOMTYPE");
                makeRoom(roomType);
                String checkInDate = booking.getString("CHECKINDATE");
                String checkOutDate = booking.getString("CHECKOUTDATE");
                String phone = booking.getString("PHONE");
                Booking newBooking = new Booking(name, room, phone, checkInDate, checkOutDate);
                bookingList.add(newBooking);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void delectBooking(Booking booking) {
        String query = "DELETE FROM BOOKINGLIST WHERE CUSTOMERNAME = ? AND ROOMTYPE = ? AND CHECKINDATE = ? AND CHECKOUTDATE = ? AND PHONE = ? ";
        try {
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setString(1, booking.name);
                statement.setString(2, booking.room.getRoomType());
                statement.setString(3, booking.CheckIndate);
                statement.setString(4, booking.CheckOutdate);
                statement.setString(5, booking.phone);
                statement.executeUpdate();
                statement.close();
                ArrayList<Booking> delect = bookingList;
                for (Booking token : delect) {
                    if (token.compareTo(booking)) {
                        delect.remove(token);
                        break;
                    }
                }
                bookingList = delect;
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// search booking record using name and phone
    public ArrayList<Booking> checkForBooking(String name, String phone) {

        ArrayList<Booking> found = new ArrayList<>();
        for (Booking token : bookingList) {
            if (name != null) {
                if (name.equalsIgnoreCase(token.name)) {
                    if (phone.equals(token.phone) && !found.contains(token)) {
                        found.add(token);
                    }
                }
            }
        }
        return found;
    }

    //make Room object
    private void makeRoom(String roomType) {
        if (roomType.contains("Single")) {
            room = new SingleRoom();
        }
        if (roomType.contains("Double")) {
            room = new DoubleRoom();
        }
        if (roomType.contains("Deluxe")) {
            room = new DeluxeRoom();
        }
    }
}

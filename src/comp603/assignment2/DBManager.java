/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comp603.assignment2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 *
 * @author Jeremy
 */
public class DBManager {

    private final String USER_NAME = "pdc";
    private final String PASSWORD = "pdc";
    private String URL;
//    private static final String URL = "jdbc:derby://localhost:1527/Comp603 HotelBookingSystem;create=true";  //url of the DB host

    Connection conn;

    public DBManager() {
        establishConnection();
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
    
    public void addBooking(Booking booking) {
        String query = "INSERT INTO BOOKINGLIST(CUSTOMERNAME, ROOMTYPE, CHECKINDATE, CHECKOUTDATE, PHONE) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1,booking.name);
            statement.setString(2,booking.room.getRoomType());
            statement.setString(3,booking.CheckIndate);
            statement.setString(4,booking.CheckOutdate);
            statement.setString(5, booking.phone);
            statement.executeUpdate();
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cancelBooking(){
        
    }
}

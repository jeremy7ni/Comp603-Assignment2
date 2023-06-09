/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comp603.assignment2;

import java.util.Date;

/**
 *
 * @author Jeremy
 */
// To create new Booking after user agree with everything
public class Booking {

    protected String name;
    protected Room room;
    protected String phone;
    protected String CheckIndate;
    protected String CheckOutdate;

    public Booking(String name, Room room, String phone, String CheckIndate, String CheckOutdate) {
        this.name = name;
        this.room = room;
        this.phone = phone;
        this.CheckIndate = CheckIndate;
        this.CheckOutdate = CheckOutdate;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n"
                + "phone: " + phone + "\n"
                + "Room Type: " + room.getRoomType() + "\n"
                + "CheckIndate: " + CheckIndate + "\n"
                + "CheckOutdate: " + CheckOutdate + "\n\n";
    }
    
    public boolean compareTo(Booking booking){
        boolean valid = true;
        if(!booking.name.equalsIgnoreCase(this.name)){
            valid = false;
        }
        if(valid){
            if(!booking.phone.equalsIgnoreCase(this.phone)){
                valid = false;
            }
            
        }
        if(valid){
            if(!booking.CheckIndate.equalsIgnoreCase(this.CheckIndate)){
                valid = false;
            }
        }
        if(valid){
            if(!booking.CheckOutdate.equalsIgnoreCase(this.CheckOutdate)){
                valid = false;
            }
        }
        if(valid){
            if(!booking.room.comepareTo(this.room)){
                valid = false;
            }
        }
        return valid;
    }
}

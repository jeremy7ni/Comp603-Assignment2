/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comp603.assignment2;

/**
 *
 * @author JIUXIN NI
 */
public class DeluxeRoom extends Room {

    public DeluxeRoom() {
        super(350, "Deluxe Room");
    }

    @Override
    public void RoomService() {
        System.out.println("You have more than basic Room Serivce in Deluxe Room");
        System.out.println("You can ask for Cleaning Service by dial 0111 using the phone in the room");
        System.out.println("You can use Don't Disturb card if you needed");
        System.out.println("You can ask for Wake Up Service by dial 0112 using the phone in the room \n"
                + "and tell the reception when you need the Wake Up Service");
    }

    public void Kitchen() {
        System.out.println("you have a Kitchen to use");
    }

    @Override
    public void getRoomInfo() {
        RoomService();
        Kitchen();
    }
}

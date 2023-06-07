/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comp603.assignment2;

/**
 *
 * @author Jeremy
 */
public class DoubleRoom extends Room {

    public DoubleRoom() {
    }
    
    
    
    public DoubleRoom(double price, String roomType) {
        super(price = 220, roomType = "Double Room");
    }
    
    @Override
    public void BedSize() {
        System.out.println("You have Double Bed in the Double Room");
    }

    @Override
    public void RoomService() {
        System.out.println("You have basic Room Serivce in Double Room");
        System.out.println("You can ask for Cleaning Service by dial 0111 using the phone in the room");
        System.out.println("You can use Don't Disturb card if you needed");
    }

}

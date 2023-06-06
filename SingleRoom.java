
package comp603.assignment2;

/**
 *
 * @author Jeremy
 */
public class SingleRoom extends Room {

    
    public SingleRoom(){
        
    }
    public SingleRoom(double price, String roomType) {
        super(price = 150, roomType = "Single Room");
    }

    @Override
    public void BedSize() {
        System.out.println("You have Single Bed in the Single Room");
    }

    @Override
    public void RoomService() {
        System.out.println("You have basic Room Serivce in Single Room");
        System.out.println("You can ask for Cleaning Service by dial 0111 using the phone in the room");
        System.out.println("You can use Don't Disturb card if you needed");
    }

}

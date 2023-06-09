
package comp603.assignment2;

/**
 *
 * @author JIUXIN 
 */
public class DoubleRoom extends Room {

    public DoubleRoom() {
        super(220, "Double Room");
    }

    @Override
    public void RoomService() {
        System.out.println("You have basic Room Serivce in Double Room");
        System.out.println("You can ask for Cleaning Service by dial 0111 using the phone in the room");
        System.out.println("You can use Don't Disturb card if you needed");
    }

}

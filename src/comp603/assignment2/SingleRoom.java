package comp603.assignment2;

/**
 *
 * @author JIUXIN NI
 */
public class SingleRoom extends Room {


    public SingleRoom() {
        super(150, "Single Room");
    }


    @Override
    public void RoomService() {

        System.out.println("You have basic Room Serivce in Single Room");
        System.out.println("You can ask for Cleaning Service by dial 0111\n "
                + "using the phone in the room");
        System.out.println("You can use Don't Disturb card if you needed");
    }

}

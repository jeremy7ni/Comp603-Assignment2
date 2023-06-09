package comp603.assignment2;

/**
 *
 * @author Jeremy
 */
abstract class Room {

    private double price;
    private String roomType;
    private boolean vip;

    public Room() {

    }

    public Room(double price, String roomType) {
        this.price = price;
        this.roomType = roomType;
        this.vip = false;
    }

    // if input vip is true, then room.vip = true
    public abstract void RoomService();

    public void getRoomInfo() {
        RoomService();
    }

    public double calCost() {
        if (vip) {
            this.price *= 0.8;
        }
        return this.price;

    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public double getPrice() {
        return price;
    }

    public boolean isVip() {
        return vip;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean comepareTo(Room room) {
        boolean valid = true;
        if (!room.roomType.equalsIgnoreCase(this.roomType)) {
            valid = false;
        }
        if (valid) {
            if (!(room.price == this.price)) {
                valid = false;
            }
        }
        return valid;
    }
}

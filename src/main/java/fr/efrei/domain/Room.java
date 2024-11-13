package fr.efrei.domain;

public class Room{
    private int roomNumber;
    private int capacity;
    private int floorNumber;
    private String roomType;
    private double nightPrice;

   public Room() {}

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Room(int roomNumber, int capacity, int floorNumber, String roomType, double nightPrice) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.floorNumber = floorNumber;
        this.roomType = roomType;
        this.nightPrice = nightPrice;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
    public int getCapacity() { return capacity; }
    public int getFloorNumber() { return floorNumber; }
    public String getRoomType() { return roomType; }
    public double getNightPrice() { return nightPrice; }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", capacity=" + capacity +
                ", floorNumber=" + floorNumber +
                ", roomType='" + roomType + '\'' +
                ", nightPrice=" + nightPrice +
                '}';
    }
}
package fr.efrei.domain;

public class Room{
    private int roomNumber;
    private int capacity;
    private int floorNumber;
    private RoomType roomType;
    private double nightPrice;

    private Room() {}



    private Room(BuilderRoom builderRoom) {
        this.roomNumber = builderRoom.roomNumber;
        this.capacity = builderRoom.capacity;
        this.floorNumber = builderRoom.floorNumber;
        this.roomType = builderRoom.roomType;
        this.nightPrice = builderRoom.nightPrice;
    }

    public static class BuilderRoom{
        private int roomNumber;
        private int capacity;
        private int floorNumber;
        private RoomType roomType;
        private double nightPrice;

        public BuilderRoom setRoomNumber(int roomNumber){
            this.roomNumber = roomNumber;
            return this;
        }
        public BuilderRoom setCapacity(int capacity){
            this.capacity = capacity;
            return this;
        }
        public BuilderRoom setFloorNumber(int floorNumber){
            this.floorNumber = floorNumber;
            return this;
        }
        public BuilderRoom setRoomType(RoomType roomType){
            this.roomType = roomType;
            return this;
        }

        public BuilderRoom setNightPrice(double nightPrice){
            this.nightPrice = nightPrice;
            return this;
        }

        public Room copy(Room room){

            this.roomNumber=room.roomNumber;
            this.capacity=room.capacity;
            this.floorNumber=room.floorNumber;
            this.roomType=room.roomType;
            this.nightPrice=room.nightPrice;
            return new Room(this);


        }
        public Room build(){
            return new Room(this);
        }

    }

    public int getRoomNumber() {
        return roomNumber;
    }
    public int getCapacity() { return capacity; }
    public int getFloorNumber() { return floorNumber; }
    public RoomType getRoomType() { return roomType; }
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
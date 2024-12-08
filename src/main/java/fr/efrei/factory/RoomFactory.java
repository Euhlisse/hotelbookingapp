package fr.efrei.factory;
import fr.efrei.domain.Room;
import fr.efrei.domain.RoomType;

public class RoomFactory {
    private static int roomNumber = 0;
    public static Room createRoom(int floorNumber, RoomType roomType){
        if (Helper.isNullOrEmpty(String.valueOf(floorNumber))||Helper.isNullOrEmpty(String.valueOf(roomType)))
        {
            return null;
        }
        int capacity=0;
        double nightPrice=-0;

        switch (roomType){
            case Single :
                capacity = 1;
                nightPrice = 400;
                break;
            case Double :
                capacity = 2;
                nightPrice = 800;
            case Suite:
                capacity = 4;
                nightPrice = 1600;
        }
        roomNumber++;

        if(roomNumber <= 0 || capacity <= 0 || floorNumber <= 0 || nightPrice <= 0){
            return null;
        }

        return new Room.BuilderRoom()
                .setRoomNumber(roomNumber)
                .setRoomType(roomType)
                .setCapacity(capacity)
                .setFloorNumber(floorNumber)
                .setNightPrice(nightPrice)
                .build();
    }
}
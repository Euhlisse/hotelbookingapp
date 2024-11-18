package fr.efrei.factory;

import fr.efrei.domain.Booking;
import fr.efrei.domain.Room;

public class RoomFactory {
    public static Room createRoom(int roomNumber, int capacity, int floorNumber,String roomType,double nightPrice){
        if (Helper.isNullOrEmpty(String.valueOf(roomNumber))
                ||Helper.isNullOrEmpty(String.valueOf(capacity))
                ||Helper.isNullOrEmpty(String.valueOf(floorNumber))
                ||Helper.isNullOrEmpty(String.valueOf(roomType))
                ||Helper.isNullOrEmpty(String.valueOf(nightPrice)))
                {

            return null;
        }

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
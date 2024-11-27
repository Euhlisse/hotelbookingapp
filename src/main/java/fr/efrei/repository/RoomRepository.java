package fr.efrei.repository;

import fr.efrei.domain.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RoomRepository implements IRoomRepository{

    private static IRoomRepository repository = null;
    private List<Room> roomList;
    private RoomRepository(){roomList=new ArrayList<Room>();}
    public static IRoomRepository getRepository(){
        if (repository==null){
            repository = new RoomRepository();
        }
        return repository;
    }

    @Override
    public Room create(Room room) {
        boolean success =  roomList.add(room);
        if (success)
            return room;
        return null;
    }

    @Override
    public Room read(Integer roomNumber) {
        Optional<Room> foundRoom = roomList.stream().filter(room -> Objects.equals(room.getRoomNumber(), roomNumber))
                .findFirst();
        return foundRoom.orElse(null);
    }

    @Override
    public Room update(Room room) {
        int id = room.getRoomNumber();
        Room oldRoom = read(id);
        if (oldRoom==null)
            return null;
        boolean success = delete(id);
        if (success){
            if(roomList.add(room)){
                return room;
            }
        }
        return null;
    }

    @Override
    public boolean delete(Integer roomNumber) {
        return (roomList.remove(read(roomNumber)));
    }

    @Override
    public List<Room> getAll() {
        return roomList;
    }

}

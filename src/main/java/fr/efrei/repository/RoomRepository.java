package fr.efrei.repository;

import fr.efrei.domain.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomRepository implements IRoomRepository{
    private static IRoomRepository repository = null;
    private List<Room> roomList;
    private RoomRepository(){roomList=new ArrayList<Room>();}
    public static IRoomRepository getRepository(){
        if (repository == null){
            repository = new RoomRepository();
        }
        return repository;
    }

    @Override
    public Room create(Room Room){
        boolean success = roomList.add(Room);
        if (success)
            return Room;
        return null;
    }

    @Override
    public Room read(Integer s){
        Optional<Room> foundRoom = roomList.stream().filter(Room -> Room.getRoomNumber()==s).findFirst();
        return foundRoom.orElse(null);
    }

    @Override
    public Room update(Room Room){
        int idNumber = Room.getRoomNumber();
        Room oldRoom = read(idNumber);
        if (oldRoom == null)
            return null;
        boolean success = delete(idNumber);
        if (success){
            if(roomList.add(Room))
                return Room;
        }
        return null;
    }

    @Override
    public boolean delete(Integer idNumber){
        return (roomList.remove(read(idNumber)));
    }

    @Override
    public List<Room> getAll(){
        return roomList;
    }
}

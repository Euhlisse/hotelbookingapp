package fr.efrei.repository;
import fr.efrei.domain.Room;
import java.util.List;

public interface IRoomRepository extends IRepository<Room, Integer>{

    public List<Room> getAll();
}

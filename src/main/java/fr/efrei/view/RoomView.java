package fr.efrei.view;
import fr.efrei.domain.Customer;
import fr.efrei.domain.Room;
import fr.efrei.domain.RoomType;
import fr.efrei.factory.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import fr.efrei.domain.Customer;
import fr.efrei.factory.CustomerFactory;
import fr.efrei.factory.Helper;
import fr.efrei.repository.ICustomerRepository;
import fr.efrei.repository.IRoomRepository;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class RoomView {

    protected static IRoomRepository roomRepository;

    public RoomView(){}

    public RoomView(IRoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    public static void createRoom() {

        int floorNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter Floor Number "));

        RoomType[] roomTypes = RoomType.values();
        String[] roomTypeNames = Arrays.stream(roomTypes).map(RoomType::name)
                .toArray(String[]::new);
        String selectedType = (String) JOptionPane.showInputDialog(null,
                "Select the room type", "Room Type"
                ,JOptionPane.QUESTION_MESSAGE, null,
                roomTypeNames, roomTypeNames[0]);

        RoomType roomType = RoomType.valueOf(selectedType);
        Room room = RoomFactory.createRoom(floorNumber,roomType);
        if (room == null){
            JOptionPane.showMessageDialog(null,"incorrect input or room not created");
            return;
        }

    }

    public static void searchRoom(){roomRepository.read(selectRoomId());}
    public static void deleteRoom(){roomRepository.delete(selectRoomId());}
    public static void showRooms(){
        StringBuilder stringBuilder = new StringBuilder();
        roomRepository.getAll().forEach((room -> stringBuilder.append(room.toString()+"\n")));
        JOptionPane.showMessageDialog(null,stringBuilder.toString());
    }

    public static Integer selectRoomId(){
        List<Integer> roomNumbers = roomRepository.getAll().stream()
                .map(Room::getRoomNumber)
                .toList();
        int roomChoice=JOptionPane.showOptionDialog(
                null,
                "Select your id",
                "Room login",
                0,
                3,
                null,
                roomNumbers.toArray(),
                roomNumbers.getFirst()
        );
        return roomNumbers.get(roomChoice);
    }

    public static void roomMenu(){
        String[] menuChoices = {
                "Create an Room",
                "Search an Room",
                "Delete an Room",
                "Show Room informations"
        };
        int typeChoice = JOptionPane.showOptionDialog(
                null,
                "Select the new Room type",
                "Update Room Type",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                menuChoices,
                menuChoices[0]
        );
        switch (typeChoice){
            case 0 -> createRoom();
            case 1 -> searchRoom();
            case 2 -> deleteRoom();
            case 3 -> showRooms();
            default -> JOptionPane.showMessageDialog(null,"Selection error");

        }

    }

}

package STSWENGMCO;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import java.util.Objects;
import static org.apache.commons.lang3.Validate.notBlank;

public class Room {
    private String nameOfRoom;
    private final int maxCapacity;
    private int currentCapacity;

    public Room(String roomName, int maxCapacity) {
        notBlank(roomName, "Please provide room name. Room name cannot be left blank");
        Validate.isTrue(StringUtils.isAlphanumeric(roomName),"Room Name must be in alphanumeric, was: " + roomName);

        this.nameOfRoom = roomName;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return nameOfRoom.equals(room.nameOfRoom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfRoom);
    }


    void checkRoomCapacity () {
        if (this.currentCapacity >= maxCapacity) {
            throw new RuntimeException("Warning: Full Capacity!");
        }
    }

    public String getNameOfRoom(){
        return nameOfRoom;
    }

    public int getMaxCapacity(){
        return maxCapacity;
    }

    public int addToRoom(){
        return this.currentCapacity++;
    }

    public int removeFromRoom(){
        return this.currentCapacity--;
    }
}

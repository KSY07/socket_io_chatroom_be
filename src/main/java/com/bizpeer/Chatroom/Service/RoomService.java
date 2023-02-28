package com.bizpeer.Chatroom.Service;

import com.bizpeer.Chatroom.Entity.Room;
import com.bizpeer.Chatroom.Repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomService {

    private final RoomRepository roomRepository;

    public List<Room> getRoomList() throws SQLException {
        List<Room> roomList;
        roomList = roomRepository.getRoomList().orElseThrow(
                () -> new SQLException("There is not exist rooms")
        );

        return roomList;
    }
    public boolean createRoom(String roomName, String createUser) {
        try {
            Room target = Room.builder()
                    .roomName(roomName)
                    .createUser(createUser)
                    .build();

            roomRepository.save(target);

            return true;
        } catch(Exception e) {
            log.error("Create Room Error (Service)");
            return false;
        }
    }
}

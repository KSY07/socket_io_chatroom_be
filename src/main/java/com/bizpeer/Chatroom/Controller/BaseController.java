package com.bizpeer.Chatroom.Controller;

import com.bizpeer.Chatroom.DTO.RoomListCreateRequest;
import com.bizpeer.Chatroom.Entity.Room;
import com.bizpeer.Chatroom.Service.RoomService;
import io.socket.client.IO;
import io.socket.client.Socket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class BaseController {

    private final RoomService roomService;
    private final URI SOCKET_URI = URI.create("http://localhost:1234/socketApi");


    @GetMapping("/getRoomList")
    @CrossOrigin
    public List<Room> getRoomList() {
        List<Room> result = new ArrayList<>();
        try {
            result = roomService.getRoomList();
        } catch (Exception e) {
            log.error("Exception = {}", e);
        }

        return result;
    }


    @PostMapping("/createRoom")
    @CrossOrigin
    public ResponseEntity<?> createRoom(@RequestBody RoomListCreateRequest req) {

        boolean result;
        String roomName = req.getRoomName();
        String createUser = req.getCreateBy();

        IO.Options options = IO.Options.builder().build();

        Socket socket = IO.socket(SOCKET_URI,options);
        socket.connect();

        try {
            result = roomService.createRoom(roomName, createUser);

        } catch (Exception e) {

            log.error("create Room Error (Controller)");

            return ResponseEntity.badRequest().body(
                    "Create Room Fail"
            );

        }

        if(result) {
            socket.emit("sendToRerenderRoomList");
            return ResponseEntity.ok(
                    "Create Room Complete"
            );
        } else {
            return ResponseEntity.badRequest().body(
                    "Unknown Error"
            );
        }

    }


}

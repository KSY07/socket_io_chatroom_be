package com.bizpeer.Chatroom.Repository;

import com.bizpeer.Chatroom.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query(value = "SELECT * FROM CHATROOM", nativeQuery = true)
    Optional<List<Room>> getRoomList();

}

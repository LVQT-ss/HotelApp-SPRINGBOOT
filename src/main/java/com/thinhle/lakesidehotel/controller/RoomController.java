package com.thinhle.lakesidehotel.controller;

import com.thinhle.lakesidehotel.model.Room;
import com.thinhle.lakesidehotel.response.RoomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class RoomController {

    public ResponseEntity<RoomResponse> addNewRoom(
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("roomType") String roomType,
            @RequestParam("roomPrice") BigDecimal roomPrice) {
        Room savedRoom = roomService.addNewRoom(photo,roomType,roomPrice);
        RoomResponse response = new RoomResponse(savedRoom.getId(),
                savedRoom.getRoomType(), savedRoom.getRoomPrice());


    }
}

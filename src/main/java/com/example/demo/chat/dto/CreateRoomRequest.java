package com.example.demo.chat.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoomRequest {
    @Size(min = 1, max = 50, message = "채팅방 이름은 1~50자여야 합니다.")
    private String name;
}

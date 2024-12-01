package com.example.demo.vo;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserVO {
    private int id;
    private String name;
    private String email;
    private LocalDateTime createdAt;  // 생성일시 (TIMESTAMP에 대응)
}

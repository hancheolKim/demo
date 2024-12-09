package com.erp.vo;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserVO {
    private int userNum; // user_num: 사용자 번호
    private String id; // id: 사용자 아이디
    private String password; // password: 사용자 비밀번호
    private char status; // status: 사용자 상태 ('a', 'm', 'u')
}

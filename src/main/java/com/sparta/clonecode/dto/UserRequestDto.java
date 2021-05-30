package com.sparta.clonecode.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String username;
    private String password;
    private String passwordNen;
    private String passwordConfirm;
}


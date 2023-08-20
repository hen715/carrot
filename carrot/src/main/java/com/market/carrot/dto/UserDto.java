package com.market.carrot.dto;

import com.market.carrot.domain.user.Role;
import com.market.carrot.domain.user.User;
import lombok.Getter;

@Getter
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String picture;
    private Role role;

    public UserDto(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.picture = user.getPicture();
        this.role=this.getRole();

    }

}

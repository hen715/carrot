package com.market.carrot.dto;

import com.market.carrot.domain.user.Role;
import com.market.carrot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveDto {
    private String name;
    private String email;
    private String password;
    private String picture;
    private Role role;

    @Builder
    public UserSaveDto(String name, String email, String password, String picture,Role role){
        this.name = name;
        this.email = email;
        this.password = password;
        this.picture = picture;
        this.role = role;
    }

    public User toEntity(){
      return  User.builder()
              .name(name)
              .email(email)
              .password(password)
              .picture(picture)
              .role(role)
              .build();
    }
}

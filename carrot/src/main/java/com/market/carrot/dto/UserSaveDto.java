package com.market.carrot.dto;

import com.market.carrot.domain.user.Role;
import com.market.carrot.domain.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSaveDto {
    @Size(min=2,max=5)
    @NotEmpty(message = "사용자 이름은 필수입니다.")
    private String name;

    @NotEmpty(message = "사용자의 이메일은 필수입니다.")
    @Email
    private String email;

    @NotEmpty(message = "사용자의 비밀번호는 필수입니다.")
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

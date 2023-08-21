package com.market.carrot.service;

import com.market.carrot.domain.user.User;
import com.market.carrot.domain.user.UserRepository;
import com.market.carrot.dto.UserDto;
import com.market.carrot.dto.UserSaveDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(UserSaveDto userSaveDto){
        return userRepository.save(userRepository.save(userSaveDto.toEntity())).getId();
    }

    @Transactional
    public UserDto login(String email, String password){
        UserDto user = findByEmail(email);
        if(user.getPassword().equals(password))
            return user;
        else return null;

    }

    @Transactional
    public UserDto findByEmail(String email){
        User user = userRepository.findByEmail(email).orElseThrow(()->new IllegalArgumentException("존재하지 않는 이메일입니다."));
        return new UserDto(user);
    }
}

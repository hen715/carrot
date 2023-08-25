package com.market.carrot.service;

import com.market.carrot.domain.user.User;
import com.market.carrot.domain.user.UserRepository;
import com.market.carrot.dto.UserDto;
import com.market.carrot.dto.UserSaveDto;
import jakarta.transaction.Transactional;
import jdk.jfr.TransitionTo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long join(UserSaveDto userSaveDto){
        userRepository.findByEmail(userSaveDto.getEmail()).ifPresent(existingUser -> {
            throw new IllegalStateException("이미 존재하는 사용자입니다.");
        });
        userSaveDto.setPassword(passwordEncoder.encode(userSaveDto.getPassword()));
        return userRepository.save(userRepository.save(userSaveDto.toEntity())).getId();
    }

/*    @Transactional
    public UserDto login(String email, String password){
        UserDto user = findByEmail(email);
        if(user.getPassword().equals(password))
            return user;
        else return null;

    }*/

    @Transactional
    public UserDto findByEmail(String email){
        User user = userRepository.findByEmail(email).orElseThrow(()->new IllegalArgumentException("존재하지 않는 이메일입니다."));
        return new UserDto(user);
    }

    @Transactional
    public User getUser(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            return user.get();
        }else {
            throw new IllegalStateException("사용자가 없습니다.");
        }
    }

}

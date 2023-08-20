package com.market.carrot.service;

import com.market.carrot.domain.user.UserRepository;
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
}

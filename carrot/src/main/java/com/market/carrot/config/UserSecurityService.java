package com.market.carrot.config;

import com.market.carrot.domain.user.Role;
import com.market.carrot.domain.user.User;
import com.market.carrot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> _user = userRepository.findByEmail(email);
        if(_user.isEmpty()){
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        User user = _user.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if("hen715@naver.com".equals(email)){
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getKey()));
        }else{
            authorities.add(new SimpleGrantedAuthority(Role.USER.getKey()));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);

    }
}

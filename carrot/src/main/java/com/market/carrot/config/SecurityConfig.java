package com.market.carrot.config;

import com.market.carrot.domain.user.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Logger;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeRequests((authorizeRequests)-> authorizeRequests.
                        requestMatchers(new AntPathRequestMatcher("/")).permitAll())
                .authorizeRequests((authorizeRequests)-> authorizeRequests.
                        requestMatchers(new AntPathRequestMatcher("/join/**")).permitAll())
                .authorizeRequests((authorizeRequests)-> authorizeRequests.
                        requestMatchers(new AntPathRequestMatcher("/api/v2/join")).permitAll())
                .authorizeRequests((authorizeRequests)-> authorizeRequests.
                        requestMatchers(new AntPathRequestMatcher("/itemShow/**")).permitAll())
                .authorizeRequests((authorizeRequests)-> authorizeRequests.
                        requestMatchers(new AntPathRequestMatcher("/index.js")).permitAll())
                .authorizeRequests((authorizeRequests)-> authorizeRequests.
                        requestMatchers(new AntPathRequestMatcher("/js/**")).permitAll())
                .authorizeRequests((authorizeRequests)-> authorizeRequests.
                        requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll())
                .authorizeRequests((authorizeRequests)-> authorizeRequests.
                        requestMatchers(new AntPathRequestMatcher("/image/**")).permitAll())
                .authorizeRequests((authorizeRequests)-> authorizeRequests.
                        requestMatchers(new AntPathRequestMatcher("/loginFail")).permitAll())
                .authorizeRequests((authorizeRequests)-> authorizeRequests.
                        requestMatchers(new AntPathRequestMatcher("/api/v1/**")).hasRole(Role.USER.name()).anyRequest().authenticated())
                .csrf((csrf)-> csrf.
                        disable())
                .headers((headers)->headers
                        .addHeaderWriter(new XFrameOptionsHeaderWriter(
                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
                .formLogin((formLogin)->formLogin
                        .loginPage("/user/login")
                        .defaultSuccessUrl("/")
                        .failureHandler((request, response, exception) -> {
                            String msg = exception.toString();
                            if (exception instanceof UsernameNotFoundException ) {
                                msg = "존재하지 않는 계정입니다.";
                            } else if (exception instanceof BadCredentialsException) {
                                msg = "아이디와 비밀번호를 확인해주세요.";
                            }
                            Logger log = Logger.getGlobal();
                            log.info(msg);
                            response.sendRedirect("/");
                        }))
                .exceptionHandling(auth->auth.authenticationEntryPoint((request, response, authException) -> {
                    Logger log = Logger.getGlobal();
                    log.info("인증권한이 없습니다.");
                    response.sendRedirect("/");
                }))
                .logout((logout)->logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true))
                ;
        return http.build();
    }



    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

}

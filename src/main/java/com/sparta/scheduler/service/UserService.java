package com.sparta.scheduler.service;

import com.sparta.scheduler.dto.LoginRequestDto;
import com.sparta.scheduler.dto.SignupRequestDto;
import com.sparta.scheduler.entity.User;
import com.sparta.scheduler.entity.UserRoleEnum;
import com.sparta.scheduler.jwt.JwtUtil;
import com.sparta.scheduler.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String nickname = requestDto.getNickname();

        if (username.length() < 4 || username.length() > 10 || !Pattern.matches("^[a-z0-9]+$", username)) {
            throw new IllegalArgumentException("username은 최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)로 구성되어야 합니다.");
        }

        if (password.length() < 8 || password.length() > 15 || !Pattern.matches("^[a-zA-Z0-9]+$", password)) {
            throw new IllegalArgumentException("password는 최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성되어야 합니다.");
        }

        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(username, encodedPassword, nickname, role);
        userRepository.save(user);
    }

    public void login(LoginRequestDto requestDto, HttpServletResponse response) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String token = jwtUtil.createToken(username, user.getRole());
        jwtUtil.addJwtToCookie(token, response);

        Cookie usernameCookie = new Cookie("username", username);
        response.addCookie(usernameCookie);

        Cookie userIdCookie = new Cookie("user_id", user.getId().toString());
        response.addCookie(userIdCookie);
    }
}
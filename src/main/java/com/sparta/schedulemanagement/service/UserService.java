package com.sparta.schedulemanagement.service;

import com.sparta.schedulemanagement.dto.SignupRequestDto;
import com.sparta.schedulemanagement.entity.User;
import com.sparta.schedulemanagement.entity.UserRoleEnum;
import com.sparta.schedulemanagement.exception.NotFoundException;
import com.sparta.schedulemanagement.jwt.JwtUtil;
import com.sparta.schedulemanagement.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public ResponseEntity<String> signup(SignupRequestDto requestDto) {

        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("중복되는 아이디입니다.");
        }

        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        User user = new User(requestDto.getNickname(), username, password, role);
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body("회원가입이 완료됐습니다.");
    }

    public ResponseEntity<String> reissueToken(HttpServletRequest request, HttpServletResponse response) {

        String refreshTokenValue = jwtUtil.getRefreshTokenFromHeader(request);

        if(jwtUtil.validateToken(refreshTokenValue)) {
            log.error("Token Error");
            return ResponseEntity.status(401).body("Refresh 토큰이 유효하지 않습니다.");
        }

        Claims info = jwtUtil.getUserInfoFromToken(refreshTokenValue);

        User user = userRepository.findByUsername(info.getSubject()).orElseThrow(
                () -> new NotFoundException("사용자가 존재하지 않습니다.")
        );

        String accessToken = jwtUtil.createAccessToken(user.getUsername(), user.getRole());
        String refreshToken = jwtUtil.createRefreshToken(user.getUsername(), user.getRole());
        response.addHeader(JwtUtil.AUTHORIZATION_ACCESS_HEADER, accessToken);
        response.addHeader(JwtUtil.AUTHORIZATION_REFRESH_HEADER, refreshToken);

        return ResponseEntity.status(200).body("새 토큰 발급 성공");

    }
}

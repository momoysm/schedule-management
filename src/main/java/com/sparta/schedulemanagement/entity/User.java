package com.sparta.schedulemanagement.entity;

import com.sparta.schedulemanagement.dto.SignupRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user")
@NoArgsConstructor
public class User extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, unique = true)
    @Size(min = 4, max = 10, message = "아이디는 4자에서 10자 사이입니다.")
    @Pattern(regexp = "[a-z0-9]*$", message = "아이디 형식이 일치하지 않습니다.")
    private String username;

    @Column(nullable = false)
    @Size(min = 8, max = 15, message = "비밀번호는 8자에서 15자 사이입니다.")
    @Pattern(regexp = "[a-zA-Z0-9]*$", message = "비밀번호 형식이 일치하지 않습니다.")
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(SignupRequestDto requestDto, UserRoleEnum role){
        this.nickname = requestDto.getNickname();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.role = role;
    }

}

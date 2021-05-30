package com.sparta.clonecode.service;

import com.sparta.clonecode.dto.SignupRequestDto;
import com.sparta.clonecode.model.User;
import com.sparta.clonecode.model.UserRole;
import com.sparta.clonecode.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_ID_REGEX =
            Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String text, Pattern pattern) {
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }


    public void registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        // 회원 ID 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        boolean isemail = validate(requestDto.getEmail(), VALID_EMAIL_ADDRESS_REGEX);
        if (isemail == false) {
            throw new IllegalArgumentException("이메일 형식을지켜라 !!.");
        }

        boolean isId = validate(requestDto.getUsername(), VALID_ID_REGEX);
        if (isId == false) {
            throw new IllegalArgumentException("형식이 지키라우");
        }


        // 패스워드 인코딩
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();
        // 사용자 ROLE 확인
        UserRole role = UserRole.USER;
        if (requestDto.isAdmin()) {

        }

        User user = new User(username, password, email, role);
        userRepository.save(user);
    }
}

package com.kh.Calendar.service;

import com.kh.Calendar.dto.TokenDto;
import com.kh.Calendar.dto.UserRequestDto;
import com.kh.Calendar.dto.UserResponseDto;
import com.kh.Calendar.entity.User;
import com.kh.Calendar.repository.UserRepository;
import com.kh.Calendar.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // final 필드에 대한 생성자 자동 생성
@Transactional(readOnly = true) // 기본적으로는 읽기 전용 트랜잭션
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional // 쓰기 작업이므로 readOnly=false 적용
    public UserResponseDto signUp(UserRequestDto requestDto) {
        // 아이디 중복 체크
        if (userRepository.findByUserId(requestDto.getUserId()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        // 비밀번호 암호화
        User user = requestDto.toEntity(passwordEncoder);
        User savedUser = userRepository.save(user);
        return UserResponseDto.of(savedUser);
    }

    @Override
    @Transactional
    public TokenDto signIn(UserRequestDto requestDto) {
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(requestDto.getUserId(), requestDto.getUserPwd());

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        String token = jwtTokenProvider.createToken(authentication);

        // 4. 사용자 정보 조회 (토큰과 함께 반환하기 위해)
        User user = userRepository.findByUserId(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자 정보를 찾을 수 없습니다."));

        // 5. TokenDto 반환
        return TokenDto.builder()
                .grantType("Bearer")
                .accessToken(token)
                .tokenExpiresIn(86400000L)
                .userNo(user.getUserNo())
                .userId(user.getUserId())
                .userName(user.getUserName())
                .build();
    }

    @Override
    @Transactional
    public UserResponseDto updateUser(Long userNo, UserRequestDto requestDto) {
        User user = userRepository.findByUserNo(userNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원을 찾을 수 없습니다."));

        // 엔티티에 추가한 update 메소드를 사용하여 상태 변경
        user.update(requestDto.getUserName(), requestDto.getUserPhone(), requestDto.getUserEmail());

        // 트랜잭션이 커밋될 때 변경 감지(Dirty Checking)에 의해 자동으로 UPDATE 쿼리가 실행됨
        return UserResponseDto.of(user);
    }

    @Override
    public String findUserId(UserRequestDto requestDto) {
        return userRepository.findUserId(requestDto.getUserName(), requestDto.getUserPhone())
                .orElseThrow(() -> new IllegalArgumentException("해당 정보와 일치하는 아이디가 없습니다."));
    }

    @Override
    @Transactional
    public void resetUserPwd(UserRequestDto requestDto) {
        // 1. 사용자 확인 (아이디, 이름, 핸드폰번호)
        User user = userRepository.findByUserIdAndUserNameAndUserPhone(
                requestDto.getUserId(), requestDto.getUserName(), requestDto.getUserPhone())
                .orElseThrow(() -> new IllegalArgumentException("입력하신 정보와 일치하는 사용자를 찾을 수 없습니다."));

        // 2. 새 비밀번호 암호화 및 저장
        // newPassword 필드가 있으면 그것을 사용하고, 없으면 userPwd 필드를 사용하도록 유연하게 처리
        String newPassword = requestDto.getNewPassword();
        if (newPassword == null || newPassword.isBlank()) {
            newPassword = requestDto.getUserPwd();
        }
        
        if (newPassword == null || newPassword.isBlank()) {
             throw new IllegalArgumentException("새 비밀번호를 입력해주세요.");
        }

        String encryptedPwd = passwordEncoder.encode(newPassword);
        user.updatePassword(encryptedPwd);
        // userRepository.save(user); // JPA 변경 감지로 인해 생략 가능하지만 명시적으로 호출해도 무방
    }

    @Override
    @Transactional
    public void deleteUser(Long userNo) {
        User user = userRepository.findByUserNo(userNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원을 찾을 수 없습니다."));
        userRepository.delete(user);
    }

    @Override
    public boolean checkIdDuplicate(String userId) {
        // 존재하면 true, 없으면 false 반환
        return userRepository.findByUserId(userId).isPresent();
    }
}

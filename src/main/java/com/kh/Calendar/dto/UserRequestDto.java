package com.kh.Calendar.dto;

import com.kh.Calendar.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    // 회원가입, 로그인, 아이디 찾기 등 공통 필드
    // 상황에 따라 @NotBlank가 방해가 될 수 있으므로(예: 로그인 시 이름 불필요), 
    // 엄격한 검증은 별도 DTO 분리 혹은 Group Validation을 쓰는 게 정석이지만,
    // 여기서는 편의상 유지하되, 필요한 필드만 채워서 보내는 방식을 가정합니다.
    // (단, @Valid가 붙은 컨트롤러 메서드에서는 모든 필드를 검사하므로 주의 필요)
    // -> 비밀번호 재설정 시에는 userId, userName, userPhone, newPassword가 필요함.

    private String userId;

    private String userPwd; // 로그인 시 사용

    private String userName;

    private String userPhone;

    private String userEmail;
    
    // 비밀번호 재설정용 필드 추가
    private String newPassword;

    // DTO -> Entity 변환 메소드 (비밀번호 암호화 적용)
    public User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
                .userId(userId)
                .userPwd(passwordEncoder.encode(userPwd))
                .userName(userName)
                .userPhone(userPhone)
                .userEmail(userEmail)
                .build();
    }
}

package ainish.member.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class signUpDto {
    @Email
    String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()-_=+\\[\\]{}|;:'\",.<>?/])\\S{8,}$",
            message = "비밀번호는 최소 8자 이상이며, 숫자, 영소문자, 영대문자, 특수문자가 각각 1개 이상 포함되어야 합니다.")
    String password;

    @NotEmpty
    String username;
}

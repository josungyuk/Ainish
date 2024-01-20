package ainish.member.dto;

import lombok.*;

import javax.validation.constraints.Email;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class loginDto {
    @Email
    String email;

    String password;
}

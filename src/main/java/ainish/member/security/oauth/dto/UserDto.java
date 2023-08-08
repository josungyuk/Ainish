package ainish.member.security.oauth.dto;

import ainish.member.entity.Member;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private Long id;
    private String username;
    private String email;
    private String image;

    public UserDto(Member member){
        this.username = member.getUsername();
        this.email = member.getEmail();
        this.image = member.getEmail();
    }
}

package ainish.member.security.oauth.dto;

import ainish.member.entity.Member;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OauthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String profile;

    public static OauthAttributes of(String registrationId, String usernameAttributeName, Map<String, Object> attributes){
        return ofNaver(usernameAttributeName, attributes);
    }

    private static OauthAttributes ofNaver(String usernameAttributeName, Map<String, Object> attributes){
        return OauthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .profile((String) attributes.get("profile"))
                .attributes(attributes)
                .nameAttributeKey(usernameAttributeName)
                .build();
    }

    public Member toEntity(){
        return Member.builder()
                .username(name)
                .email(email)
                .role(Member.Role.USER)
                .build();
    }
}

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
    private String image;

    public static OauthAttributes of(String registrationId, String usernameAttributeName, Map<String, Object> attributes){
        if(registrationId.equals("naver"))
            return ofNaver("id", attributes);
        else if(registrationId.equals("google"))
            return ofGoogle(usernameAttributeName, attributes);

        return ofNaver(usernameAttributeName, attributes);
    }

    private static OauthAttributes ofGoogle(String usernameAttributeName, Map<String, Object> attributes){
        return OauthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .image((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(usernameAttributeName)
                .build();
    }

    private static OauthAttributes ofNaver(String usernameAttributeName, Map<String, Object> attributes){
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OauthAttributes.builder()
                .name((String) response.get("nickname"))
                .email((String) response.get("email"))
                .image((String) response.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(usernameAttributeName)
                .build();
    }

    public Member toEntity(){
        return Member.builder()
                .username(name)
                .email(email)
                .image(image)
                .role(Member.Role.USER)
                .build();
    }
}

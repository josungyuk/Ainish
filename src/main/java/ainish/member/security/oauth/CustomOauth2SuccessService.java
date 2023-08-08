package ainish.member.security.oauth;

import ainish.member.entity.Member;
import ainish.member.repository.MemberJpaRepository;
import ainish.member.security.oauth.dto.OauthAttributes;
import ainish.member.security.oauth.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOauth2SuccessService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String usernameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OauthAttributes attributes = OauthAttributes.of(registrationId, usernameAttributeName, oAuth2User.getAttributes());

        Member member = saveOrUpdate(registrationId, attributes);

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(member.getRole().getKey()))
        , attributes.getAttributes()
        , attributes.getNameAttributeKey());
    }

    private Member saveOrUpdate(String registrationId, OauthAttributes attributes){
        if(registrationId.equals("naver"))
            attributes.setEmail("Naver:" + attributes.getEmail());

        Member member = memberJpaRepository.findByEmailOptional(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName()))
                .orElse(attributes.toEntity());

        member.setActive(true);

        return memberJpaRepository.save(member);
    }
}

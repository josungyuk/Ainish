package ainish.member.security.handler;

import ainish.member.entity.Member;
import ainish.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomOauth2SuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String username = oAuth2User.getAttribute("name");

        Member member = memberJpaRepository.findByUsername(username);

        getRedirectStrategy().sendRedirect(request, response, "http://localhost:8080/api");
    }
}

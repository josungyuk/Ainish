package ainish.member.service;

import ainish.member.dto.loginDto;
import ainish.member.entity.Member;
import ainish.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberJpaRepository memberJpaRepository;

    public long login(loginDto dto){
        Member user = memberJpaRepository.findByEmail(dto.getEmail()).orElseThrow( () -> new RuntimeException("존재하지 않은 회원"));

        if(user.getPassword().equals(dto.getPassword())) return user.getId();

        return -1L;
    }
}

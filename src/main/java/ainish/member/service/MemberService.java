package ainish.member.service;

import ainish.member.dto.signUpDto;
import ainish.member.entity.Member;
import ainish.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberJpaRepository memberJpaRepository;

    public long signUp(signUpDto dto){
        boolean isEmailPresent = memberJpaRepository.findByEmail(dto.getEmail()) != null;
        boolean isUsernamePresent = memberJpaRepository.findByUsername(dto.getUsername()) != null;

        if(isEmailPresent)
            return -1L;
        else if(isUsernamePresent)
            return -2L;
        else if(isEmailPresent && isUsernamePresent)
            return -3L;

        Member member = Member.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();

        long createdMemberId = memberJpaRepository.save(member).getId();

        return createdMemberId;
    }
}

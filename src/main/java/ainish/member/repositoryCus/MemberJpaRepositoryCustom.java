package ainish.member.repositoryCus;

import ainish.member.entity.Member;

import java.util.Optional;

public interface MemberJpaRepositoryCustom {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByUsername(String username);
}

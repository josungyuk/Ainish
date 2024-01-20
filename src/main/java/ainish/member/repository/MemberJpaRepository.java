package ainish.member.repository;

import ainish.member.entity.Member;
import ainish.member.repositoryCus.MemberJpaRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member, Long>, MemberJpaRepositoryCustom {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByUsername(String username);
}

package ainish.member.repository;

import ainish.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member, Integer> {
    @Query("select m from Member m where m.email = :email")
    Optional<Member> findByEmailOptional(String email);

    @Query("select m from Member m where m.username = :username")
    Optional<Member> findByUsernameOptional(String username);

    @Query("select m from Member m where m.email = :email")
    Member findByEmail(String email);

    @Query("select m from Member m where m.username = :username")
    Member findByUsername(String username);
}

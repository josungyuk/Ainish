package ainish.member.repositoryIml;

import ainish.member.entity.Member;
import ainish.member.entity.QMember;
import ainish.member.repositoryCus.MemberJpaRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class MemberJpaRepositoryImpl implements MemberJpaRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Member> findByEmail(String email){
        QMember member = QMember.member;

        return Optional.ofNullable(
                queryFactory.select(member)
                        .from(member)
                        .where(member.email.eq(email))
                        .fetchOne()
        );
    }
    @Override
    public Optional<Member> findByUsername(String username){
        QMember member = QMember.member;

        return Optional.ofNullable(
                queryFactory.select(member)
                        .from(member)
                        .where(member.username.eq(username))
                        .fetchOne()
        );
    }
}

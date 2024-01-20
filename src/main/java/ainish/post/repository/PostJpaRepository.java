package ainish.post.repository;

import ainish.member.repositoryCus.MemberJpaRepositoryCustom;
import ainish.post.entity.Post;
import ainish.post.repositoryCustom.PostJpaRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostJpaRepository extends JpaRepository<Post, Long>, PostJpaRepositoryCustom {
    Optional<Post> findByTitle(String title);
}

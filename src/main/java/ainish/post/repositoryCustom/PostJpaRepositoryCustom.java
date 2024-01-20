package ainish.post.repositoryCustom;

import ainish.post.entity.Post;

import java.util.Optional;

public interface PostJpaRepositoryCustom {
    Optional<Post> findByTitle(String title);
}

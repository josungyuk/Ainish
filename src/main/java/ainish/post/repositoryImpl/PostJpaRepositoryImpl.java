package ainish.post.repositoryImpl;

import ainish.post.entity.Post;
import ainish.post.entity.QPost;
import ainish.post.repositoryCustom.PostJpaRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PostJpaRepositoryImpl implements PostJpaRepositoryCustom {
    private  final JPAQueryFactory queryFactory;

    public Optional<Post> findByTitle(String title){
        QPost post = QPost.post;

        return Optional.ofNullable(
                queryFactory.select(post)
                        .from(post)
                        .where(post.title.eq(title))
                        .fetchOne()
        );
    }
}

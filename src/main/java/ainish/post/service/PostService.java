package ainish.post.service;

import ainish.post.dto.createDto;
import ainish.post.dto.deleteDto;
import ainish.post.entity.Post;
import ainish.post.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostJpaRepository postJpaRepository;

    public long create(createDto dto, String url){
        Post post = Post.builder()
                        .title(dto.getTitle())
                                .content(dto.getContent())
                                        .imgUrl(url)
                                                .build();

        Post result = postJpaRepository.save(post);
        return result.getId();
    }

    public void delete(deleteDto dto){
        Post post = postJpaRepository.findByTitle(dto.getTitle()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));
        postJpaRepository.delete(post);
    }
}

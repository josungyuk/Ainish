package ainish.post.entity;

import ainish.config.BaseTimeEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Post extends BaseTimeEntity {
    @Id
    private long id;
    private String title;
    private String content;
    private String imgUrl;
}

package project.entity;

import lombok.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "posts")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(name = "content")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", updatable = false)
    @CreationTimestamp
    @EqualsAndHashCode.Exclude
    private Date create;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated")
    @UpdateTimestamp
    @EqualsAndHashCode.Exclude
    private Date updated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    private Writer writer;

    public Post(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", create=" + create +
                ", updated=" + updated +
                ", writer=" + writer.getId() +
                '}';
    }
}

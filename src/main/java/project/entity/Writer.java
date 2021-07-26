package project.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "writers")
public class Writer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", referencedColumnName = "id")
    private Label label;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "writer")
    @EqualsAndHashCode.Exclude
    private List<Post> posts = new ArrayList<>();

    public Writer(String firstName, String lastName, Label label) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.label= label;
    }

    public void addPost(Post post){
        post.setWriter(this);
        posts.add(post);
    }

    public void addPosts(List<Post> postList){
        postList = postList.stream().peek(p -> p.setWriter(this)).collect(Collectors.toList());
        posts.addAll(postList);
    }
}

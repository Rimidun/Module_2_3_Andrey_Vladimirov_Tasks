package project.repository.implementation;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.MyRepository;
import project.entity.Post;
import project.repository.PostRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@MyRepository
public class PostRepositoryImpl extends GenericRepositoryImp<Post, Long> implements PostRepository {

    public PostRepositoryImpl() {
        super(Post.class);
    }

    @Override
    public Post getByName(String name) {
        Post post = null;
        String sqlQuery = String.format("SELECT * FROM posts WHERE content='%s'", name);
        try (
                Session session = super.getSession()
        ) {
            post = (Post) session.createSQLQuery(sqlQuery).addEntity(Post.class).uniqueResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return post;
    }

    @Override
    public Post getByContent(String content) {
        return getByName(content);
    }

    @Override
    public Post getById(Long id) {
        Post post = null;
        try (
                Session session = super.getSession()
        ) {
            post = session.get(Post.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return post;
    }

    @Override
    public List<Post> getByWriterId(Long writerId) {
        List<Post> posts = null;
        try (
                Session session = super.getSession()
        ) {
            posts = session.createSQLQuery(String.format("SELECT * FROM posts WHERE writer_id=%d", writerId))
                    .addEntity(Post.class)
                    .getResultList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if(posts == null){
            return new ArrayList<>();
        }

        return posts;
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        try (
                Session session = super.getSession()
        ) {
            posts = session.createSQLQuery("SELECT * FROM posts")
                    .addEntity(Post.class)
                    .list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return posts;
    }

    @Override
    public Post save(Post post) {
        Transaction tx = null;

        try (
                Session session = super.getSession()
        ) {
            tx = session.getTransaction();
            tx.begin();
            session.save(post);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e.getMessage());
        }

        return post;
    }

    @Override
    public Post update(Post post) {
        Transaction tx = null;
        try (
                Session session = super.getSession()
        ) {
            tx = session.getTransaction();
            tx.begin();
            session.update(post);
            session.refresh(post);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e.getMessage());
        }

        return post;
    }

    @Override
    public void remove(Post post) {
        Transaction tx = null;
        try (
                Session session = super.getSession()
        ) {
            tx = session.getTransaction();
            tx.begin();
            session.delete(post);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e.getMessage());
        }
    }
}

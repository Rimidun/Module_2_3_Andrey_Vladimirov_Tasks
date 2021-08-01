package project.repository.implementation;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.MyRepository;
import project.entity.Label;
import project.repository.LabelRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@MyRepository
public class LabelRepositoryImpl extends GenericRepositoryImp<Label, Long> implements LabelRepository {

    public LabelRepositoryImpl() {
        super(Label.class);
    }

    @Override
    public Label getById(Long id) {
        Label find = null;
        try (
                Session session = super.getSession()
        ) {
            find = session.get(Label.class, id);
        }

        return find;
    }

    @Override
    public Label getByName(String name) {
        Label find = null;
        try (
                Session session = super.getSession()
        ) {
            String sqlQuery = String.format("SELECT * FROM labels WHERE name='%s'", name);
            find = (Label) session.createSQLQuery(sqlQuery)
                    .addEntity(Label.class)
                    .uniqueResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return find;
    }

    @Override
    public List<Label> getAll() {
        return null;
    }

    @Override
    public Label save(Label label) {
        Transaction tx = null;

        try (Session session = super.getSession()
        ) {
            tx = session.getTransaction();
            tx.begin();
            session.save(label);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e.getMessage());
        }

        return label;
    }

    @Override
    public Label update(Label label) {
        Transaction tx = null;
        String sqlQuery = String.format("UPDATE labels SET name='%s' WHERE id=%d", label.getName(), label.getId());
        try (
                Session session = super.getSession()
        ) {
            tx = session.getTransaction();
            int row = session.createSQLQuery(sqlQuery).addEntity(Label.class).executeUpdate();
            if (row == 0 || row > 1) {
                //Some Warn
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e.getMessage());
        }

        return label;
    }

    @Override
    public void remove(Label label) {
        Transaction tx = null;

        try (
                Session session = super.getSession()
        ) {
            tx = session.getTransaction();
            tx.begin();

            session.delete(label);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e.getMessage());
        }
    }
}

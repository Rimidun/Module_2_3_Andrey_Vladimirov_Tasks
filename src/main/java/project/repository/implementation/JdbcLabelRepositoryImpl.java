package project.repository.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.entity.Label;
import project.repository.DBUtils;
import project.repository.LabelRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcLabelRepositoryImpl implements LabelRepository {
    private final Logger log = LoggerFactory.getLogger("LabelRepositoryImpl");

    public Label save(Label entity) {
        Label label = get(entity.getName());
        if (label != null) {
            return label;
        }

        try (Statement stmt = DBUtils.getStatement()) {
            int row = stmt.executeUpdate(
                    String.format(DBUtils.SAVE_LABEL, entity.getName()),
                    Statement.RETURN_GENERATED_KEYS
            );
            if (row == 0) {
                log.warn("IN save - Note " + entity + " not saved.");
                return null;
            }
            if (row > 1) {
                log.warn("IN save - SAVE " + entity + " affected other notes.");
            }
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setId(rs.getLong(1));
                    log.info("IN - save - Added new note " + entity);
                } else
                    throw new SQLException("The save was successful, but could not get the id for the note " + entity);
            }
        } catch (SQLException e) {
            log.warn("IN - save - " + e.getMessage());
        }

        return entity;
    }

    public Label get(Long id) {
        Label find = null;

        try (
                Statement st = DBUtils.getStatement();
                ResultSet rs = st.executeQuery(String.format(DBUtils.GET_LABEL_BY_ID, id))
        ) {
            while (rs.next()) {
                find = new Label(rs.getLong("id"),
                        rs.getString("name"));
            }
        } catch (SQLException e) {
            log.warn("IN - Label(get) - " + e.getMessage());
        }

        return find;
    }

    public Label get(String name) {
        Label find = null;

        try (
                Statement st = DBUtils.getStatement();
                ResultSet rs = st.executeQuery(String.format(DBUtils.GET_LABEL_BY_NAME, name))
        ) {
            if (rs.next()) {
                find = new Label(rs.getLong("id"),
                        rs.getString("name"));
                return find;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return find;
    }

    @Override
    public Label update(Label label) {
        try (Statement st = DBUtils.getStatement()) {
            int isUpdated = st.executeUpdate(String.format(DBUtils.UPDATE_LABEL, label.getName(), label.getId()));
            if (isUpdated == 1) {
                log.info("IN - Labels(update) - Note updated to " + label);
                return label;
            } else {
                log.warn("Note " + label + " not updated.");
            }
        } catch (SQLException e) {
            log.error("IN - Labels(update) - " + e.getMessage());
        }

        return null;
    }

    @Override
    public void remove(Long id) {
        try (Statement st = DBUtils.getStatement()) {
            st.execute(String.format(DBUtils.DELETE_LABEL, id));
        } catch (SQLException e) {
            log.error("IN - Labels(remove) - " + e.getMessage());
        }
    }
}

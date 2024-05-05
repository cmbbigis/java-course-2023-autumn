package edu.java.domain.jdbc;

import edu.java.domain.entity.Chat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ChatDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(Chat chat) {
        String sql = "INSERT INTO Chat (name, created_at, created_by) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, chat.getName(), chat.getCreatedAt(), chat.getCreatedBy());
    }

    public void remove(Long id) {
        String sql = "DELETE FROM Chat WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Chat> findAll() {
        String sql = "SELECT * FROM Chat";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new Chat(rs.getLong("id"),
                rs.getString("name"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getString("created_by")));
    }
}

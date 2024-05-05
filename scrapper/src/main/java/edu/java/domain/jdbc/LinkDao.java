package edu.java.domain.jdbc;

import edu.java.domain.entity.Link;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class LinkDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String urlString = "url";
    private final String createdAtString = "created_at";
    private final String createdByString = "created_by";

    public void add(Link link) {
        String sql = "INSERT INTO Link (url, created_at, last_checked_at, created_by) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, link.getUrl(), link.getCreatedAt(), link.getLastCheckedAt(), link.getCreatedBy());
    }

    public void remove(Long id) {
        String sql = "DELETE FROM Link WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Link> findAll() {
        String sql = "SELECT * FROM Link";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new Link(rs.getLong("id"),
                rs.getString(urlString),
                rs.getTimestamp(createdAtString).toLocalDateTime(),
                rs.getString(createdByString)));
    }

    public void update(Link link) {
        String sql = "UPDATE Link SET url = ?, created_at = ?, created_by = ? WHERE id = ?";
        jdbcTemplate.update(sql, link.getUrl(),
            Timestamp.valueOf(link.getCreatedAt()), link.getCreatedBy(), link.getId());
    }

    public List<Link> findLinksNotCheckedSince(Duration duration) {
        LocalDateTime cutoff = LocalDateTime.now().minus(duration);
        String sql = "SELECT * FROM Link WHERE last_checked_at < ?";
        return jdbcTemplate.query(sql, new Object[]{Timestamp.valueOf(cutoff)}, (rs, rowNum) ->
            new Link(rs.getLong("id"),
                rs.getString(urlString),
                rs.getTimestamp(createdAtString).toLocalDateTime(),
                rs.getString(createdByString)));
    }
}

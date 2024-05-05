package edu.java.domain.jpa;

import edu.java.domain.entity.Link;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Long> {
    List<Link> findByLastCheckedAtBefore(LocalDateTime dateTime);
}

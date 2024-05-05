package edu.java.service.jdbc;

import edu.java.domain.LinkDao;
import edu.java.domain.entity.Link;
import edu.java.service.LinkService;
import java.net.URI;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JdbcLinkService implements LinkService {
    @Autowired
    private LinkDao linkDao;

    @Override
    public Link add(long tgChatId, URI url) {
        Link link = new Link();
        link.setUrl(url.toString());
        linkDao.add(link);
        return link;
    }

    @Override
    public Link remove(long tgChatId, URI url) {
        Link link = linkDao.findAll().stream().filter(l -> l.getUrl().equals(url.toString())).findFirst().orElse(null);
        if (link != null) {
            linkDao.remove(link.getId());
        }
        return link;
    }

    @Override
    public Collection<Link> listAll(long tgChatId) {
        return linkDao.findAll();
    }
}

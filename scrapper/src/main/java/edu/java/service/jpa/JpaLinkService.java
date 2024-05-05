package edu.java.service.jpa;

import edu.java.domain.entity.Link;
import edu.java.domain.jpa.LinkRepository;
import edu.java.service.LinkService;
import java.net.URI;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JpaLinkService implements LinkService {
    @Autowired
    private LinkRepository linkRepository;

    @Override
    public Link add(long tgChatId, URI url) {
        Link link = new Link();
        link.setUrl(url.toString());
        return linkRepository.save(link);
    }

    @Override
    public Link remove(long tgChatId, URI url) {
        Link link = linkRepository.findById(tgChatId).orElse(null);
        if (link != null) {
            linkRepository.delete(link);
        }
        return link;
    }

    @Override
    public Collection<Link> listAll(long tgChatId) {
        return linkRepository.findAll();
    }
}

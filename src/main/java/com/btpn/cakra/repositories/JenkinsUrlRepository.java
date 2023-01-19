package com.btpn.cakra.repositories;

import com.btpn.cakra.models.JenkinsUrl;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JenkinsUrlRepository implements PanacheMongoRepository<JenkinsUrl> {
    public JenkinsUrl findByGroudId(Long groupId) {
        return find("groupId", groupId).firstResult();
    }
}

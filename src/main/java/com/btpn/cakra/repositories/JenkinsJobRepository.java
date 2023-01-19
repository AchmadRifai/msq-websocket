package com.btpn.cakra.repositories;

import com.btpn.cakra.models.JenkinsJob;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JenkinsJobRepository implements PanacheMongoRepository<JenkinsJob> {
    public JenkinsJob findByJobName(String jobName) {
        return find("jobName", jobName).firstResult();
    }
}

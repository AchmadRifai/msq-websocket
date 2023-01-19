package com.btpn.cakra.controllers;

import com.btpn.cakra.models.JenkinsJob;
import com.btpn.cakra.repositories.JenkinsJobRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/jenkins")
public class JenkinsController {
    @Inject
    JenkinsJobRepository jobRepository;

    @GET
    public List<String> allJob() {
        return jobRepository.streamAll()
                .map(JenkinsJob::getJobName)
                .collect(Collectors.toList());
    }
}

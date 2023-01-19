package com.btpn.cakra.restclient;

import com.btpn.cakra.restclient.domains.JenkinsJobsResponse;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public interface JenkinsJobApiClient {
    @GET
    @Path("/api/json")
    JenkinsJobsResponse job(@HeaderParam("Authorization") String basicAuth);
    @GET
    @Path("/{job}/logText/progressiveText")
    String logs(@PathParam("job") Integer job, @HeaderParam("Authorization") String basicAuth);
}

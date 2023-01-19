package com.btpn.cakra.restclient;

import com.btpn.cakra.restclient.domains.JenkinsResponse;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;

public interface JenkinsApiClient {
    @GET
    @Path("/api/json")
    JenkinsResponse json(@HeaderParam("Authorization") String basicAuth);
}

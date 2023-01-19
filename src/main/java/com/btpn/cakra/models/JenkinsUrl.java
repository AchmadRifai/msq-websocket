package com.btpn.cakra.models;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@AllArgsConstructor
@Builder
@Data
@MongoEntity(collection = "jenkins_url")
@NoArgsConstructor
public class JenkinsUrl {
    private ObjectId id;
    private String jenkinsUname;
    private Long groupId;
    private Boolean jenkins;
    private String jenkinsToken;
    private String name;
    private String jenkinsUrl;
}

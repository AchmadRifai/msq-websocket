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
@MongoEntity(collection = "jenkins_job")
@NoArgsConstructor
public class JenkinsJob {
    private ObjectId id;
    private String jobName;
    private JobStatus jobStatus;
    private Boolean pushEvent;
    private String groovyScript;
    private String specificBranchEvent;
    private Long groupId;
    private Long jobHashcode;
    private Boolean tagEvent;
    private Boolean mraEvent;
    private String groupName;
    private JobStage jobStage;
    private String targetBranch;
    private String cronTrigger;
    private Boolean allBranchEvent;
    private String selectedTrigger;
    private String projectName;
    private Long stageHashcode;
    private String gitUrl;
    private Long projectId;
    private String stageLabel;
}

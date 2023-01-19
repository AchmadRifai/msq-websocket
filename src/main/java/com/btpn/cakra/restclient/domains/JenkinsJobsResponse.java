package com.btpn.cakra.restclient.domains;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class JenkinsJobsResponse {
    private String _class;
    private List<Action> actions;
    private String description;
    private String displayName;
    private String displayNameOrNull;
    private String fullDisplayName;
    private String fullName;
    private String name;
    private String url;
    private Boolean buildable;
    private List<Build> builds;
    private String color;
    private Build firstBuild;
    private List<HealthReport> healthReport;
    private Boolean inQueue;
    private Boolean keepDependencies;
    private Build lastBuild;
    private Build lastCompletedBuild;
    private Build lastFailedBuild;
    private Build lastStableBuild;
    private Build lastSuccessfulBuild;
    private Build lastUnstableBuild;
    private Build lastUnsuccessfulBuild;
    private Integer nextBuildNumber;
    private List<Object> property;
    private Object queueItem;
    private Boolean concurrentBuild;
    private Boolean resumeBlocked;
}

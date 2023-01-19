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
public class JenkinsResponse {
    private String _class;
    private List<AssignedLabel> assignedLabels;
    private String mode;
    private String nodeDescription;
    private String nodeName;
    private Integer numExecutors;
    private String description;
    private List<Job> jobs;
    private OverallLoad overallLoad;
    private View primaryView;
    private Object quietDownReason;
    private Boolean quietingDown;
    private Integer slaveAgentPort;
    private UnlabeledLoad unlabeledLoad;
    private String url;
    private Boolean useCrumbs;
    private Boolean useSecurity;
    private List<View> views;
}

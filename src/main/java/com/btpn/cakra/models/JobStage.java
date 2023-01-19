package com.btpn.cakra.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonProperty;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class JobStage {
    private Long endTimeMillis;
    private Long pauseDurationMillis;
    private Long queueDurationMillis;
    private Long startTimeMillis;
    private String name;
    private List<Stage> stages;
    private String description;
    private Long durationMillis;
    private String id;
    private Long estimatedDuration;
    private String status;
    @BsonProperty("_links")
    private Link link;
}

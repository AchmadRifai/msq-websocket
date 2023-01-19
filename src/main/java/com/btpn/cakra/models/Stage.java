package com.btpn.cakra.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonProperty;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Stage {
    private Long pauseDurationMillis;
    private Long startTimeMillis;
    private String execNode;
    private String name;
    private Long durationMillis;
    private String id;
    private String status;
    @BsonProperty("_links")
    private Link link;
}
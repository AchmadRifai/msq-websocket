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
public class JobStatus {
    private String color;
    private LastSuccessfulBuild lastSuccessfulBuild;
    private String name;
    @BsonProperty("_class")
    private String className;
}

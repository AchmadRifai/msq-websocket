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
public class LastSuccessfulBuild {
    private String displayName;
    @BsonProperty("_class")
    private String className;
    private Long timestamp;
}

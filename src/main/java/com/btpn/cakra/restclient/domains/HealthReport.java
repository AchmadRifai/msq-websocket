package com.btpn.cakra.restclient.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class HealthReport {
    private String description;
    private String iconClassName;
    private String iconUrl;
    private Integer score;
}

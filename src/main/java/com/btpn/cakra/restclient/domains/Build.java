package com.btpn.cakra.restclient.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Build {
    private String _class;
    private Integer number;
    private String url;
}

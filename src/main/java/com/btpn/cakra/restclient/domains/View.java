package com.btpn.cakra.restclient.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class View {
    private String _class;
    private String name;
    private String url;
}

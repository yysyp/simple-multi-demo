package com.jcompetence.opa.and.inmemory.authentication.security.opa;

import lombok.Data;

@Data
public class OPADataResponse {

    private OPAResult result;


    @Data
    public static class OPAResult{
        private Boolean allow;
    }


}

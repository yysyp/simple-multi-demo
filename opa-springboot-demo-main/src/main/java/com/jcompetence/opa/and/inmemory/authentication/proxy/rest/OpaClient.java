package com.jcompetence.opa.and.inmemory.authentication.proxy.rest;

import com.jcompetence.opa.and.inmemory.authentication.security.opa.OPADataRequest;
import com.jcompetence.opa.and.inmemory.authentication.security.opa.OPADataResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value = "opaAuthorization", url = "${app.opa.authz.url}")
public interface OpaClient {

    @PostMapping("/jcompetence/authz")
    OPADataResponse authorizedToAccessAPI(@RequestBody OPADataRequest opaDataRequest);

}

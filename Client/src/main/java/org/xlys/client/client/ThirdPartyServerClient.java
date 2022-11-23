package org.xlys.client.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.xlys.client.config.CustomizedFeignHttpClientFactory;

@FeignClient(name = "ThirdPartyServerClient", url = "https://localhost:9999")
public interface ThirdPartyServerClient {

    @GetMapping(value = "/secure/endpoint/ping", consumes = MediaType.APPLICATION_JSON_VALUE)
    String secureRequest();

}

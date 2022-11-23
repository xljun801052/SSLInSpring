package org.xlys.serverwithoutcertificate.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/insecure/endpoint")
public class ServiceController {

    @Value("${server.port:19998}")
    private String port;

    @GetMapping("/ping")
    public String ping() {
        log.info(">>> insecure <<< endpoint receives the request!");
        return "[insecure response] ---> " + port;
    }
}

package org.xlys.client.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xlys.client.client.ThirdPartyServerClient;

@RestController
@RequestMapping("/client/endpoint")
@Slf4j
public class ClientController {

    @Autowired
    ThirdPartyServerClient thirdPartyServerClient;

    @GetMapping("/testSSL")
    public String ping() {
        log.info("receive request at 9997");
        return thirdPartyServerClient.secureRequest();
    }

    @GetMapping("ping")
    public String ping2() {
        log.info("receive request at 9997");
        return "ping successfully";
    }
}

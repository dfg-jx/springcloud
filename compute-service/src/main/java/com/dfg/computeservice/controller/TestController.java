package com.dfg.computeservice.controller;

import com.dfg.computeservice.entity.GitAutoRefreshConfig;
import com.dfg.computeservice.entity.GitConfig;
import com.sun.istack.logging.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private GitConfig gitConfig;

    @Autowired
    private GitAutoRefreshConfig gitAutoRefreshConfig;

    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        ServiceInstance instance = client.getInstances(client.getServices().get(0)).get(0);
        Integer r = a + b;
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
        return r;
    }


    @GetMapping(value = "/show")
    public Object show(){
        String env = gitConfig.getEnv();
        String password = gitConfig.getPassword();
        String username = gitConfig.getUsername();
        return gitConfig;
    }

    @GetMapping(value = "/autoShow")
    public Object autoShow(){
        String env = gitAutoRefreshConfig.getEnv();
        String password = gitAutoRefreshConfig.getUser().getUsername();
        String username = gitAutoRefreshConfig.getUser().getUsername();
        return gitAutoRefreshConfig;
    }

}

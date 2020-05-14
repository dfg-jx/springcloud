package com.dfg.feign.client.hystrix;

import com.dfg.feign.client.ComputeClient;

public class ComputeClientHystrix implements ComputeClient {
    @Override
    public Integer add(Integer a, Integer b) {
        return -1;
    }
}

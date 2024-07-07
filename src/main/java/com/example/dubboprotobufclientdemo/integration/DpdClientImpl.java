package com.example.dubboprotobufclientdemo.integration;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import com.example.dubboprotobufserverdemo.common.model.Llm;
import com.example.dubboprotobufserverdemo.facade.DpdFacade;

@Service
public class DpdClientImpl implements DpdClient {

    @DubboReference(version = "${dubbo-service-version.protobuf-server-demo}", check = true)
    private DpdFacade dpdFacade;

    @Override
    public String ping() {
        return dpdFacade.ping();
    }

    @Override
    public void createLlm(Llm llm) {
        dpdFacade.createLlm(llm);
    }

    @Override
    public Llm getLlm(String id) {
        return dpdFacade.getLlm(id);
    }

    @Override
    public Llm getLlmTimeConsuming(String id, long duration) {
        return dpdFacade.getLlmTimeConsuming(id, duration);
    }

    @Override
    public void throwException() {
        dpdFacade.throwException();
    }

}

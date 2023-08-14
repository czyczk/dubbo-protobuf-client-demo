package com.example.dubboprotobufclientdemo.integration;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import com.example.dubboprotobufserverdemo.facade.CreateLlmRequest;
import com.example.dubboprotobufserverdemo.facade.DpdProtobufFacade;
import com.example.dubboprotobufserverdemo.facade.GetLlmRequest;
import com.example.dubboprotobufserverdemo.facade.GetLlmResponse;
import com.example.dubboprotobufserverdemo.facade.PingResponse;

@Service
public class DpdProtobufClientImpl implements DpdProtobufClient {

    @DubboReference(version = "${dubbo-service-version.protobuf-server-demo}", check = true, protocol = "tri", filter = "protocolDetectionFilter")
    private DpdProtobufFacade dpdProtobufFacade;

    @Override
    public PingResponse ping() {
        return dpdProtobufFacade.ping();
    }

    @Override
    public void createLlm(CreateLlmRequest llm) {
        dpdProtobufFacade.createLlm(llm);
    }

    @Override
    public GetLlmResponse getLlm(GetLlmRequest id) {
        return dpdProtobufFacade.getLlm(id);
    }

    @Override
    public void throwException() {
        dpdProtobufFacade.throwException();
    }

}

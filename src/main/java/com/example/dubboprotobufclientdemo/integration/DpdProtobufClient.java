package com.example.dubboprotobufclientdemo.integration;

import com.example.dubboprotobufserverdemo.facade.CreateLlmRequest;
import com.example.dubboprotobufserverdemo.facade.GetLlmRequest;
import com.example.dubboprotobufserverdemo.facade.GetLlmResponse;
import com.example.dubboprotobufserverdemo.facade.PingResponse;

public interface DpdProtobufClient {

    PingResponse ping();

    void createLlm(CreateLlmRequest llm);

    GetLlmResponse getLlm(GetLlmRequest id);

    void throwException();

}

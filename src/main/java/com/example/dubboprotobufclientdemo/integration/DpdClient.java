package com.example.dubboprotobufclientdemo.integration;

import com.example.dubboprotobufserverdemo.common.model.Llm;

public interface DpdClient {

    String ping();

    void createLlm(Llm llm);

    Llm getLlm(String id);

    Llm getLlmTimeConsuming(String id, long duration);

    void throwException();

}

package com.example.dubboprotobufclientdemo.integration;

import com.example.dubboprotobufserverdemo.common.model.Llm;

public interface DpdClient {

    String ping();

    void createLlm(Llm llm);

    Llm getLlm(String id);

    void throwException();

}

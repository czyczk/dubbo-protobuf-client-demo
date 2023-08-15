package com.example.dubboprotobufclientdemo.integration;

import com.example.dubboprotobufserverdemo.common.model.Llm;
import org.apache.dubbo.common.stream.StreamObserver;

public interface DpdClient {

    String ping();

    void createLlm(Llm llm);

    Llm getLlm(String id);

    StreamObserver<Integer> sumStream(StreamObserver<Integer> response);

    void throwException();

}

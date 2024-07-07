package com.example.dubboprotobufclientdemo.integration;

import com.example.dubboprotobufserverdemo.common.model.Llm;

import reactor.core.publisher.Mono;

public interface DpdReactiveClient {

    Mono<String> ping();

    Mono<Void> createLlm(Llm llm);

    Mono<Llm> getLlm(String id);

    Mono<Llm> getLlmTimeConsuming(String id, long duration);

    Mono<Void> throwException();

}

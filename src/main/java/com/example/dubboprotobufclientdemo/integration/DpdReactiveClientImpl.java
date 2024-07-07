package com.example.dubboprotobufclientdemo.integration;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import com.example.dubboprotobufserverdemo.common.model.Llm;
import com.example.dubboprotobufserverdemo.facade.DpdAsyncFacade;

import reactor.core.publisher.Mono;

@Service
public class DpdReactiveClientImpl implements DpdReactiveClient {

    @DubboReference(version = "${dubbo-service-version.protobuf-server-demo}", check = true)
    private DpdAsyncFacade dpdAsyncFacade;

    @Override
    public Mono<String> ping() {
        return Mono.fromFuture(dpdAsyncFacade.ping());
    }

    @Override
    public Mono<Void> createLlm(Llm llm) {
        return Mono.fromFuture(dpdAsyncFacade.createLlm(llm));
    }

    @Override
    public Mono<Llm> getLlm(String id) {
        return Mono.fromFuture(dpdAsyncFacade.getLlm(id));
    }

    @Override
    public Mono<Llm> getLlmTimeConsuming(String id, long duration) {
        return Mono.fromFuture(dpdAsyncFacade.getLlmTimeConsuming(id, duration));
    }

    @Override
    public Mono<Void> throwException() {
        return Mono.fromFuture(dpdAsyncFacade.throwException());
    }

}

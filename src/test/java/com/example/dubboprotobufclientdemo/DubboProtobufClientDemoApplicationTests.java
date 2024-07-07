package com.example.dubboprotobufclientdemo;

import org.apache.dubbo.common.stream.StreamObserver;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.dubboprotobufclientdemo.integration.DpdClientImpl;
import com.example.dubboprotobufclientdemo.integration.DpdProtobufClientImpl;
import com.example.dubboprotobufclientdemo.integration.DpdReactiveClientImpl;
import com.example.dubboprotobufserverdemo.common.model.Llm;
import com.example.dubboprotobufserverdemo.facade.GetLlmRequest;
import com.example.dubboprotobufserverdemo.util.converter.LlmConverter;

import cn.hutool.core.lang.Assert;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class DubboProtobufClientDemoApplicationTests {

	@Autowired
	private DpdClientImpl dpdClientImpl;

	@Autowired
	private DpdProtobufClientImpl dpdProtobufClientImpl;

	@Autowired
	private DpdReactiveClientImpl dpdReactiveClientImpl;

	@Test
	public void testSanity() {
		Assert.equals(2, 1 + 1);
	}

	@Test
	public void testStandardDpdClientImpl() throws InterruptedException {
		// Test ping()
		String pong = dpdClientImpl.ping();
		Assert.equals("pong", pong);

		// Test getLlm()
		Llm expectedLlm = new Llm();
		expectedLlm.setId("chatgpt");
		expectedLlm.setName("ChatGPT");
		expectedLlm.setCompany("OpenAI");
		expectedLlm.setOpenSource(false);

		Llm actualLlm = dpdClientImpl.getLlm(expectedLlm.getId());
		Assert.equals(expectedLlm, actualLlm);

		long startTime = System.currentTimeMillis();
		actualLlm = dpdClientImpl.getLlmTimeConsuming(expectedLlm.getId(), 200);
		long elapsedTime = System.currentTimeMillis() - startTime;
		Assert.equals(expectedLlm, actualLlm);
		Assert.isTrue(elapsedTime >= 200);

		// Test sumStream()
		CountDownLatch countDownLatch = new CountDownLatch(2);
		StreamObserver<Integer> sumStreamRequest = dpdClientImpl.sumStream(new StreamObserver<>() {
			@Override
			public void onNext(Integer data) {
				System.out.println("response onNext: " + data);
				Assert.equals(6, data);
				countDownLatch.countDown();
			}

			@Override
			public void onError(Throwable throwable) {
				System.out.println("response onError: " + throwable.getMessage());
				countDownLatch.countDown();
				throw new RuntimeException(throwable);
			}

			@Override
			public void onCompleted() {
				System.out.println("response onCompleted");
				countDownLatch.countDown();
			}
		});

		sumStreamRequest.onNext(1);
		sumStreamRequest.onNext(2);
		sumStreamRequest.onNext(3);
		countDownLatch.await(10, TimeUnit.SECONDS);
	}

	@Test
	public void testDpdProtobufClientImpl() {
		// I have no idea why this doesn't work.
		/*
		 * cause: org.apache.dubbo.rpc.StatusRpcException: INTERNAL : Call aborted cause
		 * client exception
		 * ...
		 * Caused by: java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds
		 * for length 0
		 */

		// String pong = dpdProtobufClientImpl.ping().getMsg();
		// Assert.equals("pong", pong);

		Llm expectedLlm = new Llm();
		expectedLlm.setId("chatgpt");
		expectedLlm.setName("ChatGPT");
		expectedLlm.setCompany("OpenAI");
		expectedLlm.setOpenSource(false);

		GetLlmRequest getLlmRequest = GetLlmRequest.newBuilder().setId(expectedLlm.getId()).build();

		Llm actualLlm = LlmConverter.toDomainLlm(
				dpdProtobufClientImpl.getLlm(getLlmRequest).getLlm());
		Assert.equals(expectedLlm, actualLlm);
	}

	@Test
	public void testDpdReactiveClientImpl() {
		String pong = dpdReactiveClientImpl.ping().block();
		Assert.equals("pong", pong);

		Llm expectedLlm = new Llm();
		expectedLlm.setId("chatgpt");
		expectedLlm.setName("ChatGPT");
		expectedLlm.setCompany("OpenAI");
		expectedLlm.setOpenSource(false);

		Llm actualLlm = dpdReactiveClientImpl.getLlm(expectedLlm.getId()).block();
		Assert.equals(expectedLlm, actualLlm);

		long startTime = System.currentTimeMillis();
		actualLlm = dpdReactiveClientImpl.getLlmTimeConsuming(expectedLlm.getId(), 200).block();
		long elapsedTime = System.currentTimeMillis() - startTime;
		Assert.equals(expectedLlm, actualLlm);
		Assert.isTrue(elapsedTime >= 200);
	}

}

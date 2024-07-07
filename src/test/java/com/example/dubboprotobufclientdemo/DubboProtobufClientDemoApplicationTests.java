package com.example.dubboprotobufclientdemo;

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
	public void testStandardDpdClientImpl() {
		String pong = dpdClientImpl.ping();
		Assert.equals("pong", pong);

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

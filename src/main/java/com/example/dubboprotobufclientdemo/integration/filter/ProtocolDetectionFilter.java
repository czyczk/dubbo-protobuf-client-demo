package com.example.dubboprotobufclientdemo.integration.filter;

import cn.hutool.core.lang.Console;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;
import org.springframework.stereotype.Component;

/**
 * @author Tanming (Zenas Chen)
 * @version 2023-08-14 16:12
 */
@Component("protocolDetectionFilter")
public class ProtocolDetectionFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Console.log(invoker.getUrl().getProtocol());

        // Proceed with the invocation
        return invoker.invoke(invocation);
    }
}
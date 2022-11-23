package org.xlys.gateway.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * Establish the gateway-service to test accessing the downstream services using HttpClient provided by Netty. <br/>
 *  if we access the endpoints in ServerWithCertificate with "https", we just need to add this config:
 *  spring.cloud.gateway.httpclient.ssl.use-insecure-trust-manager=true <br/>
 *  Refer to:{@linkplain reactor.netty.http.client.HttpClientConfig} and {@linkplain } for trusting the self-signed certificate. <br/>
 *
 *  But think about this scenario: a site provides endpoints with "https" but has no X.509 certificate. If we try to access this endpoint,what would happen?
 *  So, for re-producing this scenario, I create a service named "ServerWithoutCertificate", and typed in "localhost:8888/insecure/endpoint/ping" in my browser.
 *  Here is the backend console error log:
 * @see <a href="https://www.rfc-editor.org/rfc/rfc2246">refer to 7.2.2 decode_error</a>
 * */
/* *
 *      <textarea>
 *         io.netty.handler.codec.DecoderException: io.netty.handler.ssl.NotSslRecordException: not an SSL/TLS record: 485454502f312e31203430302042616420526571756573740d0a636f6e74656e742d6c656e6774683a20300d0a636f6e6e656374696f6e3a20636c6f73650d0a0d0a
 *          	at io.netty.handler.codec.ByteToMessageDecoder.callDecode(ByteToMessageDecoder.java:489) ~[netty-codec-4.1.84.Final.jar:4.1.84.Final]
 *          	at io.netty.handler.codec.ByteToMessageDecoder.channelRead(ByteToMessageDecoder.java:280) ~[netty-codec-4.1.84.Final.jar:4.1.84.Final]
 *          	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:444) ~[netty-transport-4.1.84.Final.jar:4.1.84.Final]
 *          	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:420) ~[netty-transport-4.1.84.Final.jar:4.1.84.Final]
 *          	at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:412) ~[netty-transport-4.1.84.Final.jar:4.1.84.Final]
 *          	at io.netty.channel.DefaultChannelPipeline$HeadContext.channelRead(DefaultChannelPipeline.java:1410) ~[netty-transport-4.1.84.Final.jar:4.1.84.Final]
 *          	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:440) ~[netty-transport-4.1.84.Final.jar:4.1.84.Final]
 *          	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:420) ~[netty-transport-4.1.84.Final.jar:4.1.84.Final]
 *          	at io.netty.channel.DefaultChannelPipeline.fireChannelRead(DefaultChannelPipeline.java:919) ~[netty-transport-4.1.84.Final.jar:4.1.84.Final]
 *          	at io.netty.channel.nio.AbstractNioByteChannel$NioByteUnsafe.read(AbstractNioByteChannel.java:166) ~[netty-transport-4.1.84.Final.jar:4.1.84.Final]
 *          	at io.netty.channel.nio.NioEventLoop.processSelectedKey(NioEventLoop.java:788) ~[netty-transport-4.1.84.Final.jar:4.1.84.Final]
 *          	at io.netty.channel.nio.NioEventLoop.processSelectedKeysOptimized(NioEventLoop.java:724) ~[netty-transport-4.1.84.Final.jar:4.1.84.Final]
 *          	at io.netty.channel.nio.NioEventLoop.processSelectedKeys(NioEventLoop.java:650) ~[netty-transport-4.1.84.Final.jar:4.1.84.Final]
 *          	at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:562) ~[netty-transport-4.1.84.Final.jar:4.1.84.Final]
 *          	at io.netty.util.concurrent.SingleThreadEventExecutor$4.run(SingleThreadEventExecutor.java:997) ~[netty-common-4.1.84.Final.jar:4.1.84.Final]
 *          	at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74) ~[netty-common-4.1.84.Final.jar:4.1.84.Final]
 *          	at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30) ~[netty-common-4.1.84.Final.jar:4.1.84.Final]
 *          	at java.base/java.lang.Thread.run(Thread.java:834) ~[na:na]
 *          Caused by: io.netty.handler.ssl.NotSslRecordException: not an SSL/TLS record: 485454502f312e31203430302042616420526571756573740d0a636f6e74656e742d6c656e6774683a20300d0a636f6e6e656374696f6e3a20636c6f73650d0a0d0a
 *          	at io.netty.handler.ssl.SslHandler.decodeJdkCompatible(SslHandler.java:1215) ~[netty-handler-4.1.84.Final.jar:4.1.84.Final]
 *          	Suppressed: reactor.core.publisher.FluxOnAssembly$OnAssemblyException:
 *          Error has been observed at the following site(s):
 *          	*__checkpoint ⇢ org.springframework.cloud.gateway.filter.WeightCalculatorWebFilter [DefaultWebFilterChain]
 *          	*__checkpoint ⇢ HTTP GET "/insecure/endpoint/ping" [ExceptionHandlingWebHandler]
 *      </textarea>
 * */
@SpringBootApplication
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

}

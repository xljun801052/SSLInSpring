package org.xlys.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.xlys.client.client.ThirdPartyServerClient;


/**
 * 实现总结：
 *      openfeign会根据是否启用了loadbalance等相关策略来启动不同的feignClient实例。{@linkplain  org.springframework.cloud.openfeign.FeignAutoConfiguration} <br/>
 *      而我们这里没有那么复杂，只有一个固定https的URL调用 {@linkplain  ThirdPartyServerClient}，所以默认使用了HttpURLConnection作为feignClient的真正发起http request 请求的client模块实现。
 *      当然，这个天生有缺陷且不方便自定义，所以我切换了底层的HTTP client的实现为{@linkplain  org.apache.http.impl.client.CloseableHttpClient}。 <br/>
 *      Refer to：</br>
 *      <a href="https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/#spring-cloud-feign-overriding-defaults">change default httpclient</a> <br/>
 *
 *      scenario1:<br/>
 *          我在服务端里面没有用证书，也没有任何SSL相关的配置。这个时候在客户端使用https访问服务端接口，即便关闭了hostname verification等待SSL认证，还是发现一直报错：SSLException: Unsupported or unrecognized SSL message
 *          debug发现是在进行SSL握手第一阶段在读{@linkplain  java.base.sun.security.ssl.SSLSocketInputRecord}时候字节数组数据有问题,抛出了异常。<br/>
 *      scenario2:<br/>
 *          在服务端补充了自签证书，并做了SSL配置，发现客户端访问通了
 *          【怀疑】没有证书但是却使用https会导致错误！
 *
 */
@SpringBootApplication
@EnableFeignClients
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}

package org.xlys.client.config;

import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConverterConfig {

    @Bean
    public Decoder feignDecoder() {
        ObjectFactory<HttpMessageConverters> messageConvertersObjectFactory = HttpMessageConverters::new;
        return new SpringDecoder(messageConvertersObjectFactory);
    }

    @Bean
    public Encoder feignEncoder() {
        ObjectFactory<HttpMessageConverters> messageConvertersObjectFactory = HttpMessageConverters::new;
        return new SpringEncoder(messageConvertersObjectFactory);
    }
}

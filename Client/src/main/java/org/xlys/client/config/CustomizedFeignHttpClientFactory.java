//package org.xlys.client.config;
//
//import feign.Client;
//import org.apache.http.client.HttpClient;
//import org.apache.http.config.Registry;
//import org.apache.http.config.RegistryBuilder;
//import org.apache.http.conn.socket.ConnectionSocketFactory;
//import org.apache.http.conn.socket.PlainConnectionSocketFactory;
//import org.apache.http.conn.ssl.NoopHostnameVerifier;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
//import org.apache.http.ssl.SSLContexts;
//import org.apache.http.ssl.TrustStrategy;
//import org.springframework.boot.autoconfigure.AutoConfigureBefore;
//import org.springframework.cloud.openfeign.FeignAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.net.ssl.*;
//import java.security.GeneralSecurityException;
//import java.security.KeyManagementException;
//import java.security.NoSuchAlgorithmException;
//import java.security.cert.CertificateException;
//import java.security.cert.X509Certificate;
//
////@Configuration
////@AutoConfigureBefore(FeignAutoConfiguration.class)
//public class CustomizedFeignHttpClientFactory {
//
//    //    @Bean
//    public Client ignoreSSLValidationFeignClient() {
//        try {
//            SSLContext sslContext = SSLContext.getInstance("SSL");
//            X509TrustManager tm = new X509TrustManager() {
//
//                @Override
//                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
//
//                }
//
//                @Override
//                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
//
//                }
//
//                @Override
//                public X509Certificate[] getAcceptedIssuers() {
//                    //                return new X509Certificate[0];
//                    return null;
//                }
//            };
//            sslContext.init(null, new TrustManager[]{tm}, null);
//            Client.Default client = new Client.Default(sslContext.getSocketFactory(), new HostnameVerifier() {
//                @Override
//                public boolean verify(String s, SSLSession sslSession) {
//                    return true;
//                }
//            });
//            return client;
//        } catch (NoSuchAlgorithmException | KeyManagementException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     *
//     */
////    @Bean
//    public Client feignClient() {
//        return new Client.Default(getSSLSocketFactory(), new NoopHostnameVerifier());
//    }
//
//    private SSLSocketFactory getSSLSocketFactory() {
//        try {
//            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
//            return sslContext.getSocketFactory();
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//
//
//    @Bean
//    public HttpClient customizedHttpClient() throws SSLException, GeneralSecurityException {
//        /**
//         *  Note: way-1---> turn off the hostname validation. will throws GeneralSecurityException
//         * */
//        TrustStrategy acceptingTrustStrategy = (cert, authType) -> true;
//        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
//        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
//
//        Registry<ConnectionSocketFactory> socketFactoryRegistry =
//                RegistryBuilder.<ConnectionSocketFactory>create()
//                        .register("https", sslsf)
//                        .register("http", new PlainConnectionSocketFactory())
//                        .build();
//
//        BasicHttpClientConnectionManager connectionManager = new BasicHttpClientConnectionManager(socketFactoryRegistry);
//        CloseableHttpClient httpClient = HttpClients
//                .custom()
//                .setSSLSocketFactory(sslsf)
//                .setConnectionManager(connectionManager)
//                .build();
//        /**
//         * Note: way-2---> client instance to make calls to a server that has self-signed/insecure/expired certificate
//         *
//         */
//        /*SslContext sslContext = SslContextBuilder
//                .forClient()
//                .trustManager(InsecureTrustManagerFactory.INSTANCE)
//                .build();
//        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));*/
//
//        return httpClient;
//
//    }
//}

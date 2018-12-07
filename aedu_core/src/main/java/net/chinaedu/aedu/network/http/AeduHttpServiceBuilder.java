package net.chinaedu.aedu.network.http;

import net.chinaedu.aedu.network.http.convertor.AeduHttpGsonConvertor;

import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * Created by MartinKent on 2017/5/22.
 */

public class AeduHttpServiceBuilder {
    private String baseUrl;
    private IAeduHttpConvertor converter;
    private IAeduHttpInterceptor headerInterceptor, paramInterceptor;
    private AeduBaseHttpServiceHandler serviceHandler;
    private AeduHttpServiceParameter httpServiceParameter;

    public static AeduHttpServiceBuilder create(String baseUrl) {
        return new AeduHttpServiceBuilder(baseUrl, null);
    }

    public static AeduHttpServiceBuilder create(String baseUrl, AeduHttpServiceParameter httpServiceParameter) {
        return new AeduHttpServiceBuilder(baseUrl, httpServiceParameter);
    }

    private AeduHttpServiceBuilder(String baseUrl, AeduHttpServiceParameter httpServiceParameter) {
        this.baseUrl = baseUrl;
        this.httpServiceParameter = httpServiceParameter;
        this.converter = AeduHttpGsonConvertor.create();
        this.headerInterceptor = new AeduHttpDefaultInterceptor();
        this.paramInterceptor = new AeduHttpDefaultInterceptor();
        this.serviceHandler = AeduHttpServiceHandlerFactory.create(this);
    }

    public IAeduHttpService build() {
        return (IAeduHttpService) Proxy.newProxyInstance(IAeduHttpService.class.getClassLoader(), new Class<?>[]{IAeduHttpService.class}, serviceHandler);
    }

    public String baseUrl() {
        return baseUrl;
    }


    public AeduHttpServiceBuilder converter(IAeduHttpConvertor converter) {
        this.converter = converter;
        return this;
    }

    public IAeduHttpConvertor converter() {
        return converter;
    }

    public AeduHttpServiceBuilder headerInterceptor(IAeduHttpInterceptor interceptor) {
        headerInterceptor = interceptor;
        return this;
    }

    public IAeduHttpInterceptor headerInterceptor() {
        return headerInterceptor;
    }

    public AeduHttpServiceBuilder paramInterceptor(IAeduHttpInterceptor interceptor) {
        paramInterceptor = interceptor;
        return this;
    }

    public IAeduHttpInterceptor paramInterceptor() {
        return paramInterceptor;
    }

    public AeduHttpServiceBuilder serviceHandler(AeduBaseHttpServiceHandler handler) {
        serviceHandler = handler;
        return this;
    }

    public AeduBaseHttpServiceHandler serviceHandler() {
        return serviceHandler;
    }

    public AeduHttpServiceParameter httpServiceParameter(){
        if(httpServiceParameter == null){
            httpServiceParameter = new AeduHttpServiceParameter.Builder().connectTimeout(20)
                    .readTimeout(20).writeTimeout(20).timeUnit(TimeUnit.SECONDS).build();
        }
        return httpServiceParameter;
    }
}

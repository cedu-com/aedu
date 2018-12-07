package net.chinaedu.aedu.network.http;

import android.support.annotation.NonNull;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class AeduBaseHttpServiceHandler implements InvocationHandler {

    private final AeduHttpServiceBuilder builder;

    protected AeduBaseHttpServiceHandler(@NonNull AeduHttpServiceBuilder builder) {
        this.builder = builder;
    }

    @Override
    public final Object invoke(Object o, Method method, Object[] args) throws Throwable {
        System.out.println("yyy method=" + method.getName());
        if (method.getDeclaringClass() == Object.class) {
            return method.invoke(this, args);
        }
        if ("get".equals(method.getName())) {
            String url = (String) args[0];
            Map<String, String> headers = (Map<String, String>) args[1];
            Map<String, String> params = (Map<String, String>) args[2];
            IAeduHttpService.Callback<?> callback = (IAeduHttpService.Callback<?>) args[3];

            return handleGet(url, builder.headerInterceptor().intercept(headers), builder.paramInterceptor().intercept(params), new AeduCallbackWrapper(builder, callback));
        } else if ("post".equals(method.getName())) {
            String url = (String) args[0];
            Map<String, String> headers = (Map<String, String>) args[1];
            Map<String, String> params = (Map<String, String>) args[2];
            IAeduHttpService.Callback<Object> callback = (IAeduHttpService.Callback<Object>) args[3];

            return handlePost(url, builder.headerInterceptor().intercept(headers), builder.paramInterceptor().intercept(params), new AeduCallbackWrapper(builder, callback));
        } else if ("put".equals(method.getName())) {
            String url = (String) args[0];
            Map<String, String> headers = (Map<String, String>) args[1];
            Map<String, String> params = (Map<String, String>) args[2];
            IAeduHttpService.Callback<Object> callback = (IAeduHttpService.Callback<Object>) args[3];

            return handlePut(url, builder.headerInterceptor().intercept(headers), builder.paramInterceptor().intercept(params), new AeduCallbackWrapper(builder, callback));
        } else if ("delete".equals(method.getName())) {
            String url = (String) args[0];
            Map<String, String> headers = (Map<String, String>) args[1];
            Map<String, String> params = (Map<String, String>) args[2];
            IAeduHttpService.Callback<Object> callback = (IAeduHttpService.Callback<Object>) args[3];

            return handleDelete(url, builder.headerInterceptor().intercept(headers), builder.paramInterceptor().intercept(params), new AeduCallbackWrapper(builder, callback));
        } else if ("upload".equals(method.getName())) {
            String url = (String) args[0];
            Map<String, String> headers = (Map<String, String>) args[1];
            Map<String, String> params = (Map<String, String>) args[2];
            Map<String, File> files = (Map<String, File>) args[3];
            IAeduHttpService.Callback<Object> callback = (IAeduHttpService.Callback<Object>) args[4];

            return handleUpload(url, builder.headerInterceptor().intercept(headers), builder.paramInterceptor().intercept(params), files, new AeduCallbackWrapper(builder, callback));
        }
        throw new RuntimeException("Method not found. method=>" + method.getName() + ", args=>" + Arrays.toString(args));
    }

    protected abstract Object handleGet(String url, Map<String, String> headers, Map<String, String> params, IAeduHttpService.Callback<String> callback);

    protected abstract Object handlePost(String url, Map<String, String> headers, Map<String, String> params, IAeduHttpService.Callback<String> callback);

    protected abstract Object handlePut(String url, Map<String, String> headers, Map<String, String> params, IAeduHttpService.Callback<String> callback);

    protected abstract Object handleDelete(String url, Map<String, String> headers, Map<String, String> params, IAeduHttpService.Callback<String> callback);

    protected abstract Object handleUpload(String url, Map<String, String> headers, Map<String, String> params, Map<String, File> files, IAeduHttpService.Callback<String> callback);
}

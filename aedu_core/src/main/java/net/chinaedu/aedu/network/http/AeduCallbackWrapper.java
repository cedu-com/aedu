package net.chinaedu.aedu.network.http;

import com.google.common.reflect.TypeToken;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Locale;

/**
 * Created by MartinKent on 2017/5/23.
 */

@SuppressWarnings("unchecked")
class AeduCallbackWrapper implements IAeduHttpService.Callback<String> {
    private final AeduHttpServiceBuilder builder;
    private final IAeduHttpService.Callback callback;

    AeduCallbackWrapper(AeduHttpServiceBuilder builder, IAeduHttpService.Callback callback) {
        this.builder = builder;
        this.callback = callback;
    }

    @Override
    public void onSuccess(final IAeduHttpResponse<String> response) {
        if (null != callback) {
            try {
                callback.onSuccess(parseResponse(response));
            }
            finally {

            }
//            catch (Exception e) {
//                callback.onError(e);
//            }

        }
    }

    private IAeduHttpResponse parseResponse(final IAeduHttpResponse<String> response) {
        if(null == response){
            return null;
        }
        return (IAeduHttpResponse) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{IAeduHttpResponse.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                try {
                    if (method.getDeclaringClass() == Object.class) {
                        return method.invoke(this, args);
                    }
                    if ("code".equals(method.getName())) {
                        return response.code();
                    } else if ("body".equals(method.getName())) {
                        return builder.converter().convert(response.body(), getGenTypeClass(callback));
                    } else if ("headers".equals(method.getName())) {
                        return response.headers();
                    } else {
                        callback.onError(new RuntimeException(String.format(Locale.getDefault(), "Not supported[IAeduHttpResponse.%s]", method.getName())));
                        return null;
                    }
                } catch (Exception e) {
                    callback.onError(e);
                }
                return null;
            }
        });
    }

    @Override
    public void onError(Throwable e) {
        if (null != callback) {
            callback.onError(e);
        }
    }

    private Type getGenTypeClass(IAeduHttpService.Callback callback) {
        TypeToken token = TypeToken.of(callback.getClass());
        TypeToken.TypeSet types = token.getTypes();
        Iterator<TypeToken> iterator = types.iterator();
        while (iterator.hasNext()) {
            Type type = iterator.next().getType();
            if (!(type instanceof ParameterizedType)) {
                continue;
            }
            ParameterizedType parameterizedType = ((ParameterizedType) type);
            Type rawType = parameterizedType.getRawType();
            if (rawType == IAeduHttpService.Callback.class) {
                Type[] params = parameterizedType.getActualTypeArguments();
                return params[0];
            }
        }
        return Object.class;
    }
}
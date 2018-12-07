package net.chinaedu.aedu.network.http;

import java.io.File;
import java.util.Map;

/**
 * Created by hsh on 2017/5/21.
 */

public interface IAeduHttpService {
    <T> IAeduHttpCall<?> get(String url, Map<String, String> headers, Map<String, String> params, Callback<T> callback);

    <T> IAeduHttpCall<?> post(String url, Map<String, String> headers, Map<String, String> params, Callback<T> callback);

    <T> IAeduHttpCall<?> put(String url, Map<String, String> headers, Map<String, String> params, Callback<T> callback);

    <T> IAeduHttpCall<?> delete(String url, Map<String, String> headers, Map<String, String> params, Callback<T> callback);

    <T> IAeduHttpCall<?> upload(String url, Map<String, String> headers, Map<String, String> params, Map<String, File> files, Callback<String> callback);

    interface Callback<T> {
        void onSuccess(IAeduHttpResponse<T> response);

        void onError(Throwable e);
    }
}

package net.chinaedu.aedu.network.request;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MartinKent
 * @time 2018/10/29
 */
public class Request {

    private final Method mMethod;
    private final Map<String, String> mHeader;
    private final Map<String, String> mParams;

    private Request mParent;

    public enum Method {
        GET,
        POST,
        PUT,
        DELETE
    }

    private Request(Method method, Map<String, String> header, Map<String, String> params, Callback callback) {
        mMethod = method;
        mHeader = null == header ? new HashMap<String, String>() : header;
        mParams = null == params ? new HashMap<String, String>() : params;
    }

    public static Request create(Method method, Map<String, String> params, Callback callback) {
        return new Request(method, null, params, callback);
    }

    public static Request create(Method method, Map<String, String> header, Map<String, String> params, Callback callback) {
        return new Request(method, header, params, callback);
    }

    public Request then(Request... requests) {
        for (Request request : requests) {
            request.setParent(this);
        }
        return this;
    }

    public Request thenif(boolean condition, Request[] requestsIf, Request[] requestsElse) {
        if (null != requestsIf) {
            for (Request request : requestsIf) {
                request.setParent(this);
            }
        }
        if (null != requestsElse) {
            for (Request request : requestsElse) {
                request.setParent(this);
            }
        }
        return this;
    }

    public void start() {

    }

    public void cancel() {

    }

    public interface Callback<T> {
        void onResponse(Request request, T response);

        void onError(Request request, Throwable e);
    }

    private void setParent(Request parent) {
        mParent = parent;
    }
}

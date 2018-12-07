package net.chinaedu.aedu.network.http.iml.retrofit;

import android.support.annotation.NonNull;

import net.chinaedu.aedu.network.http.IAeduHttpCall;
import net.chinaedu.aedu.network.http.AeduHttpServiceBuilder;
import net.chinaedu.aedu.network.http.AeduBaseHttpServiceHandler;
import net.chinaedu.aedu.network.http.IAeduHttpResponse;
import net.chinaedu.aedu.network.http.IAeduHttpService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hsh on 2017/5/7.
 */

@SuppressWarnings("unchecked")
public class AeduRetrofitServiceHandler extends AeduBaseHttpServiceHandler {

    private IAeduRetrofitService mRetrofitService;

    public AeduRetrofitServiceHandler(@NonNull AeduHttpServiceBuilder builder) {
        super(builder);
        mRetrofitService = AeduRetrofitFactory.getService(builder.baseUrl(), builder.httpServiceParameter());
    }

    @Override
    protected Object handleGet(String url, Map<String, String> headers, Map<String, String> params, final IAeduHttpService.Callback<String> callback) {
        Call<String> call = mRetrofitService.get(url, headers, params);
        call.enqueue(new RetrofitCallbackIml(callback));
        return getResult(call);
    }

    @Override
    protected Object handlePost(String url, Map<String, String> headers, Map<String, String> params, final IAeduHttpService.Callback<String> callback) {
        Call<String> call = mRetrofitService.post(url, headers, params);
        call.enqueue(new RetrofitCallbackIml(callback));
        return getResult(call);
    }

    @Override
    protected Object handlePut(String url, Map<String, String> headers, Map<String, String> params, final IAeduHttpService.Callback<String> callback) {
        Call<String> call = mRetrofitService.put(url, headers, params);
        call.enqueue(new RetrofitCallbackIml(callback));
        return getResult(call);
    }

    @Override
    protected Object handleDelete(String url, Map<String, String> headers, Map<String, String> params, final IAeduHttpService.Callback<String> callback) {
        Call<String> call = mRetrofitService.delete(url, headers, params);
        call.enqueue(new RetrofitCallbackIml(callback));
        return getResult(call);
    }

    @Override
    protected Object handleUpload(String url, Map<String, String> headers, Map<String, String> params, Map<String, File> files, IAeduHttpService.Callback<String> callback) {
        List<MultipartBody.Part> parts = new ArrayList<>();
        Set<Map.Entry<String, String>> paramSet = params.entrySet();
        for (Map.Entry<String, String> entry : paramSet) {
            parts.add(MultipartBody.Part.createFormData(entry.getKey(), entry.getValue()));
        }
        Set<Map.Entry<String, File>> fileSet = files.entrySet();
        for (Map.Entry<String, File> entry : fileSet) {
            parts.add(MultipartBody.Part.createFormData(entry.getKey(), entry.getValue().getName(), RequestBody.create(MediaType.parse("multipart/form-data"), entry.getValue())));
        }
        Call<String> call = mRetrofitService.upload(url, headers, parts);
        call.enqueue(new RetrofitCallbackIml(callback));
        return getResult(call);
    }

    private IAeduHttpCall getResult(Call<String> call){
        IAeduHttpCall httpResult = new AeduRetofitCall();
        httpResult.setResult(call);
        return httpResult;
    }

    private class RetrofitCallbackIml implements Callback<String> {

        private final IAeduHttpService.Callback<String> callback;

        RetrofitCallbackIml(IAeduHttpService.Callback<String> callback) {
            this.callback = callback;
        }

        @Override
        public void onResponse(Call<String> call, Response<String> response) {
            callback.onSuccess(new HttpResponseIml(response));
        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {
            callback.onError(t);
        }
    }

    private static class HttpResponseIml implements IAeduHttpResponse<String> {
        private int code;
        private String body;
        private Map<String, List<String>> headers;

        HttpResponseIml(Response<String> response) {
            this.code = response.code();
            this.body = response.body();
            this.headers = response.headers().toMultimap();
        }

        @Override
        public int code() {
            return code;
        }

        @Override
        public String body() {
            return body;
        }

        @Override
        public Map<String, List<String>> headers() {
            return headers;
        }
    }
}

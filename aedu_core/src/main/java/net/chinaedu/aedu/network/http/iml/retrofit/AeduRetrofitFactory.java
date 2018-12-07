package net.chinaedu.aedu.network.http.iml.retrofit;

import net.chinaedu.aedu.network.http.AeduHttpServiceParameter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by hsh on 2017/5/21.
 */

class AeduRetrofitFactory {
//    private static OkHttpClient OkHttpClient = new OkHttpClient.Builder()
//                    .readTimeout(30, TimeUnit.SECONDS)//设置读取超时时间
//                    .writeTimeout(30,TimeUnit.SECONDS)//设置写的超时时间
//                    .connectTimeout(30,TimeUnit.SECONDS)//设置连接超时时间
//                    .build();

    private static ConcurrentHashMap<String, IAeduRetrofitService> mRetrofitServices = new ConcurrentHashMap<>();

    static IAeduRetrofitService getService(String baseUrl, AeduHttpServiceParameter httpServiceParameter) {
        IAeduRetrofitService service = mRetrofitServices.get(baseUrl);
        if (null == service) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .readTimeout(httpServiceParameter.getReadTimeout(), httpServiceParameter.getTimeUnit())//设置读取超时时间
                            .writeTimeout(httpServiceParameter.getWriteTimeout(), httpServiceParameter.getTimeUnit())//设置写的超时时间
                            .connectTimeout(httpServiceParameter.getConnectTimeout(), httpServiceParameter.getTimeUnit())//设置连接超时时间
                            .build();

            service = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                    .create(IAeduRetrofitService.class);
            mRetrofitServices.put(baseUrl, service);
        }

        return service;
    }
}

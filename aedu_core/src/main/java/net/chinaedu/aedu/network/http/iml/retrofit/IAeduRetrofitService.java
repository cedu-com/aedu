package net.chinaedu.aedu.network.http.iml.retrofit;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by hsh on 2017/5/21.
 */

interface IAeduRetrofitService {
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @GET
    Call<String> get(@Url String url, @HeaderMap Map<String, String> headers, @QueryMap Map<String, String> params);

    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST
    Call<String> post(@Url String url, @HeaderMap Map<String, String> headers, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @PUT
    Call<String> put(@Url String url, @HeaderMap Map<String, String> headers, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @DELETE
    Call<String> delete(@Url String url, @HeaderMap Map<String, String> headers, @FieldMap Map<String, String> params);

    @Multipart
    @POST
    Call<String> upload(@Url String url, @HeaderMap Map<String, String> headers, @Part List<MultipartBody.Part> file);
}

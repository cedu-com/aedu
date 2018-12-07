package net.chinaedu.aedu.utils.gson;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * @author MartinKent
 * @time 2018/4/3
 */
public class AeduGsonUtil {
    private static Gson gson = new GsonBuilder()
            .serializeNulls()
            .registerTypeAdapterFactory(ArrayTypeAdapter.FACTORY)
            .registerTypeAdapterFactory(CollectionTypeAdapter.FACTORY)
//            .registerTypeAdapterFactory(TypeAdapters.STRING_FACTORY)
            .create();

    public static <T> T fromJson(String json, Class<T> typeOfT) {
        return gson.fromJson(json, typeOfT);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }

    public static <T> T fromJson(String json, TypeToken<T> typeOfT) {
        return gson.fromJson(json, typeOfT.getType());
    }

    public static String toJson(Object o) {
        return gson.toJson(o);
    }
}

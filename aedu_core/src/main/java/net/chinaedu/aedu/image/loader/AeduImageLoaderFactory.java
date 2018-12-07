package net.chinaedu.aedu.image.loader;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by MartinKent on 2017/4/26.
 */

public class AeduImageLoaderFactory {
    public enum LoaderName {
        GLIDE,
        PICASSO
    }

    private static LoaderName mDefault = LoaderName.GLIDE;

    private static final Map<LoaderName, AbstractAeduImageLoader> imageloaders = new ConcurrentHashMap<>();

    static {
        imageloaders.put(LoaderName.PICASSO, new AeduPicassoImageLoader());
        imageloaders.put(LoaderName.GLIDE, new AeduGlideImageLoader());
    }

    public static void setDefault(LoaderName loader) {
        mDefault = loader;
    }

    public static AbstractAeduImageLoader getDefault() {
        return get(mDefault);
    }

    public static AbstractAeduImageLoader get(LoaderName loaderName) {
        return imageloaders.get(loaderName);
    }
}

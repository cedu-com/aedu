package net.chinaedu.aedu.image.loader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by MartinKent on 2017/4/26.
 */

public abstract class AbstractAeduImageLoader {

    public final AeduRequestCreator load(Uri uri) {
        return new AeduRequestCreator(this, uri);
    }

    public final AeduRequestCreator load(String path) {
        return new AeduRequestCreator(this, Uri.parse(path));
    }

    public final AeduRequestCreator load(File file) {
        return new AeduRequestCreator(this, Uri.fromFile(file));
    }

    public final AeduRequestCreator load(@DrawableRes int resId) {
        return new AeduRequestCreator(this, resId);
    }

    abstract void into(Context context, ImageView target, AeduRequestCreator creator);

    public static class AeduRequestCreator {

        private int mDrawableRes = -1;
        private Drawable mPlaceHolderDrawable, mErrorDrawable;

        private final AbstractAeduImageLoader mLoader;
        private Uri uri = null;
        private int mResizeWidth = -1;
        private int mResizeHeight = -1;

        private boolean isGif = false;

        AeduRequestCreator(AbstractAeduImageLoader loader, Uri uri) {
            this.mLoader = loader;
            this.uri = uri;
        }

        AeduRequestCreator(AbstractAeduImageLoader loader, @DrawableRes int drawableRes) {
            this.mLoader = loader;
            this.mDrawableRes = drawableRes;
        }

        public AeduRequestCreator placeHolder(Drawable drawable) {
            mPlaceHolderDrawable = drawable;
            return this;
        }

        public AeduRequestCreator error(Drawable drawable) {
            mErrorDrawable = drawable;
            return this;
        }

        public AeduRequestCreator resize(int w, int h) {
            this.mResizeWidth = w;
            this.mResizeHeight = h;
            return this;
        }

        public AeduRequestCreator asGif() {
            this.isGif = true;
            return this;
        }

        public void into(Context context, ImageView imageView) {
            mLoader.into(context, imageView, this);
        }

        Uri getUri() {
            return uri;
        }

        Drawable getPlaceHolder() {
            return mPlaceHolderDrawable;
        }

        Drawable getErrorDrawable() {
            return mErrorDrawable;
        }

        int getResizeWidth() {
            return mResizeWidth;
        }

        int getResizeHeight() {
            return mResizeHeight;
        }

        int getDrawableRes() {
            return mDrawableRes;
        }

        public boolean isGif() {
            return isGif;
        }
    }

//    public static class NotSupportedException extends RuntimeException {
//        public NotSupportedException() {
//        }
//
//        public NotSupportedException(String message) {
//            super(message);
//        }
//
//        public NotSupportedException(String message, Throwable cause) {
//            super(message, cause);
//        }
//
//        public NotSupportedException(Throwable cause) {
//            super(cause);
//        }
//
//        public NotSupportedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
//            super(message, cause, enableSuppression, writableStackTrace);
//        }
//    }

    /**
     * 暂停请求
     * @param context
     */
    public abstract void pauseRequest(Context context);

    /**
     * 暂停请求
     * @param context
     */
    public abstract void resumeRequest(Context context);

    /**
     * 销毁所有请求
     * @param context
     */
    public abstract void destoryRequest(Context context);
}